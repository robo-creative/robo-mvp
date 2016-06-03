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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * A container of view's event listeners.
 * 
 * @author robo-admin
 *
 */
public class Listeners {

	private Map<Type, Object> mListeners;

	public Listeners() {
		mListeners = new HashMap<>();
	}

	public <TListener> void set(Class<? extends TListener> contract, TListener listener) {
		if (!mListeners.containsKey(contract)) {
			mListeners.put(contract, listener);
		}
	}

	public boolean has(Class<?> contract) {
		return mListeners.containsKey(contract);
	}

	@SuppressWarnings("unchecked")
	public <TListener> TListener get(Class<? extends TListener> contract) {
		return (TListener) mListeners.get(contract);
	}

	public void flush() {
		mListeners.clear();
	}
}
