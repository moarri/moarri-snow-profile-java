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
package org.moarri.snowprofile.iacs;

import org.moarri.snowprofile.caaml.baseenum.CodeableEnum;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Kuba Radliński
 */
public enum IACSGrainShapeType implements CodeableEnum {

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
    
    public final static Set<IACSGrainShapeType> FIRST_LEVEL = EnumSet.of(PP,MM,DF,RG,FC,DH,SH,MF,IF);
    private final String code;
    private IACSGrainShapeType parent;
    private EnumSet<IACSGrainShapeType> children;
    
    @Override
    public String getCode() {
        return code;
    }

    public IACSGrainShapeType getParent() {
        return parent;
    }

    public EnumSet<IACSGrainShapeType> getChildren() {
        return children;
    }

    static{
        PP.children = EnumSet.of(PP_CO,PP_ND,PP_PL,PP_SD,PP_IR,PP_GP,PP_HL,PP_IP,PP_RM);
        MM.children = EnumSet.of(MM_CI,MM_RP);
        DF.children = EnumSet.of(DF_BK,DF_DC);
        RG.children = EnumSet.of(RG_LG,RG_SR,RG_WP,RG_XF);
        FC.children = EnumSet.of(FC_SF,FC_SO,FC_XR);
        DH.children = EnumSet.of(DH_CH,DH_CP,DH_LA,DH_PR,DH_XR);
        SH.children = EnumSet.of(SH_CV,SH_SU,SH_XR);
        MF.children = EnumSet.of(MF_CL,MF_CR,MF_PC,MF_SL);
        IF.children = EnumSet.of(IF_BI,IF_IC,IF_IL,IF_RC,IF_SC);        
    }
    
    
    IACSGrainShapeType(String code) {
        this.code = code;
    }


    
    IACSGrainShapeType(String code, IACSGrainShapeType parent) {
        this.code = code;
        this.parent = parent;
    }

    public static Set<String> validCodes() {
        Set<String> result = new HashSet<>();
        for (IACSGrainShapeType sgt : IACSGrainShapeType.values()) {
            result.add(sgt.code);
        }
        return result;
    }



}
