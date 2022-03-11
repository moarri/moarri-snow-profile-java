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

package org.moarri.snowprofile.caaml.engine;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Optional;

import static java.util.Optional.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

class NodeUtils {
    static Optional<Node> findChildNode(final Node parentNode, final String nodeName) {
        final NodeList children = parentNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node node = children.item(i);
            if (node instanceof Element && node.getNodeName().equals(nodeName)){
                return of(node);
            }
        }
        return empty();
    }

    static Node findMandatoryNode(final Node node, final String nodeName) throws MandatoryFieldException{
        return findChildNode(node, nodeName)
                .orElseThrow(() -> new MandatoryFieldException(CaamlMeta.SnowProfileType.CHILD_SNOW_PROFILE_RESULTS_OF));
    }

    static Optional<String> parseTextNode(final Node parentNode, final String nodeName){
        return findChildNode(parentNode, nodeName)
                .flatMap(NodeUtils::extractText);
    }

    static Optional<String> extractText(final Node node){
        return ofNullable(isNotEmpty(node.getTextContent()) ? node.getTextContent().trim() : null);
    }
}
