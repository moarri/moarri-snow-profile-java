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

package org.moarri.snowprofile.caaml.profile;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class SnowProfileMeasurementsType {
    private final DirectionType dir;
    private final MeasureLengthCmType profileDepth;

    public DirectionType getDir() {
        return dir;
    }

    public MeasureLengthCmType getProfileDepth() {
        return profileDepth;
    }

    private SnowProfileMeasurementsType(Builder builder) {
        this.dir = builder.dir;
        this.profileDepth = builder.profileDepth;
    }

    public static Builder builder(DirectionType dir){
        return new Builder(dir);
    }

    public static class Builder{
        private final DirectionType dir;
        private MeasureLengthCmType profileDepth;

        public Builder withProfileDepth(MeasureLengthCmType profileDepth) {
            this.profileDepth = profileDepth;
            return this;
        }

        private Builder(DirectionType dir) {
            this.dir = dir;
        }

        public SnowProfileMeasurementsType build(){
            return new SnowProfileMeasurementsType(this);
        }
    }
}
