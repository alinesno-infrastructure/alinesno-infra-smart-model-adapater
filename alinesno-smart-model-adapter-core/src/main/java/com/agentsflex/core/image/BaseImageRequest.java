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
package com.agentsflex.core.image;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Data
public class BaseImageRequest {

    private String model;
    private Integer n;
    private String responseFormat;
    private String user;
    private Integer width;
    private Integer height;
    private Map<String, Object> options;

    public void setModel(String model) {
        this.model = model;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setSize(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public String getSize() {
        if (this.width == null || this.height == null) {
            return null;
        }
        return this.width + "x" + this.height;
    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    public void addOption(String key, Object value) {
        if (this.options == null) {
            this.options = new HashMap<>();
        }
        this.options.put(key, value);
    }

    public Object getOption(String key) {
        return this.options == null ? null : this.options.get(key);
    }

    public Object getOptionOrDefault(String key, Object defaultValue) {
        return this.options == null ? defaultValue : this.options.getOrDefault(key, defaultValue);
    }
}
