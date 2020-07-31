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

/**
 * @author Kuba Radliński <kuba.radlinski at harpy.pl >
 */

public abstract class PercentIntType extends NilReasonType {
    public static final int MIN_INCLUSIVE_VALUE = 0;
    public static final int MAX_INCLUSIVE_VALUE = 110;
    private int value;

    public int getValue() throws NilValueException {
        if (isNilReason()) {
            throw new NilValueException();
        }
        return value;
    }

    public PercentIntType(NilReasonEnumeration nilReason) {
        super(nilReason);
    }

    public PercentIntType(int value) throws ValueOutsideRangeException {
        super(null);
        if (value<MIN_INCLUSIVE_VALUE || value > MAX_INCLUSIVE_VALUE) {
            throw new ValueOutsideRangeException();
        }
        this.value = value;
    }
}
