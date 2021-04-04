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
import java.util.List;

/**
 *
 * @author Kuba Radliński
 */
public class SnowProfileResultsOf {
    private final SnowProfileMeasurementsType snowProfileMeasurements;

    public SnowProfileMeasurementsType getSnowProfileMeasurements() {
        return snowProfileMeasurements;
    }

    public SnowProfileResultsOf(SnowProfileMeasurementsType snowProfileMeasurements) {
        this.snowProfileMeasurements = snowProfileMeasurements;
    }

    //    private CaamlSnowProfileMeasurements snowProfileMeasurements = new CaamlSnowProfileMeasurements();
//
//    public CaamlSnowProfileMeasurements getSnowProfileMeasurements() {
//        return snowProfileMeasurements;
//    }
//
//    public void setSnowProfileMeasurements(CaamlSnowProfileMeasurements snowProfileMeasurements) {
//        this.snowProfileMeasurements = snowProfileMeasurements;
//    }
//
//
//    public Double getAirTemp(){
//        if(snowProfileMeasurements==null){
//            return null;
//        }
//        return snowProfileMeasurements.getAirTempPres();
//    }
//
//
//    public double getSnowHeight(){
//        if(snowProfileMeasurements==null){
//            return 0d;
//        }
//        return snowProfileMeasurements.getSnowHeight();
//    }
//
//    public List<CaamlTempProfileObs> getSortedTempProfile(){
//        return snowProfileMeasurements != null? snowProfileMeasurements.getSortedTempProfile():Collections.EMPTY_LIST;
//    }
//
//    public List<CaamlLayer> getSortedLayers(){
//        return snowProfileMeasurements != null? snowProfileMeasurements.getSortedLayers():Collections.EMPTY_LIST;
//    }
//
//
}
