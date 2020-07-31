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

import org.junit.Test;
import org.moarri.snowprofile.caaml.engine.nodetools.NonExistingCodeException;
import org.moarri.snowprofile.caaml.engine.nodetools.NullCodeValueException;
import org.moarri.snowprofile.iacs.tools.CodeableEnum;
import org.moarri.snowprofile.iacs.tools.EnumValueProvider;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class EnumValueProviderTest {

    enum TestEnum implements CodeableEnum<TestEnum> {
        VALUE1("codeVal1"),
        VALUE2("codeVal2");

        private String code;

        @Override
        public String getCode() {
            return code;
        }

        public static TestEnum valueOfCode(String code) throws NonExistingCodeException, NullCodeValueException {
            return EnumValueProvider.valueOfCode(TestEnum.class, code);
        }

        TestEnum(String code) {
            this.code = code;
        }


    }

    @Test
    public void testValueOfForProperValues() {
        for(TestEnum te:TestEnum.values()){
            try {
                TestEnum result = TestEnum.valueOfCode(te.getCode());
                assertSame(te, result);
            } catch (NonExistingCodeException | NullCodeValueException e) {
                fail(e.getMessage());
            }
        }
    }

    @Test(expected = NonExistingCodeException.class)
    public void testValueOfForWrongValue() throws NonExistingCodeException, NullCodeValueException {
        TestEnum result = TestEnum.valueOfCode("WRONG_VALUE");
    }

    @Test(expected = NullCodeValueException.class)
    public void testValueOfForNullValue() throws NonExistingCodeException, NullCodeValueException {
        TestEnum result = TestEnum.valueOfCode(null);
    }

}