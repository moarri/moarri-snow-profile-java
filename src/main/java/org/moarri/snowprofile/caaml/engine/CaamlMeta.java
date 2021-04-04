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

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

class CaamlMeta {
    static class SnowProfileType {
        static final String MAIN_NODE="caaml:SnowProfile";
        static final String ATTR_GML_ID="gml:id";
        static final String ATTR_XMLNS = "xmlns";
        static final String ATTR_XMLNS_VALUE = "http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS";
        static final String ATTR_XMLNS_GML = "xmlns:gml";
        static final String ATTR_XMLNS_GML_VALUE = "http://www.opengis.net/gml";
        static final String ATTR_XMLNS_APP = "xmlns:app";
        static final String ATTR_XMLNS_APP_VALUE = "http://www.snowprofileapplication.com";
        static final String ATTR_XMLNS_XSI = "xmlns:xsi";
        static final String ATTR_XMLNS_XSI_VALUE = "http://www.w3.org/2001/XMLSchema-instance";
        static final String ATTR_XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
        static final String ATTR_XSI_SCHEMA_LOCATION_VALUE = "http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS http://caaml.org/Schemas/V5.0/Profiles/SnowprofileIACS/CAAMLv5_SnowProfileIACS.xsd";
        static final String CHILD_META_DATA = "caaml:metaData";
        static final String CHILD_TIME_REF = "caaml:timeRef";
        static final String CHILD_SNOW_PROFILE_RESULTS_OF = "caaml:snowProfileResultsOf";
        static final String CHILD_SRC_REF = "caaml:srcRef";
        static final String CHILD_LOC_REF = "caaml:locRef";
        static final String CHILD_APPLICATION = "caaml:application";
        static final String CHILD_APPLICATION_VERSION = "caaml:applicationVersion";
    }

    static class SnowProfileResultsOf{
        static final String MAIN_NODE="caaml:snowProfileResultsOf";
        static final String CHILD_SNOW_PROFILE_MEASUREMENTS = "caaml:SnowProfileMeasurements";

    }

    static class SnowProfileMeasurementsType{
        static final String MAIN_NODE = "caaml:SnowProfileMeasurements";
        static final String ATTR_DIR = "dir";
        static final String CHILD_PROFILE_DEPTH = "caaml:profileDepth";
    }

}
