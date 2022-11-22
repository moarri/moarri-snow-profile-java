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

package org.moarri.snowprofile.caaml.engine.parser;

import org.moarri.snowprofile.caaml.engine.WrongNodeException;
import org.moarri.snowprofile.caaml.engine.meta.Meta;
import org.moarri.snowprofile.caaml.engine.parser.nodes.AttributeMissingException;
import org.moarri.snowprofile.caaml.engine.parser.nodes.CaamlException;
import org.moarri.snowprofile.caaml.engine.parser.nodes.MandatoryFieldException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public abstract class Parser<T> {
    private final Meta mainNodeMeta;

    protected Parser(Meta mainNodeMeta) {
        this.mainNodeMeta = mainNodeMeta;
    }

    protected T parse(final Element e) throws CaamlException {
        checkMainNode(e, mainNodeMeta);
        return parseMainNode(e).orElse(null);
    }

    protected T parseFromParent(final Element node) throws CaamlException {
        Optional<Element> mainNode = findChildNode(node, mainNodeMeta);
        return mainNode.isPresent() ? parseMainNode(mainNode.get()).orElse(null): null;
    }

    protected T parseMandatoryFromParent(final Element node, final Meta mainNodeMeta) throws CaamlException {
        return parseMainNode(findMandatoryNode(node, mainNodeMeta)).orElseThrow(() -> new MandatoryFieldException(mainNodeMeta));
    }

    private void checkMainNode(final Element e, final Meta mainNodeMeta) throws WrongNodeException {
        if(!mainNodeMeta.value().equals(e.getNodeName())){
            throw new WrongNodeException(mainNodeMeta, e.getNodeName());
        }
    }

    protected abstract Optional<T> parseMainNode(final Element node) throws CaamlException;

    protected Optional<Element> findChildNode(final Element parentNode, final Meta nodeMeta) {
        final NodeList children = parentNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node node = children.item(i);
            if (node instanceof Element && node.getNodeName().equals(nodeMeta.value())){
                return of((Element)node);
            }
        }
        return empty();
    }

    protected Element findMandatoryNode(final Element node, final Meta nodeMeta) throws MandatoryFieldException {
        return findChildNode(node, nodeMeta)
                .orElseThrow(() -> new MandatoryFieldException(nodeMeta));
    }

    protected Optional<String> parseTextNode(final Element parentNode, final Meta nodeMeta){
        return findChildNode(parentNode, nodeMeta)
                .flatMap(this::extractText);
    }

    protected String parseMandatoryTextNode(final Element parentNode, final Meta nodeMeta) throws MandatoryFieldException {
        return extractText(findMandatoryNode(parentNode, nodeMeta)).orElse(null);
    }

    protected Optional<String> extractText(final Element node) {
        return ofNullable(isNotEmpty(node.getTextContent()) ? node.getTextContent().trim() : null);
    }

    protected Optional<String> parseAttribute(final Element e, final Meta attributeMeta) {
        final String attributeName = attributeMeta.value();
        return provideAttribute(e, attributeName);
    }

    protected String parseMandatoryAttribute(final Element e, final Meta attributeMeta) throws AttributeMissingException {
        final String attributeName = attributeMeta.value();
        return provideAttribute(e, attributeName).orElseThrow(() -> new AttributeMissingException(attributeName, e.getNodeName()));
    }

    private Optional<String> provideAttribute(final Element node, final String attributeName) {
        return node.hasAttribute(attributeName) ? of(node.getAttribute(attributeName)) : empty();
    }

}
