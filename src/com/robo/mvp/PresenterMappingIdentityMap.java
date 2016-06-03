/**
 * Copyright (c) 2016 Robo Creative - https://robo-creative.github.io.
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
package com.robo.mvp;

import java.util.HashMap;
import java.util.Map;

final class PresenterMappingIdentityMap {

	private Map<String, PresenterMapping> mMappings;

	public PresenterMappingIdentityMap() {
		mMappings = new HashMap<>();
	}

	public PresenterMapping get(String identity) {
		if (mMappings.containsKey(identity)) {
			return mMappings.get(identity);
		}
		return null;
	}

	public void put(String identity, PresenterMapping mapping) {
		if (!mMappings.containsKey(identity)) {
			mMappings.put(identity, mapping);
		}
	}
}
