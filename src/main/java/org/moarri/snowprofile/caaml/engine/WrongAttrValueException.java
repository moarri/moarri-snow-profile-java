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

import org.moarri.snowprofile.caaml.profile.CaamlException;

import java.text.MessageFormat;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class WrongAttrValueException extends CaamlException {
    private static final String MSG="Wrong attribute: {0} value <{1}> for node: {2}. Required: <{3}>";

    public WrongAttrValueException(String attrName, String value, String nodeName, String expectedValue) {
        super(MessageFormat.format(MSG, attrName, value, nodeName, expectedValue));
    }
}
