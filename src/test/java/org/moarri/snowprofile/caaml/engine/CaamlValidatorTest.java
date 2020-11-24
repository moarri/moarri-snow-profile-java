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

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CaamlValidatorTest {
    private static final String TEST_CAAML_FILE="/resources/test-profile-data/SnowProfile_IACS_SLF22950.xml";
    private static final String TEST_WRONG_ELEMENT_CAAML_FILE="/resources/test-profile-data/SnowProfile_IACS_SLF22950-wrong-element.xml";
    private static final String TEST_WRONG_VERSION_CAAML_FILE="/resources/test-profile-data/SnowProfile_IACS_SLF22950-wrong-version.xml";

    @Test
    public void validateProperCaaml() {
        try (InputStream is = this.getClass().getResourceAsStream(TEST_CAAML_FILE)) {
            String caamlText = TextFileReader.readFromInputStream(is);
            ValidationResult expectedResult = new ValidationResult(ValidationResultType.VALIDATION_OK, CaamlVersion.V_6_0_3, "");
            ValidationResult result = CaamlValidator.validateCaaml(caamlText);
            assertEquals(expectedResult, result);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateWrongElementCaaml() {
        try (InputStream is = this.getClass().getResourceAsStream(TEST_WRONG_ELEMENT_CAAML_FILE)) {
            String caamlText = TextFileReader.readFromInputStream(is);
            ValidationResult expectedResult = new ValidationResult(ValidationResultType.XML_VALIDATION_ERROR, CaamlVersion.V_6_0_3, "");
            ValidationResult result = CaamlValidator.validateCaaml(caamlText);
            assertEquals(expectedResult, result);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateWrongVersionCaaml() {
        try (InputStream is = this.getClass().getResourceAsStream(TEST_WRONG_VERSION_CAAML_FILE)) {
            String caamlText = TextFileReader.readFromInputStream(is);
            ValidationResult expectedResult = new ValidationResult(ValidationResultType.CAAML_UNSUPORTED_VERSION, null, "");
            ValidationResult result = CaamlValidator.validateCaaml(caamlText);
            assertEquals(expectedResult, result);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

}