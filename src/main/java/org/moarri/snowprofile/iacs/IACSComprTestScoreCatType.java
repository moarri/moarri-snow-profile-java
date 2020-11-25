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

public enum IACSComprTestScoreCatType implements CodeableEnum {
    CTV("CTV"),
    CTE("CTE"),
    CTM("CTM"),
    CTH("CTH");
    private String code;

    @Override
    public String getCode() {
        return code;
    }

    IACSComprTestScoreCatType(String code) {
        this.code = code;
    }

}
