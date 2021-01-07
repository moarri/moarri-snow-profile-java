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

import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;

/**
 *
 * @author Kuba Radliński
 */
public enum PrecipTIType implements CodeableEnum {

    DZ_MINUS("-DZ"),
    DZ("DZ"),
    DZ_PLUS("+DZ"),
    RA_MINUS("-RA"),
    RA("RA"),
    RA_PLUS("+RA"),
    SN_MINUS("-SN"),
    SN("SN"),
    SN_PLUS("+SN"),
    SG_MINUS("-SG"),
    SG("SG"),
    SG_PLUS("+SG"),
    IC_MINUS("-IC"),
    IC("IC"),
    IC_PLUS("+IC"),
    PE_MINUS("-PE"),
    PE("PE"),
    PE_PLUS("+PE"),
    GR_MINUS("-GR"),
    GR("GR"),
    GR_PLUS("+GR"),
    GS_MINUS("-GS"),
    GS("GS"),
    GS_PLUS("+GS"),
    UP("UP"),
    NIL("Nil"),
    RASN("RASN"),
    FZRA("FZRA");
    private final String code;

    @Override
    public String getCode() {
        return code;
    }

    PrecipTIType(String code) {
        this.code = code;
    }

}
