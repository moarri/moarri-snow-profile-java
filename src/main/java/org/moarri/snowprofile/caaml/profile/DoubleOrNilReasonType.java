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

import org.moarri.snowprofile.caaml.gml.NilReasonType;
import org.moarri.snowprofile.caaml.gml.NilReasonEnumeration;
import org.moarri.snowprofile.caaml.profile.NilValueException;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */

public abstract class DoubleOrNilReasonType extends NilReasonType {

    private double value;

    public double getValue() throws NilValueException {
        if (isNilReason()) {
            throw new NilValueException();
        }
        return value;
    }

    public DoubleOrNilReasonType(NilReasonEnumeration nilReason) {
        super(nilReason);
    }

    public DoubleOrNilReasonType(double value) {
        super(null);
        this.value = value;
    }
}
