/*
 * Copyright (c) 2019 Moarri Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.moarri.snowprofile.caaml.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * @author Kuba Radliński <kuba.radlinski at harpy.pl >
 */

class CaamlValidator {
    private static final String XSD_PATH = "/resources/xsd";
    private static final String XSD_FILE_NAME = "CAAMLv6_SnowProfileIACS.xsd";


    static ValidationResult validateCaaml(String caamlText){
        ValidationResult result ;
        Optional<CaamlVersion> optionalCaamlVersion = Optional.empty();
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource( new StringReader( caamlText ) ));
            doc.getDocumentElement().normalize();
            Element rootElement = doc.getDocumentElement();
            String caamlAttributeString = rootElement.getAttribute("xmlns:caaml");
            if (caamlAttributeString == null || caamlAttributeString.isEmpty()){
                return new ValidationResult(ValidationResultType.XML_VALIDATION_ERROR, null, "No xmlns:caaml attribute");
            }
            String [] parts = caamlAttributeString.split("/");
            if (parts.length <1) {
                return new ValidationResult(ValidationResultType.XML_VALIDATION_ERROR, null, "Empty xmlns:caaml attribute");
            }
            String versionString = parts[parts.length-1];
            optionalCaamlVersion = Arrays.stream(CaamlVersion.values()).filter(cv->(cv.getVersionString().equals(versionString))).findFirst();
            if (optionalCaamlVersion.isEmpty()){
                return new ValidationResult(ValidationResultType.CAAML_UNSUPORTED_VERSION, null, "Unknown version string: "+versionString);
            }
            String xsdPath = XSD_PATH+"/"+optionalCaamlVersion.get().getVersionString().replace('.','_')+"/"+XSD_FILE_NAME;
            Source xsdSource = new StreamSource(ValidationResult.class.getResourceAsStream(xsdPath));

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schemaFactory.setResourceResolver(new ResourceResolver());
            Schema schema = schemaFactory.newSchema(xsdSource);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource( new StringReader( caamlText ) ));
            result = new ValidationResult(ValidationResultType.VALIDATION_OK, optionalCaamlVersion.get(), "");
        } catch (ParserConfigurationException | IOException ex) {
            result = new ValidationResult(ValidationResultType.XML_PARSE_ERROR, null, ex.getLocalizedMessage());
        } catch (SAXException e1) {
            result = new ValidationResult(ValidationResultType.XML_VALIDATION_ERROR, optionalCaamlVersion.orElseGet(null), e1.getMessage());
        }
            return result;
    }
}
