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

import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */

public enum IACSComprTestScoreNumType implements CodeableEnum {
    CT_0("0"),
    CT_01("1"),
    CT_02("2"),
    CT_03("3"),
    CT_04("4"),
    CT_05("5"),
    CT_06("6"),
    CT_07("7"),
    CT_08("8"),
    CT_09("9"),
    CT_10("10"),
    CT_11("11"),
    CT_12("12"),
    CT_13("13"),
    CT_14("14"),
    CT_15("15"),
    CT_16("16"),
    CT_17("17"),
    CT_18("18"),
    CT_19("19"),
    CT_20("20"),
    CT_21("21"),
    CT_22("22"),
    CT_23("23"),
    CT_24("24"),
    CT_25("25"),
    CT_26("26"),
    CT_27("27"),
    CT_28("28"),
    CT_29("29"),
    CT_30("30");

    private String code;

    @Override
    public String getCode() {
        return code;
    }

    IACSComprTestScoreNumType(String code) {
        this.code = code;
    }


}
