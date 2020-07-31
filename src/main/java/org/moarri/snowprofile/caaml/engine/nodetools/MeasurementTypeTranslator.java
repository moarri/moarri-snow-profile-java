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

import org.moarri.snowprofile.iacs.gml.NilReasonEnumeration;
import org.moarri.snowprofile.iacs.measurementtype.*;
import org.moarri.snowprofile.iacs.tools.CodeableEnum;
import org.moarri.snowprofile.iacs.unittype.Uom;
import org.w3c.dom.Element;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Kuba Radliński <kuba.radlinski at harpy.pl >
 */

public abstract class MeasurementTypeTranslator {

    private static <E extends DoubleOrNilReasonType> E fromDomElement(Class<E> measClass, Element e) throws NullCodeValueException, NonExistingCodeException, NegativeValueException, ValueOutsideRangeException, CaamlClassException {
        try {
            NilReasonEnumeration nilReasonEnumeration = CodeableEnumTranslator.fromDomElement(NilReasonEnumeration.class, e);
            return measClass.getConstructor(NilReasonEnumeration.class).newInstance(nilReasonEnumeration);
        } catch (NonExistingCodeException ex) {
            double initialValue = Double.valueOf(e.getTextContent().trim());
            try {
                return measClass.getConstructor(Double.class).newInstance(initialValue);
            } catch (InstantiationException  | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex1) {
                ex1.printStackTrace();
                throw new CaamlClassException();
            }
        } catch (InstantiationException  | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex2) {
            ex2.printStackTrace();
            throw new CaamlClassException();
        }
    }

    private static <T extends CodeableEnum & Uom, E extends DoubleOrNilReasonType & FixedUom<T>> E fromDomElement(Class<E> measClass, Class<T> uomClass, Element e) throws NullCodeValueException, NonExistingCodeException, NegativeValueException, ValueOutsideRangeException, CaamlClassException, AttributeMissingException, UomWrongUnitException {
        T uom = CodeableEnumTranslator.fromUomAttribute(uomClass, e);
        try {
            NilReasonEnumeration nilReasonEnumeration = CodeableEnumTranslator.fromDomElement(NilReasonEnumeration.class, e);
            return measClass.getConstructor(NilReasonEnumeration.class, Uom.class).newInstance(nilReasonEnumeration, uom);
        } catch (NonExistingCodeException ex) {
            double initialValue = Double.valueOf(e.getTextContent().trim());
            try {
                return measClass.getConstructor(Double.class, Uom.class).newInstance(initialValue, uom);
            } catch (InstantiationException  | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex1) {
                ex1.printStackTrace();
                throw new CaamlClassException();
            }
        } catch (InstantiationException  | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex2) {
            ex2.printStackTrace();
            throw new CaamlClassException();
        }

    }


}