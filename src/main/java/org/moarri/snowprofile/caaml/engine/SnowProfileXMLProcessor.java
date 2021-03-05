/*
 * Copyright (c) 2021 Moarri Project
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

import org.moarri.snowprofile.caaml.engine.nodetools.AttributeMissingException;
import org.moarri.snowprofile.caaml.profile.*;
import org.w3c.dom.Element;

import java.util.Optional;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class SnowProfileXMLProcessor extends AbstractXMLProcessor {
    private static final String MAIN_NODE="SnowProfile";
    private static final String ATTR_GML_ID="gml:id";
    private static final String ATTR_XMLNS = "xmlns";
    private static final String ATTR_XMLNS_VALUE = "http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS";
    private static final String ATTR_XMLNS_GML = "xmlns:gml";
    private static final String ATTR_XMLNS_GML_VALUE = "http://www.opengis.net/gml";
    private static final String ATTR_XMLNS_APP = "xmlns:app";
    private static final String ATTR_XMLNS_APP_VALUE = "http://www.snowprofileapplication.com";
    private static final String ATTR_XMLNS_XSI = "xmlns:xsi";
    private static final String ATTR_XMLNS_XSI_VALUE = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String ATTR_XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
    private static final String ATTR_XSI_SCHEMA_LOCATION_VALUE = "http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS http://caaml.org/Schemas/V5.0/Profiles/SnowprofileIACS/CAAMLv5_SnowProfileIACS.xsd";
    private static final String CHILD_META_DATA = "metaData";
    private static final String CHILD_TIME_REF = "timeRef";
    private static final String CHILD_SNOW_PROFILE_RESULTS_OF = "snowProfileResultsOf";
    private static final String CHILD_SRC_REF = "srcRef";
    private static final String CHILD_LOC_REF = "locRef";
    private static final String CHILD_APPLICATION = "application";
    private static final String CHILD_APPLICATION_VERSION = "applicationVersion";

    public static SnowProfile read(Element e) throws CaamlException {
        if(!MAIN_NODE.equals(e.getNodeName())){
            throw new WrongNodeException(MAIN_NODE, e.getNodeName());
        }
        if(!e.hasAttribute(ATTR_GML_ID)){
            throw new AttributeMissingException(ATTR_GML_ID, e.getNodeName());
        }
        String id = e.getAttribute(ATTR_GML_ID);
        TimeRef timeRef = findChildNode(e, CHILD_TIME_REF).flatMap(n -> Optional.of(new TimeRef())).orElseThrow(()->new MandatoryFieldException(CHILD_TIME_REF));
        SnowProfileResultsOf snowProfileResultsOf = findChildNode(e, CHILD_SNOW_PROFILE_RESULTS_OF).flatMap(n -> Optional.of(new SnowProfileResultsOf())).orElseThrow(()->new MandatoryFieldException(CHILD_SNOW_PROFILE_RESULTS_OF));
        SrcRef srcRef = findChildNode(e, CHILD_SRC_REF).flatMap(n -> Optional.of(new SrcRef())).orElseThrow(()->new MandatoryFieldException(CHILD_SRC_REF));
        LocRef locRef = findChildNode(e, CHILD_LOC_REF).flatMap(n -> Optional.of(new LocRef())).orElseThrow(()->new MandatoryFieldException(CHILD_LOC_REF));
        MetaData metaData = findChildNode(e, CHILD_META_DATA).flatMap(n -> Optional.of(new MetaData())).orElse(null);
        String application = findChildNode(e, CHILD_APPLICATION).flatMap(n -> Optional.of(n.getTextContent().trim())).orElse(null);
        String applicationVersion = findChildNode(e, CHILD_APPLICATION_VERSION).flatMap(n -> Optional.of(n.getTextContent().trim())).orElse(null);
        return SnowProfile.builder(id, timeRef, srcRef, locRef, snowProfileResultsOf)
                .withMetaData(metaData)
                .withApplicationInfo(application,applicationVersion)
                .build();
    }

    public static Element write(SnowProfile snowProfile){
        return null;
    }

}
