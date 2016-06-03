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

import com.robo.reflect.AnnotationUtils;

/**
 * Default implementation of {@link PresenterMappingCollector}, collects mapping
 * information using reflection.
 * 
 * @author robo-admin
 */
public class PresenterMappingCollectorImp implements PresenterMappingCollector {

	private PresenterMappingIdentityMap mIdentityMap;

	public PresenterMappingCollectorImp() {
		mIdentityMap = new PresenterMappingIdentityMap();
	}

	@Override
	public PresenterMapping collectPresenterMapping(View view) {
		String identity = view.getClass().getName();
		PresenterMapping mapping = mIdentityMap.get(identity);
		if (null == mapping) {
			mapping = collectUsingReflection(view.getClass());
			mIdentityMap.put(identity, mapping);
		}
		return mapping;
	}

	private PresenterMapping collectUsingReflection(Class<? extends View> viewType) {
		BindTo annotation = AnnotationUtils.getAnnotation(viewType, BindTo.class, true, new String[] { "com.robo.mvp" });
		if (null == annotation) {
			return null;
		}
		return new PresenterMapping(annotation.value(), viewType);
	}
}
