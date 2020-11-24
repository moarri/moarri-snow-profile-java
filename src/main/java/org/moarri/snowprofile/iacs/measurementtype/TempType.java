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

package org.moarri.snowprofile.iacs.measurementtype;

import org.moarri.snowprofile.iacs.gml.NilReasonEnumeration;
import org.moarri.snowprofile.iacs.unittype.UomTempType;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */

public class TempType extends TempOrNilReasonType implements FixedUom {
    private static final UomTempType FIXED_UOM = UomTempType.DEGC;
    private final UomTempType uom = FIXED_UOM;

    public UomTempType getUom() {
        return uom;
    }

    public TempType(double value, UomTempType uom) throws ValueOutsideRangeException, UomWrongUnitException {
        super(value);
        if (uom != FIXED_UOM){
            throw new UomWrongUnitException();
        }
    }

    public TempType(NilReasonEnumeration nilReason, UomTempType uom) throws UomWrongUnitException {
        super(nilReason);
        if (uom != FIXED_UOM){
            throw new UomWrongUnitException();
        }
    }



    @Override
    public Object fixedUom() {
        return FIXED_UOM;
    }
}
