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

class CaamlMeta {
    public enum SnowProfileType  {
    }

    public static class SnowProfileResultsOf{
        static final String MAIN_NODE="caaml:snowProfileResultsOf";
        static final String CHILD_SNOW_PROFILE_MEASUREMENTS = "caaml:SnowProfileMeasurements";

    }

    public static class SnowProfileMeasurementsType{
        static final String MAIN_NODE = "caaml:SnowProfileMeasurements";
        static final String ATTR_DIR = "dir";
        static final String CHILD_PROFILE_DEPTH = "caaml:profileDepth";
    }

}
