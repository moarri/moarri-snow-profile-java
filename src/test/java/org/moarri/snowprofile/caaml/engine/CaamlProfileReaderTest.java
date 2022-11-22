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

package org.moarri.snowprofile.caaml.engine;

import org.junit.Test;
import org.moarri.snowprofile.caaml.engine.parser.nodes.CaamlException;
import org.moarri.snowprofile.caaml.profile.SnowProfileType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class CaamlProfileReaderTest {
    private static final String TEST_CAAML_FILE="/resources/test-profile-data/SnowProfile_IACS_SLF22950.xml";
    private static final String TEST_WRONG_ELEMENT_CAAML_FILE="/resources/test-profile-data/SnowProfile_IACS_SLF22950-wrong-element.xml";
    private static final String TEST_WRONG_VERSION_CAAML_FILE="/resources/test-profile-data/SnowProfile_IACS_SLF22950-wrong-version.xml";

    @Test
    public void readCaamlStringProperProfileFile() {
        try (InputStream is = this.getClass().getResourceAsStream(TEST_CAAML_FILE)) {
            String caamlText = TextFileReader.readFromInputStream(is);
            CaamlProfileReader reader = new CaamlProfileReader();
            Optional<SnowProfileType> result = reader.readCaamlString(caamlText);
            assertTrue(result.isPresent());
        } catch (IOException | CaamlException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = CaamlException.class)
    public void readCaamlStringWrongProfileFile() throws CaamlException {
        for(String testFilePath: List.of(TEST_WRONG_ELEMENT_CAAML_FILE, TEST_WRONG_VERSION_CAAML_FILE)){
            try (InputStream is = this.getClass().getResourceAsStream(testFilePath)) {
                String caamlText = TextFileReader.readFromInputStream(is);
                CaamlProfileReader reader = new CaamlProfileReader();
                Optional<SnowProfileType> result = reader.readCaamlString(caamlText);
                assertNotNull(result);
            } catch (IOException e) {
                fail(e.getMessage());
            }
        }
    }
}