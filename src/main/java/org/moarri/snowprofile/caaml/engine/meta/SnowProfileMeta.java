/*
 * Copyright (c) 2022 Moarri Project
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

package org.moarri.snowprofile.caaml.engine.meta;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */
public enum SnowProfileMeta implements Meta {
    MAIN_NODE("caaml:SnowProfile"),
    ATTR_GML_ID("gml:id"),
    ATTR_XMLNS("xmlns"),
    ATTR_XMLNS_VALUE("http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS"),
    ATTR_XMLNS_GML("xmlns:gml"),
    ATTR_XMLNS_GML_VALUE("http://www.opengis.net/gml"),
    ATTR_XMLNS_APP("xmlns:app"),
    ATTR_XMLNS_APP_VALUE("http://www.snowprofileapplication.com"),
    ATTR_XMLNS_XSI("xmlns:xsi"),
    ATTR_XMLNS_XSI_VALUE("http://www.w3.org/2001/XMLSchema-instance"),
    ATTR_XSI_SCHEMA_LOCATION("xsi:schemaLocation"),
    ATTR_XSI_SCHEMA_LOCATION_VALUE("http://caaml.org/Schemas/V5.0/Profiles/SnowProfileIACS http://caaml.org/Schemas/V5.0/Profiles/SnowprofileIACS/CAAMLv5_SnowProfileIACS.xsd"),
    CHILD_META_DATA("caaml:metaData"),
    CHILD_TIME_REF("caaml:timeRef"),
    CHILD_SNOW_PROFILE_RESULTS_OF("caaml:snowProfileResultsOf"),
    CHILD_SRC_REF("caaml:srcRef"),
    CHILD_LOC_REF("caaml:locRef"),
    CHILD_APPLICATION("caaml:application"),
    CHILD_APPLICATION_VERSION("caaml:applicationVersion");

    private final String value;

    SnowProfileMeta(java.lang.String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
