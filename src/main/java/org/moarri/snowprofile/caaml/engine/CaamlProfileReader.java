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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.moarri.snowprofile.caaml.engine.parser.SnowProfileParser;
import org.moarri.snowprofile.caaml.engine.parser.nodes.CodeableEnumTranslator;
import org.moarri.snowprofile.caaml.engine.parser.nodes.MeasurementTypeTranslator;
import org.moarri.snowprofile.caaml.engine.parser.nodes.CaamlException;
import org.moarri.snowprofile.caaml.engine.parser.CustomMetaDataParser;
import org.moarri.snowprofile.caaml.profile.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static javax.xml.parsers.DocumentBuilderFactory.newInstance;
import static org.moarri.snowprofile.caaml.engine.CaamlValidator.validateCaaml;
import static org.moarri.snowprofile.caaml.engine.ValidationResultType.VALIDATION_OK;

/**
 *
 * @author Kuba Radliński
 */
public class CaamlProfileReader {
    private final CustomMetaDataParser snowProfileMetaDataParser;
    private final SnowProfileParser snowProfileParser;

    public CaamlProfileReader() {
        snowProfileMetaDataParser = null;
        snowProfileParser = new SnowProfileParser();
    }

    public Optional<SnowProfileType> readCaamlString(final String s) throws CaamlException {
        final ValidationResult validationResult = validateCaaml(s);
        if(validationResult.getResultType() != VALIDATION_OK){
            throw new ValidationException(validationResult);
        }
        try(InputStream is = new ByteArrayInputStream(s.getBytes())){
           return of(readCaamlStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empty();
    }


    private SnowProfileType readCaamlStream(final InputStream is) throws CaamlException  {
        final DocumentBuilderFactory factory = newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        factory.setIgnoringComments(true);
        try {
            final Document doc = factory.newDocumentBuilder().parse(is);
            removeWhitespace(doc.getDocumentElement());
            return snowProfileParser.parseSnowProfile(doc.getDocumentElement());
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new XmlProcessingException(ex);
        }
    }

//    private SnowProfileResultsOf parseSnowProfileResultsOf(final Node parentNode) throws CaamlException {
//        final Node node = findMandatoryNode(parentNode, CaamlMeta.SnowProfileType.CHILD_SNOW_PROFILE_RESULTS_OF);
//        if(!CaamlMeta.SnowProfileResultsOf.MAIN_NODE.equals(node.getNodeName())){
//            throw new WrongNodeException(CaamlMeta.SnowProfileResultsOf.MAIN_NODE, node.getNodeName());
//        }
//        return new SnowProfileResultsOf(parseSnowProfileMeasurements(node));
//    }
//
//    private TimeRef parseTimeRef(final Node parentNode) throws CaamlException {
//        Node timeRefNode = findMandatoryNode(parentNode, CaamlMeta.SnowProfileType.CHILD_TIME_REF);
//        return new TimeRef();
//    }
//
//    private SnowProfileMeasurementsType parseSnowProfileMeasurements(final Node parentNode) throws CaamlException {
//        final Node node = findMandatoryNode(parentNode, CaamlMeta.SnowProfileResultsOf.CHILD_SNOW_PROFILE_MEASUREMENTS);
//        final DirectionType dir = CodeableEnumTranslator.fromAttribute(DirectionType.class, (Element) node, CaamlMeta.SnowProfileMeasurementsType.ATTR_DIR);
//        if(dir != DirectionType.TOP_DOWN){
//            throw new WrongAttrValueException(CaamlMeta.SnowProfileMeasurementsType.ATTR_DIR, dir.getCode(), node.getNodeName(), DirectionType.TOP_DOWN.getCode());
//        }
//        return SnowProfileMeasurementsType.builder(dir)
//                .withProfileDepth(parseProfileDepth(node))
//                .build();
//    }
//
//    private MeasureLengthCmType parseProfileDepth(final Node parentNode) throws CaamlException {
//        final Optional<Node> node = findChildNode(parentNode, CaamlMeta.SnowProfileMeasurementsType.CHILD_PROFILE_DEPTH);
//        return node.isEmpty() ? null : MeasurementTypeTranslator.fromDomElement(MeasureLengthCmType.class, UomLengthType.class, (Element) node.get());
//    }
//
//    private SrcRef parseSrcRef(final Node parentNode) throws CaamlException {
//        final Node srcRefNode = findMandatoryNode(parentNode, CaamlMeta.SnowProfileType.CHILD_SRC_REF);
//        return new SrcRef();
//    }
//
//    private LocRef parseLocRef(final Node parentNode) throws CaamlException {
//        final Node locRefNode = findMandatoryNode(parentNode, CaamlMeta.SnowProfileType.CHILD_LOC_REF);
//        return new LocRef();
//    }
//
    public static void removeWhitespace(Element e) {
        NodeList children = e.getChildNodes();
        if (children.getLength() <= 1) {
            return ;
        }
        for (int i = children.getLength() - 1; i > -0; i--) {
            Node child = children.item(i);
            if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
                e.removeChild(child);
            } else if (child instanceof Element) {
                removeWhitespace((Element) child);
            }
        }
    }



// Utility methods



//    public static String trimAttribute(String attr) {
//        if (attr == null) {
//            return null;
//        }
//        return attr.trim();
//    }
//
//    public static double extractDouble(Element e) {
//        String s = e.getTextContent().trim();
//        double result = Double.parseDouble(s);
//        return result;
//    }
//
//
//    public static IACSAspectCardinalType extractAspectCardinal(Element e){
//        String s = e.getTextContent().trim();
//        return IACSAspectCardinalType.valueOfCode(s);
//    }
//
//
//    public static IACSDirectionType extractDirection(Element e){
//        String s = e.getTextContent().trim();
//        return IACSDirectionType.valueOfCode(s);
//    }
//
//    public static IACSGrainShapeType extractGrainShape(Element e){
//        String s = e.getTextContent().trim();
//        return IACSGrainShapeType.valueOfCode(s);
//    }
//
//    public static IACSGrainSizeType extractGrainSize(Element e){
//        String s = e.getTextContent().trim();
//        return IACSGrainSizeType.valueOfCode(s);
//    }
//
//    public static IACSHardnessType extractHardness(Element e){
//        String s = e.getTextContent().trim();
//        return IACSHardnessType.valueOfCode(s);
//    }
//
//    public static IACSLiquidWaterContentType extractLiquidWaterContent(Element e){
//        String s = e.getTextContent().trim();
//        return IACSLiquidWaterContentType.valueOfCode(s);
//    }
//
//    public static IACSPrecipTIType extractPrecipTI(Element e){
//        String s = e.getTextContent().trim();
//        return IACSPrecipTIType.valueOfCode(s);
//    }
//
//    public static IACSSkyConditionType extractSkyCondition(Element e){
//        String s = e.getTextContent().trim();
//        return IACSSkyConditionType.valueOfCode(s);
//    }
//
//    public static IACSSurfaceRoughnessType extractSurfaceRoughness(Element e){
//        String s = e.getTextContent().trim();
//        return IACSSurfaceRoughnessType.valueOfCode(s);
//    }
//
//
//    public static IACSWindSpdType extractWindSpd(Element e){
//        String s = e.getTextContent().trim();
//        return IACSWindSpdType.valueOfCode(s);
//    }
//
//    public static IACSUnitsAreaType  extractUnitsAreaAttribute(Element e) {
//        IACSUnitsAreaType uom = IACSUnitsAreaType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsDensityType  extractUnitsDensityAttribute(Element e) {
//        IACSUnitsDensityType uom = IACSUnitsDensityType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsForceType  extractUnitsForceAttribute(Element e) {
//        IACSUnitsForceType uom = IACSUnitsForceType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsInclineType  extractUnitsInclineAttribute(Element e) {
//        IACSUnitsInclineType uom = IACSUnitsInclineType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//
//
//    public static IACSUnitsLengthType extractUnitsLengthAttribute(Element e) {
//        IACSUnitsLengthType uom = IACSUnitsLengthType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsLengthType extractUnitsLengthAttribute(Element e, String attrName) {
//        IACSUnitsLengthType uom = IACSUnitsLengthType.valueOfCode(trimAttribute(e.getAttribute(attrName)));
//        return uom;
//    }
//
//    public static IACSUnitsLwcType  extractUnitsLwcAttribute(Element e) {
//        IACSUnitsLwcType uom = IACSUnitsLwcType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsPressureType  extractUnitsPressureAttribute(Element e) {
//        IACSUnitsPressureType uom = IACSUnitsPressureType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsSpecSurfAreaType  extractUnitsSpecSurfAreaAttribute(Element e) {
//        IACSUnitsSpecSurfAreaType uom = IACSUnitsSpecSurfAreaType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//
//    public static IACSUnitsTempType extractUnitsTempAttribute(Element e) {
//        IACSUnitsTempType uom = IACSUnitsTempType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//   public static IACSUnitsTempType extractUnitsTempAttribute(Element e,String attrName) {
//        IACSUnitsTempType uom = IACSUnitsTempType.valueOfCode(trimAttribute(e.getAttribute(attrName)));
//        return uom;
//    }
//
//    public static IACSUnitsUnitType  extractUnitsUnitAttribute(Element e) {
//        IACSUnitsUnitType uom = IACSUnitsUnitType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//    public static IACSUnitsWeightType  extractUnitsWeightAttribute(Element e) {
//        IACSUnitsWeightType uom = IACSUnitsWeightType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//
//    public static IACSUnitsWindSpdType extractUnitsWindSpdAttribute(Element e) {
//        IACSUnitsWindSpdType uom = IACSUnitsWindSpdType.valueOfCode(trimAttribute(e.getAttribute(UomMeta.ATTR_UOM)));
//        return uom;
//    }
//
//
//    private static CaamlAspect parseAspect(Element e){
//        NodeList mainChildren = e.getChildNodes();
//        Element mainElement = null;
//        for (int i = 0; i < mainChildren.getLength(); i++) {
//            Node actNode = mainChildren.item(i);
//            if ((actNode instanceof Element) && (AspectMeta.MAIN_NODE.equals(actNode.getNodeName()))) {
//                mainElement = (Element) actNode;
//                break;
//            }
//        }
//        if (mainElement == null) {
//            return null;
//        }
//        CaamlAspect aspect = new CaamlAspect();
//                NodeList children = mainElement.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if (AspectMeta.CHILD_POSITION.equals(nodeName)){
//                    aspect.setAspectPosition(extractAspectCardinal(childElement));
//                    break;
//                }
//            }
//        }
//        return aspect;
//    }
//
//    private static CaamlGrainSize parseGrainSize(Element e){
//        CaamlGrainSize grainSize = new CaamlGrainSize();
//        grainSize.setUom(extractUnitsLengthAttribute(e));
//        NodeList mainChildren = e.getChildNodes();
//        Element mainElement = null;
//        for (int i = 0; i < mainChildren.getLength(); i++) {
//            Node actNode = mainChildren.item(i);
//            if ((actNode instanceof Element) && (GrainSizeMeta.MAIN_NODE.equals(actNode.getNodeName()))) {
//                mainElement = (Element) actNode;
//                break;
//            }
//        }
//        if (mainElement == null) {
//            return grainSize;
//        }
//
//
//        NodeList children = mainElement.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                 Element childElement = (Element) node;
//                if (null != nodeName)switch (nodeName) {
//                    case GrainSizeMeta.CHILD_AVG:{
//                        CaamlGrainSizeComponent cmp = new CaamlGrainSizeComponent(extractDouble(childElement));
//                        grainSize.setAvg(cmp);
//                        break;
//                        }
//                    case GrainSizeMeta.CHILD_AVG_MAX:{
//                        CaamlGrainSizeComponent cmp = new CaamlGrainSizeComponent(extractDouble(childElement));
//                        grainSize.setAvgMax(cmp);
//                        break;
//                        }
//                }
//            }
//        }
//
//        return grainSize;
//    }
//
//    private static CaamlHardness parseHardness(Element e){
//        IACSUnitsForceType forceUnit = extractUnitsForceAttribute(e);
//        CaamlHardness hardness = new CaamlHardness();
//        hardness.setUom(forceUnit);
//        switch(forceUnit){
//            case N:
//                hardness.setHardnessType(CompositeValueType.NUMERIC);
//                hardness.setDoubleValue(extractDouble(e));
//                break;
//            case EMPTY:
//                hardness.setHardnessType(CompositeValueType.CARDINAL);
//                hardness.setCardinalValue(extractHardness(e));
//                break;
//        }
//        return hardness;
//    }
//
//
//
//    private static CaamlLwc parseLwc(Element e){
//        IACSUnitsLwcType lwcUnit = extractUnitsLwcAttribute(e);
//        CaamlLwc lwc = new CaamlLwc();
//        lwc.setUom(lwcUnit);
//        switch(lwcUnit){
//            case PRCVOL:
//                lwc.setLwcType(CompositeValueType.NUMERIC);
//                lwc.setDoubleValue(extractDouble(e));
//                break;
//            case EMPTY:
//                lwc.setLwcType(CompositeValueType.CARDINAL);
//                lwc.setCardinalValue(extractLiquidWaterContent(e));
//                break;
//        }
//        return lwc;
//    }
//
//    private static CaamlSnowHeightComponents parseSnowHeightComponent(Element e){
//        NodeList mainChildren = e.getChildNodes();
//        Element mainElement = null;
//        for (int i = 0; i < mainChildren.getLength(); i++) {
//            Node actNode = mainChildren.item(i);
//            if ((actNode instanceof Element) && (SnowHeightComponentsMeta.MAIN_NODE.equals(actNode.getNodeName()))) {
//                mainElement = (Element) actNode;
//                break;
//            }
//        }
//        if (mainElement == null) {
//            return null;
//        }
//        CaamlSnowHeightComponents components = new CaamlSnowHeightComponents();
//        boolean heightPresent = false;
//        boolean swePresent = false;
//        NodeList children = mainElement.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if (SnowHeightComponentsMeta.CHILD_SNOW_HEIGHT.equals(nodeName)){
//                    components.setSnowHeight(extractDouble(childElement));
//                    components.setSnowHeightUom(extractUnitsLengthAttribute(childElement));
//                    heightPresent = true;
//                } else if (SnowHeightComponentsMeta.CHILD_SWE.equals(nodeName)){
//                    components.setSwe(extractDouble(childElement));
//                    components.setSweUom(extractUnitsLengthAttribute(childElement));
//                    swePresent = true;
//                }
//            }
//
//        }
//        if(heightPresent&&swePresent){
//            components.setComponentsType(SnowHeightComponentsType.BOTH);
//        } else if(heightPresent){
//            components.setComponentsType(SnowHeightComponentsType.SNOW_HEIGHT);
//        } else if(swePresent){
//            components.setComponentsType(SnowHeightComponentsType.WATER_EQUIWALENT);
//        }
//        return components;
//    }
//
//    private static CaamlTempProfileObs parseTempProfileObs(Element e) {
//        CaamlTempProfileObs tempProfileObs = new CaamlTempProfileObs();
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if (TempProfileObsMeta.CHILD_DEPTH.equals(nodeName)) {
//                    tempProfileObs.setDepth(extractDouble(childElement));
//                } else if (TempProfileObsMeta.CHILD_SNOW_TEMP.equals(nodeName)) {
//                    tempProfileObs.setSnowTemp(extractDouble(childElement));
//                }
//            }
//        }
//        return tempProfileObs;
//    }
//
//
//    private static CaamlTempProfile parseTempProfile(Element e){
//        CaamlTempProfile tempProfile = new CaamlTempProfile();
//        tempProfile.setTempProfileUomDepth(extractUnitsLengthAttribute(e,TempProfileObsMeta.UOM_DEPTH));
//        tempProfile.setTempProfileUomTemp(extractUnitsTempAttribute(e, TempProfileObsMeta.UOM_TEMP));
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if (TempProfileObsMeta.MAIN_NODE.equals(nodeName)){
//                    CaamlTempProfileObs tempProfileObs = parseTempProfileObs(childElement);
//                    if(tempProfileObs!=null){
//                        tempProfile.getTempProfile().add(tempProfileObs);
//                        System.out.println("temp profile depth:"+tempProfileObs.getDepth()+" temp:"+tempProfileObs.getSnowTemp());
//                    }
//                }
//            }
//        }
//        return tempProfile;
//    }
//
//    private static CaamlLayer parseLayer(Element e){
//        CaamlLayer layer = new CaamlLayer();
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if (LayerMeta.CHILD_DEPTH_TOP.equals(nodeName)){
//                    layer.setDepthTop(extractDouble(childElement));
//                    layer.setDepthTopUom(extractUnitsLengthAttribute(childElement));
//                } else if (LayerMeta.CHILD_THICKNESS.equals(nodeName)){
//                    layer.setThickness(extractDouble(childElement));
//                    layer.setThicknessUom(extractUnitsLengthAttribute(childElement));
//                } else if (LayerMeta.CHILD_GRAIN_FORM_PRIMARY.equals(nodeName)){
//                    layer.setGrainFormPrimary(extractGrainShape(childElement));
//                } else if (LayerMeta.CHILD_GRAIN_FORM_SECONDARY.equals(nodeName)){
//                    layer.setGrainFormSecondary(extractGrainShape(childElement));
//                } else if  (LayerMeta.CHILD_GRAIN_SIZE.equals(nodeName)){
//                    CaamlGrainSize grainSize = parseGrainSize(childElement);
//                    if (grainSize != null){
//                        layer.setGrainSize(grainSize);
//                    }
//                } else if  (LayerMeta.CHILD_HARDNESS.equals(nodeName)){
//                    CaamlHardness hardness = parseHardness(childElement);
//                    if (hardness != null){
//                        layer.setHardness(hardness);
//                    }
//                } else if  (LayerMeta.CHILD_LWC.equals(nodeName)){
//                    CaamlLwc lwc = parseLwc(childElement);
//                    if (lwc != null){
//                        layer.setLwc(lwc);
//                    }
//                }
//
//            }
//        }
//        return layer;
//    }
//
//
//    private static List<CaamlLayer> parseStratProfile(Element e){
//        List<CaamlLayer> layers = new ArrayList<CaamlLayer>();
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if (LayerMeta.MAIN_NODE.equals(nodeName)){
//                    CaamlLayer layer = parseLayer(childElement);
//                    if (layer!=null){
//                        layers.add(layer);
//                        System.out.println("Layer, depth:"+layer.getDepthTop()+layer.getDepthTopUom()+" thickness:"+layer.getThickness()+layer.getThicknessUom()+
//                                " form primary: "+layer.getGrainFormPrimary()+" layer secondary:"+layer.getGrainFormSecondary()+
//                                " grain size avg:"+layer.getGrainSize().getAvg().getDoubleValue()+" grain size avgMax:"+layer.getGrainSize().getAvgMax().getDoubleValue()+
//                                " hardness:"+layer.getHardness().getHardnessType()+" "+layer.getHardness().getCardinalValue()+" "+layer.getHardness().getUom()+
//                                " lwc:"+layer.getLwc().getLwcType()+" "+layer.getLwc().getCardinalValue()+" "+layer.getLwc().getUom());
//                    }
//
//                }
//            }
//        }
//        return layers;
//
//    }
//
//    private static CaamlSnowProfileResults parseSnowProfileResults(Element e) {
//        if (!SnowProfileMeta.CHILD_SNOW_PROFILE_RESULTS_OF.equals(e.getNodeName())) {
//            return null;
//        }
//        CaamlSnowProfileResults snowProfileResults = new CaamlSnowProfileResults();
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                if (SnowProfileMeasurementsMeta.MAIN_NODE.equals(nodeName)) {
//                    CaamlSnowProfileMeasurements measurement = parseSnowProfileMeasurements((Element) node);
//                    if (measurement != null) {
//                        snowProfileResults.setSnowProfileMeasurements(measurement);
//                    }
//                }
//            }
//        }
//        return snowProfileResults;
//    }
//
//    private static CaamlSnowProfileMeasurements parseSnowProfileMeasurements(Element e) {
//        if (!SnowProfileMeasurementsMeta.MAIN_NODE.equals(e.getNodeName())) {
//            return null;
//        }
//        CaamlSnowProfileMeasurements measurement = new CaamlSnowProfileMeasurements();
//        IACSDirectionType direction = IACSDirectionType.valueOfCode(trimAttribute(e.getAttribute(SnowProfileMeasurementsMeta.ATTR_DIR)));
//        if (direction != null) {
//            measurement.setDirection(direction);
//        }
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                Element childElement = (Element) node;
//                if(SnowProfileMeasurementsMeta.CHILD_COMMENT.equals(nodeName)){
//                    measurement.setComment(childElement.getTextContent());
//                } else if(SnowProfileMeasurementsMeta.CHILD_PROFILE_DEPTH.equals(nodeName)){
//                    measurement.setProfileDepth(extractDouble(childElement));
//                    measurement.setProfileDepthUom(extractUnitsLengthAttribute(childElement));
//                } else if(SnowProfileMeasurementsMeta.CHILD_PENETRATION_RAM.equals(nodeName)){
//                    measurement.setPenetrationRam(extractDouble(childElement));
//                    measurement.setPenetrationRamUom(extractUnitsLengthAttribute(childElement));
//                } else if(SnowProfileMeasurementsMeta.CHILD_PENETRATION_FOOT.equals(nodeName)){
//                    measurement.setPenetrationFoot(extractDouble(childElement));
//                    measurement.setPenetrationFootUom(extractUnitsLengthAttribute(childElement));
//                }else if(SnowProfileMeasurementsMeta.CHILD_PENETRATION_SKI.equals(nodeName)){
//                    measurement.setPenetrationSki(extractDouble(childElement));
//                    measurement.setPenetrationSkiUom(extractUnitsLengthAttribute(childElement));
//                } else if(SnowProfileMeasurementsMeta.CHILD_AIR_TEMP_PRES.equals(nodeName)){
//                    measurement.setAirTempPres(extractDouble(childElement));
//                    measurement.setAirTempPresUom(extractUnitsTempAttribute(childElement));
//                } else if(SnowProfileMeasurementsMeta.CHILD_WIND_SPD.equals(nodeName)){
//                    measurement.setWindSpd(extractDouble(childElement));
//                    measurement.setWindSpdUom(extractUnitsWindSpdAttribute(childElement));
//                } else if(SnowProfileMeasurementsMeta.CHILD_WIND_DIR.equals(nodeName)){
//                    CaamlAspect aspect = parseAspect(childElement);
//                    if (aspect!=null){
//                        measurement.setWindDir(aspect);
//                    }
//                } else if(SnowProfileMeasurementsMeta.CHILD_HS.equals(nodeName)){
//                    CaamlSnowHeightComponents components = parseSnowHeightComponent(childElement);
//                    if (components!=null){
//                        measurement.setHS(components);
//                    }
//                } else if(SnowProfileMeasurementsMeta.CHILD_HIN.equals(nodeName)){
//                    CaamlSnowHeightComponents components = parseSnowHeightComponent(childElement);
//                    if (components!=null){
//                        measurement.setHIN(components);
//                    }
//                } else if(SnowProfileMeasurementsMeta.CHILD_HN24.equals(nodeName)){
//                    CaamlSnowHeightComponents components = parseSnowHeightComponent(childElement);
//                    if (components!=null){
//                        measurement.setHN24(components);
//                    }
//                }  else if(SnowProfileMeasurementsMeta.CHILD_SKY_COND.equals(nodeName)){
//                    measurement.setSkyCond(extractSkyCondition(childElement));
//                }  else if(SnowProfileMeasurementsMeta.CHILD_PRECIP_TI.equals(nodeName)){
//                    measurement.setPrecipTI(extractPrecipTI(childElement));
//                }   else if(SnowProfileMeasurementsMeta.CHILD_STRAT_PROFILE.equals(nodeName)){
//                    measurement.setStratProfile(parseStratProfile(childElement));
//                } else if(SnowProfileMeasurementsMeta.CHILD_TEMP_PROFILE.equals(nodeName)){
//                    measurement.setTempProfile(parseTempProfile(childElement));
//                }
//            }
//        }
//        return measurement;
//    }
//
//    private static CaamlSnowProfile parseSnowProfile(Element e) {
//        if (!SnowProfileMeta.MAIN_NODE.equals(e.getNodeName())) {
//            return null;
//        }
//        CaamlSnowProfile snowProfile = new CaamlSnowProfile();
//        snowProfile.setId(trimAttribute(e.getAttribute(SnowProfileMeta.ATTR_GML_ID)));
//        NodeList children = e.getChildNodes();
//        for (int i = 0; i < children.getLength(); i++) {
//            Node node = children.item(i);
//            if (node instanceof Element) {
//                String nodeName = node.getNodeName();
//                if (SnowProfileMeta.CHILD_META_DATA_PROPERTY.equals(nodeName)) {
//                    System.out.println("Skipping node:" + nodeName);
//                } else if (SnowProfileMeta.CHILD_SNOW_PROFILE_RESULTS_OF.equals(nodeName)) {
//                    CaamlSnowProfileResults results = parseSnowProfileResults((Element) node);
//                    if (results != null) {
//                        snowProfile.setSnowProfileResults(results);
//                    }
//                } else if (SnowProfileMeta.CHILD_VALID_TIME.equals(nodeName)) {
//                    System.out.println("Skipping node:" + nodeName);
//                } else if (SnowProfileMeta.CHILD_LOC_REF.equals(nodeName)) {
//                    System.out.println("Skipping node:" + nodeName);
//                }
//            }
//        }
//        return snowProfile;
//    }
    

}
