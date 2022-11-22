/*
 * Copyright (c) 2022 Moarri Project
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

package org.moarri.snowprofile.caaml.engine.parser.nodes;

import org.moarri.snowprofile.caaml.engine.meta.Meta;

import java.text.MessageFormat;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class MandatoryFieldException extends CaamlException {
    private static final String MSG="Field {0} is mandatory";

    public MandatoryFieldException(final Meta nodeMeta) {
        super(MessageFormat.format(MSG, nodeMeta.value()));
    }
}
