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

package org.moarri.snowprofile.adds;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

import org.moarri.snowprofile.caaml.engine.parser.nodes.NullCodeValueException;
import org.moarri.snowprofile.caaml.profile.HardnessType;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

import static org.moarri.snowprofile.caaml.profile.HardnessType.F_MINUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F_PLUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.FF4;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F4_MINUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F4;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F4_PLUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F4F1;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F1_MINUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F1;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F1_PLUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F1P;
import static org.moarri.snowprofile.caaml.profile.HardnessType.P_MINUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.P;
import static org.moarri.snowprofile.caaml.profile.HardnessType.P_PLUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.PK;
import static org.moarri.snowprofile.caaml.profile.HardnessType.K_MINUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.K;
import static org.moarri.snowprofile.caaml.profile.HardnessType.K_PLUS;
import static org.moarri.snowprofile.caaml.profile.HardnessType.KI;
import static org.moarri.snowprofile.caaml.profile.HardnessType.I;


public class HardnessToValueTranslator {
    private static final Map<HardnessType, Integer> VALUES_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(F_MINUS, 10),
            new AbstractMap.SimpleEntry<>(F, 20),
            new AbstractMap.SimpleEntry<>(F_PLUS, 35),
            new AbstractMap.SimpleEntry<>(FF4, 50),
            new AbstractMap.SimpleEntry<>(F4_MINUS, 75),
            new AbstractMap.SimpleEntry<>(F4, 100),
            new AbstractMap.SimpleEntry<>(F4_PLUS, 137),
            new AbstractMap.SimpleEntry<>(F4F1, 175),
            new AbstractMap.SimpleEntry<>(F1_MINUS, 210),
            new AbstractMap.SimpleEntry<>(F1, 250),
            new AbstractMap.SimpleEntry<>(F1_PLUS, 235),
            new AbstractMap.SimpleEntry<>(F1P, 390),
            new AbstractMap.SimpleEntry<>(P_MINUS, 445),
            new AbstractMap.SimpleEntry<>(P, 500),
            new AbstractMap.SimpleEntry<>(P_PLUS, 605),
            new AbstractMap.SimpleEntry<>(PK, 715),
            new AbstractMap.SimpleEntry<>(K_MINUS, 900),
            new AbstractMap.SimpleEntry<>(K, 1000),
            new AbstractMap.SimpleEntry<>(K_PLUS, 1100),
            new AbstractMap.SimpleEntry<>(KI, 1150),
            new AbstractMap.SimpleEntry<>(I, 1200));

    public static int translate(final HardnessType hardnessType) throws NullCodeValueException {
        if (Objects.isNull(hardnessType)) {
            throw new NullCodeValueException(HardnessType.class);
        }
        return VALUES_MAP.getOrDefault(hardnessType, 0);
    }
}
