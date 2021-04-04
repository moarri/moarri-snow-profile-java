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

import org.moarri.snowprofile.caaml.gml.NilReasonEnumeration;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class MeasureLengthCmType extends NonNegDoubleOrNilReasonType implements FixedUom<UomLengthType>{
    private static final UomLengthType FIXED_UOM = UomLengthType.CM;
    private final UomLengthType uom = FIXED_UOM;

    public UomLengthType getUom() {
        return uom;
    }


    public MeasureLengthCmType(Double value, Uom uom) throws NegativeValueException, UomWrongUnitException {
        super(value);
        if (uom != FIXED_UOM){
            throw new UomWrongUnitException();
        }

    }

    public MeasureLengthCmType(NilReasonEnumeration nilReason, Uom uom) throws UomWrongUnitException {
        super(nilReason);
        if (uom != FIXED_UOM){
            throw new UomWrongUnitException();
        }
    }

    @Override
    public boolean isFixedUom() {
        return true;
    }

    @Override
    public UomLengthType fixedUom() {
        return uom;
    }
}
