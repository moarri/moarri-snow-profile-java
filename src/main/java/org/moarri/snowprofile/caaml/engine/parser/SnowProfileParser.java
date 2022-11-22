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
import org.moarri.snowprofile.caaml.engine.meta.SnowProfileMeta;
import org.moarri.snowprofile.caaml.engine.parser.nodes.AttributeMissingException;
import org.moarri.snowprofile.caaml.engine.parser.nodes.CaamlException;
import org.moarri.snowprofile.caaml.profile.LocRef;
import org.moarri.snowprofile.caaml.profile.MetaDataBaseType;
import org.moarri.snowprofile.caaml.profile.SnowProfileResultsOf;
import org.moarri.snowprofile.caaml.profile.SnowProfileType;
import org.moarri.snowprofile.caaml.profile.SrcRef;
import org.moarri.snowprofile.caaml.profile.TimeRef;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Optional;

import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Kuba Radliński <kuba at radlinski.eu>
 */

public class SnowProfileParser extends Parser<SnowProfileType> {

    public SnowProfileParser() {
        super(SnowProfileMeta.MAIN_NODE);
    }

    public SnowProfileType parseSnowProfile(final Element e) throws CaamlException {
        return parse(e);
    }

    @Override
    protected Optional<SnowProfileType> parseMainNode(final Element e) throws CaamlException {
        final String id = parseMandatoryAttribute(e, SnowProfileMeta.ATTR_GML_ID);
//        final TimeRef timeRef = parseTimeRef(e);
//        final SnowProfileResultsOf snowProfileResultsOf = parseSnowProfileResultsOf(e);
//        final SrcRef srcRef = parseSrcRef(e);
//        final LocRef locRef = parseLocRef(e);
        final TimeRef timeRef = null;
        final SnowProfileResultsOf snowProfileResultsOf = null;
        final SrcRef srcRef = null;
        final LocRef locRef = null;
        final MetaDataBaseType metaData = findChildNode(e, SnowProfileMeta.CHILD_META_DATA)
                .flatMap(n -> of(new MetaDataBaseType()))
                .orElse(null);
        final String application = parseTextNode(e, SnowProfileMeta.CHILD_APPLICATION).orElse(EMPTY);
        final String applicationVersion = parseTextNode(e, SnowProfileMeta.CHILD_APPLICATION_VERSION).orElse(EMPTY);
        return of(SnowProfileType.builder(id, timeRef, srcRef, locRef, snowProfileResultsOf)
                .withMetaData(metaData)
                .withApplicationInfo(application, applicationVersion)
                .build());
    }
}
