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

package org.moarri.snowprofile.caaml.engine.readers;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class SnowProfileReader {
    public static final String MAIN_NODE="SnowProfile";
    public static final String ATTR_GML_ID="gml:id";
    public static final String ATTR_XMLNS = "xmlns";
    public static final String ATTR_XMLNS_VALUE = "http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS";
    public static final String ATTR_XMLNS_GML = "xmlns:gml";
    public static final String ATTR_XMLNS_GML_VALUE = "http://www.opengis.net/gml";
    public static final String ATTR_XMLNS_APP = "xmlns:app";
    public static final String ATTR_XMLNS_APP_VALUE = "http://www.snowprofileapplication.com";
    public static final String ATTR_XMLNS_XSI = "xmlns:xsi";
    public static final String ATTR_XMLNS_XSI_VALUE = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String ATTR_XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
    public static final String ATTR_XSI_SCHEMA_LOCATION_VALUE = "http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS http://caaml.org/Schemas/V5.0/Profiles/SnowprofileIACS/CAAMLv5_SnowProfileIACS.xsd";
    public static final String CHILD_META_DATA = "metaData";
    public static final String CHILD_TIME_REF = "timeRef";
    public static final String CHILD_SNOW_PROFILE_RESULTS_OF = "snowProfileResultsOf";
    public static final String CHILD_SRC_REF = "srcRef";
    public static final String CHILD_LOC_REF = "locRef";




}
