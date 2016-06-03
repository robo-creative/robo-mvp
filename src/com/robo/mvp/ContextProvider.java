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

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Provides access to {@link android.content.Context Context},
 * {@link android.content.SharedPreferences SharedPreferences} and
 * {@link android.content.ContentResolver ContentResolver}.
 * 
 * @author robo-admin
 * 
 */
public interface ContextProvider {
	/**
	 * Gets the preferences that is shared among context objects.
	 */
	SharedPreferences getSharedPreferences(String name, int mode);

	/**
	 * Return a ContentResolver instance for your application's package.
	 * 
	 * @return The content resolver.
	 */
	ContentResolver getContentResolver();

	/**
	 * Returns the context of this view.
	 * 
	 * @return The context
	 */
	Context getContext();
}
