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

import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;
import org.moarri.snowprofile.caaml.baseenum.EnumValueProvider;
import org.moarri.snowprofile.caaml.profile.Uom;
import org.w3c.dom.Element;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */

public class CodeableEnumTranslator {
    public static final String ATTR_UOM = "uom";


    public static <E extends CodeableEnum> E fromDomElement(Class<E> enumClass, Element e) throws NullCodeValueException, NonExistingCodeException {
        final String s = isNotEmpty(e.getTextContent()) ? e.getTextContent().trim() : null;
        return EnumValueProvider.valueOfCode(enumClass, s);
    }

    public static <E extends CodeableEnum> E fromAttribute(Class<E> enumClass, Element e, String attributeName) throws NullCodeValueException, NonExistingCodeException, AttributeMissingException {
        final String s = AttributeProcessor.trimAttribute(e.getAttribute(attributeName));
        if (isEmpty(s)) {
            throw new AttributeMissingException(attributeName, e.getNodeName());
        }
        return EnumValueProvider.valueOfCode(enumClass, s);
    }

    public static <E extends CodeableEnum & Uom> E fromUomAttribute(Class<E> enumClass, Element e) throws NullCodeValueException, NonExistingCodeException, AttributeMissingException {
        return fromAttribute(enumClass, e, ATTR_UOM);
    }
}
