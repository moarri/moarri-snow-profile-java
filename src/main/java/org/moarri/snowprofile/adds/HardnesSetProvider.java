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

import org.moarri.snowprofile.caaml.profile.HardnessType;

import static org.moarri.snowprofile.caaml.profile.HardnessType.F;
import static org.moarri.snowprofile.caaml.profile.HardnessType.FF4;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F4;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F4F1;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F1;
import static org.moarri.snowprofile.caaml.profile.HardnessType.F1P;
import static org.moarri.snowprofile.caaml.profile.HardnessType.P;
import static org.moarri.snowprofile.caaml.profile.HardnessType.PK;
import static org.moarri.snowprofile.caaml.profile.HardnessType.K;
import static org.moarri.snowprofile.caaml.profile.HardnessType.KI;
import static org.moarri.snowprofile.caaml.profile.HardnessType.I;

import java.util.EnumSet;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class HardnesSetProvider {
    public final static EnumSet<HardnessType> MAIN_ELEMENTS = EnumSet.of(F, F4, F1, P, K, I);
    public final static EnumSet<HardnessType> MAIN_MIDDLE_ELEMENTS = EnumSet.of(F, FF4, F4, F4F1, F1, F1P, P, PK, K, KI, I);
}
