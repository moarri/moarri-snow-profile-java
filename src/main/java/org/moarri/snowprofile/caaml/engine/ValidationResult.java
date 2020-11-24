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

package org.moarri.snowprofile.caaml.engine;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */

public class ValidationResult {
    private final ValidationResultType resultType;
    private final CaamlVersion version;
    private final String msg;

    public ValidationResultType getResultType() {
        return resultType;
    }

    public CaamlVersion getVersion() {
        return version;
    }

    public String getMsg() {
        return msg;
    }

    public ValidationResult(ValidationResultType resultType, CaamlVersion version, String msg) {
        this.resultType = resultType;
        this.version = version;
        this.msg = msg;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ValidationResult other = (ValidationResult) obj;
        if (this.resultType != other.resultType) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[%s,%s,%s]", this.getClass().getCanonicalName(), resultType, version, msg);
    }

}
