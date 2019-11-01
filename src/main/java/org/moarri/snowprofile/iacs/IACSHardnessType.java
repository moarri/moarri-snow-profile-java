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

import java.util.EnumSet;
/**
 *
 * @author Kuba Radliński
 */
public enum IACSHardnessType implements CodeableEnum {

    F_MINUS("F-",10),
    F("F",20),
    F_PLUS("F+",35),
    FF4("F-4F",50),
    F4_MINUS("4F-",75),
    F4("4F",100),
    F4_PLUS("4F+",137),
    F4F1("4F-1F",175),
    F1_MINUS("1F-",210),
    F1("1F",250),
    F1_PLUS("1F+",235),
    F1P("1F-P",390),
    P_MINUS("P-",445),
    P("P",500),
    P_PLUS("P+",605),
    PK("P-K",715),
    K_MINUS("K-",900),
    K("K",1000),
    K_PLUS("K+",1100),
    KI("K-I",1150),
    I("I",1200);

    public final static EnumSet<IACSHardnessType> MAIN_ELEMENTS = EnumSet.of(F, F4, F1, P, K, I);
    public final static EnumSet<IACSHardnessType> MAIN_MIDDLE_ELEMENTS = EnumSet.of(F, FF4, F4, F4F1, F1, F1P, P, PK, K, KI, I);
    private final String code;
    private final int hardness;
    
    @Override
    public String getCode() {
        return code;
    }

    public int getHardness() {
        return hardness;
    }

    
    IACSHardnessType(String code, int hardness) {
        this.code = code;
        this.hardness = hardness;
    }

    public static IACSHardnessType valueOfCode(String code)   throws NullCodeValueException, NonExistingCodeException {
        return EnumValueProvider.valueOfCode(IACSHardnessType.class, code);
    }

}
