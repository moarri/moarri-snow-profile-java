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
package org.moarri.snowprofile.iacs;

/**
 *
 * @author Kuba Radliński
 */
public enum IACSUnitsSpecSurfAreaType implements CodeableEnum{

    M2KG1("m2kg-1");
    
    private final String code;

    @Override
    public String getCode() {
        return code;
    }

    IACSUnitsSpecSurfAreaType(String code) {
        this.code = code;
    }
    
    public static IACSUnitsSpecSurfAreaType valueOfCode(String code) throws NullCodeValueException, NonExistingCodeException {
        return EnumValueProvider.valueOfCode(IACSUnitsSpecSurfAreaType.class, code);
    }
    
}
