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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Kuba Radliński
 */
public class SnowProfile {

    private final String id;
    private final MetaData metaData;
    private final TimeRef timeRef;
    private final SrcRef srcRef;
    private final LocRef locRef;
    private final SnowProfileResultsOf snowProfileResultsOf;
    private final String application;
    private final String applicationVersion;

    public String getId() {
        return id;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public TimeRef getTimeRef() {
        return timeRef;
    }

    public SrcRef getSrcRef() {
        return srcRef;
    }

    public LocRef getLocRef() {
        return locRef;
    }

    public SnowProfileResultsOf getSnowProfileResultsOf() {
        return snowProfileResultsOf;
    }

    public String getApplication() {
        return application;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public SnowProfile(Builder builder){
        this.id = builder.id;
        this.metaData = builder.metaData;
        this.timeRef = builder.timeRef;
        this.srcRef = builder.srcRef;
        this.locRef = builder.locRef;
        this.snowProfileResultsOf = builder.snowProfileResultsOf;
        this.application = builder.application;
        this.applicationVersion = builder.applicationVersion;
    }

    public static Builder builder(String id, TimeRef timeRef, SrcRef srcRef, LocRef locRef, SnowProfileResultsOf snowProfileResultsOf){
        return new Builder(id, timeRef, srcRef, locRef, snowProfileResultsOf);
    }


    public static class Builder{
        private String id;
        private MetaData metaData = null;
        private TimeRef timeRef;
        private SrcRef srcRef;
        private LocRef locRef;
        private SnowProfileResultsOf snowProfileResultsOf;
        private String application = null;
        private String applicationVersion = null;

        public Builder withMetaData(MetaData metaData) {
            this.metaData = metaData;
            return this;
        }

        public Builder withApplicationInfo(String application, String applicationVersion) {
            this.application = application;
            this.applicationVersion = applicationVersion;
            return this;
        }

        private Builder(String id, TimeRef timeRef, SrcRef srcRef, LocRef locRef, SnowProfileResultsOf snowProfileResultsOf) {
            this.id = id;
            this.timeRef = timeRef;
            this.srcRef = srcRef;
            this.locRef = locRef;
            this.snowProfileResultsOf = snowProfileResultsOf;
        }

        public SnowProfile build(){
            return new SnowProfile(this);
        }
    }


}
