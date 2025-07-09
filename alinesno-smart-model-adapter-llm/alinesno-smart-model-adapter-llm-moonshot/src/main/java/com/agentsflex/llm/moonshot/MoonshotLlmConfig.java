/*
 *  Copyright (c) 2023-2025, alinesno-smart-model-adapter (fuhai999@gmail.com).
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
package com.agentsflex.llm.moonshot;

import com.agentsflex.core.llm.LlmConfig;

public class MoonshotLlmConfig extends LlmConfig {

	private static final String DEFAULT_MODEL = "moonshot-v1-8k";
	private static final String DEFAULT_ENDPOINT = "https://api.moonshot.cn";

	public MoonshotLlmConfig() {
		setEndpoint(DEFAULT_ENDPOINT);
		setModel(DEFAULT_MODEL);
	}

}
