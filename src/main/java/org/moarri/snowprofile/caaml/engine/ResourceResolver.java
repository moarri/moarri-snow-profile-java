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

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

import java.io.InputStream;
import java.util.Objects;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */


public class ResourceResolver implements LSResourceResolver {
    private static final String XSD_PATH = "/resources/xsd";
    private static final String XSD_GML_FILE_NAME = "CAAMLv6_SnowProfileIACS_GML.xsd";

    @Override
    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, final String systemId, final String baseURI) {
        Objects.requireNonNull(systemId, String.format("Systemid for required xsd file is null (%s, %s", namespaceURI, publicId));
        final String[] parts = systemId.split("/");
        final String version = parts.length>=2 ? parts[parts.length-2] : "";
        final String fileName = parts.length>=1 ? parts[parts.length-1] : "";
        final String xsdPath = fileName.equals(XSD_GML_FILE_NAME) ? XSD_PATH+"/"+version.replace('.','_')+"/"+XSD_GML_FILE_NAME : "";
        final InputStream resourceAsStream = ResourceResolver.class.getResourceAsStream(xsdPath);
        Objects.requireNonNull(resourceAsStream, String.format("Could not find the specified xsd file: %s", systemId));
        return new Input(publicId, systemId, resourceAsStream);
    }
}