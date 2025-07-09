/*
 *  Copyright (c) 2023-2025, Agents-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agentsflex.core.document.loader;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.document.DocumentLoader;
import com.agentsflex.core.document.DocumentParser;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractDocumentLoader implements DocumentLoader {

    protected DocumentParser documentParser;

    public AbstractDocumentLoader(DocumentParser documentParser) {
        this.documentParser = documentParser;
    }

    @Override
    public Document load() {
        try (InputStream stream = loadInputStream()){
            return documentParser.parse(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract InputStream loadInputStream();

}
