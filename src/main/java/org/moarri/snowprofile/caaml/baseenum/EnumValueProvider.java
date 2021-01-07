/*
 * Copyright (c) 2020 Moarri Project
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

package org.moarri.snowprofile.caaml.baseenum;

import org.moarri.snowprofile.caaml.engine.nodetools.NonExistingCodeException;
import org.moarri.snowprofile.caaml.engine.nodetools.NullCodeValueException;

import java.util.Arrays;

public class EnumValueProvider {
    public static <E extends CodeableEnum> E valueOfCode(Class<E> enumClass, String code) throws NonExistingCodeException, NullCodeValueException {
        if (code == null) {
            throw new NullCodeValueException(enumClass);
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(ec -> (ec.getCode().equals(code)))
                .findAny()
                .orElseThrow(() -> (new NonExistingCodeException(enumClass, code)));
    }

}
