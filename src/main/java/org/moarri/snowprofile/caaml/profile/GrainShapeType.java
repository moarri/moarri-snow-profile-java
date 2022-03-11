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

import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Kuba Radliński
 */
public enum GrainShapeType implements CodeableEnum<GrainShapeType> {

    PP("PP"),
    PP_CO("PPco", PP),
    PP_ND("PPnd", PP),
    PP_PL("PPpl", PP),
    PP_SD("PPsd", PP),
    PP_IR("PPir", PP),
    PP_GP("PPgp", PP),
    PP_HL("PPhl", PP),
    PP_IP("PPip", PP),
    PP_RM("PPrm", PP),
    MM("MM"),
    MM_RP("MMrp", MM),
    MM_CI("MMci", MM),
    DF("DF"),
    DF_DC("DFdc",DF),
    DF_BK("DFbk",DF),
    RG("RG"),
    RG_SR("RGsr",RG),
    RG_LG("RGlg",RG),
    RG_WP("RGwp",RG),
    RG_XF("RGxf",RG),
    FC("FC"),
    FC_SO("FCso",FC),
    FC_SF("FCsf",FC),
    FC_XR("FCxr",FC),
    DH("DH"),
    DH_CP("DHcp",DH),
    DH_PR("DHpr",DH),
    DH_CH("DHch",DH),
    DH_LA("DHla",DH),
    DH_XR("DHxr",DH),
    SH("SH"),
    SH_SU("SHsu",SH),
    SH_CV("SHcv",SH),
    SH_XR("SHxr",SH),
    MF("MF"),
    MF_CL("MFcl",MF),
    MF_PC("MFpc",MF),
    MF_SL("MFsl",MF),
    MF_CR("MFcr",MF),
    IF("IF"),
    IF_IL("IFil",IF),
    IF_IC("IFic",IF),
    IF_BI("IFbi",IF),
    IF_RC("IFrc",IF),
    IF_SC("IFsc",IF);
    
    public final static Set<GrainShapeType> FIRST_LEVEL = EnumSet.of(PP,MM,DF,RG,FC,DH,SH,MF,IF);
    private final String code;
    private final GrainShapeType parent;
    
    @Override
    public String getCode() {
        return code;
    }

    public GrainShapeType getParent() {
        return parent;
    }

    public Set<GrainShapeType> getChildren() {
        return Arrays.stream(values()).filter(gst -> gst.parent == this).collect(Collectors.toSet());
    }

    GrainShapeType(String code) {
        this.code = code;
        this.parent = null;
    }

    GrainShapeType(String code, GrainShapeType parent) {
        this.code = code;
        this.parent = parent;
    }

    public static Set<String> validCodes() {
        return Arrays.stream(values()).map(GrainShapeType::getCode).collect(Collectors.toSet());
    }
}
