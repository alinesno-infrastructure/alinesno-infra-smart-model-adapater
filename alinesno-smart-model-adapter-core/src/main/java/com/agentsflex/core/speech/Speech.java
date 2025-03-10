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
package com.agentsflex.core.speech;

import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.core.util.IOUtil;
import com.agentsflex.core.util.StringUtil;
import lombok.Getter;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;

@Getter
public class Speech {

    private String b64Json;
    private String url;
    private byte[] bytes;

    public static Speech ofUrl(String url) {
        Speech image = new Speech();
        image.setUrl(url);
        return image;
    }

    public static Speech ofBytes(byte[] bytes) {
        Speech image = new Speech();
        image.setBytes(bytes);
        return image;
    }

    public void setB64Json(String b64Json) {
        this.b64Json = b64Json;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] readBytes() {
        return bytes;
    }

    public void writeToFile(File file) {
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IllegalStateException("Can not mkdirs for path: " + file.getParentFile().getAbsolutePath());
        }
        if (this.bytes != null && this.bytes.length > 0) {
            IOUtil.writeBytes(this.bytes, file);
        } else if (this.b64Json != null) {
            byte[] bytes = Base64.getDecoder().decode(b64Json);
            IOUtil.writeBytes(bytes, file);
        } else if (StringUtil.hasText(this.url)) {
            byte[] bytes = new HttpClient().getBytes(this.url);
            IOUtil.writeBytes(bytes, file);
        }
    }

    @Override
    public String toString() {
        return "Speech{" +
            "b64Json='" + b64Json + '\'' +
            ", url='" + url + '\'' +
            ", bytes=" + Arrays.toString(bytes) +
            '}';
    }
}
