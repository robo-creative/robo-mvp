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

/**
 * Encapsulates view-presenter type mapping information.
 * 
 * @author robo-admin
 *
 */
public final class PresenterMapping {
	/**
	 * Presenter type.
	 */
	public Class<? extends Presenter> presenterType;

	/**
	 * View type.
	 */
	public Class<? extends View> viewType;

	public PresenterMapping(Class<? extends Presenter> presenterType, Class<? extends View> viewType) {
		this.presenterType = presenterType;
		this.viewType = viewType;
	}

	@Override
	public boolean equals(Object o) {
		if (null == o || !(o instanceof PresenterMapping)) {
			return false;
		} else {
			PresenterMapping po = (PresenterMapping) o;
			return po.presenterType.equals(presenterType) && po.viewType.equals(viewType);
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		if (null != presenterType) {
			hashCode |= presenterType.hashCode();
		}
		if (null != viewType) {
			hashCode |= viewType.hashCode();
		}
		return hashCode ^ 0x7F;
	}
}
