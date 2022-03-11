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
package org.moarri.snowprofile.caaml.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;

/**
 *
 * @author Kuba Radliński
 */
@Getter
@RequiredArgsConstructor
public enum HardnessType implements CodeableEnum {

    F_MINUS("F-"),
    F("F"),
    F_PLUS("F+"),
    FF4("F-4F"),
    F4_MINUS("4F-"),
    F4("4F"),
    F4_PLUS("4F+"),
    F4F1("4F-1F"),
    F1_MINUS("1F-"),
    F1("1F"),
    F1_PLUS("1F+"),
    F1P("1F-P"),
    P_MINUS("P-"),
    P("P"),
    P_PLUS("P+"),
    PK("P-K"),
    K_MINUS("K-"),
    K("K"),
    K_PLUS("K+"),
    KI("K-I"),
    I("I");

    private final String code;
 }
