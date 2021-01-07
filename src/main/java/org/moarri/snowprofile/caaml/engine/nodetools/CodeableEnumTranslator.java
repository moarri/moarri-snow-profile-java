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

package org.moarri.snowprofile.caaml.engine.nodetools;

import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;
import org.moarri.snowprofile.caaml.baseenum.EnumValueProvider;
import org.moarri.snowprofile.caaml.profile.Uom;
import org.w3c.dom.Element;

/**
 * @author Kuba Radliński <kuba at radlinski.eu >
 */

public class CodeableEnumTranslator {
    public static final String ATTR_UOM = "uom";


    public static <E extends CodeableEnum> E fromDomElement(Class<E> enumClass, Element e) throws NullCodeValueException, NonExistingCodeException {
        String s = e.getTextContent().trim();
        return EnumValueProvider.valueOfCode(enumClass, s);
    }

    public static <E extends CodeableEnum> E fromAttribute(Class<E> enumClass, Element e, String attributeName) throws NullCodeValueException, NonExistingCodeException, AttributeMissingException {
        String s = AttributeProcessor.trimAttribute(e.getAttribute(attributeName));
        if (s == null || s.isEmpty()) {
            throw new AttributeMissingException(attributeName);
        }
        return EnumValueProvider.valueOfCode(enumClass, s);
    }

    public static <E extends CodeableEnum & Uom> E fromUomAttribute(Class<E> enumClass, Element e) throws NullCodeValueException, NonExistingCodeException, AttributeMissingException {
        return fromAttribute(enumClass, e, ATTR_UOM);
    }


}
