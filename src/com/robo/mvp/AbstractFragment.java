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

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * The base class for implementing {@link android.app.Fragment Fragment}.
 * 
 * @author robo-admin
 * 
 */
public abstract class AbstractFragment<TModel> extends Fragment implements View<TModel>, ContextProvider, LoaderManagerProvider {

	protected TModel mModel;
	protected Listeners mListeners;

	@Override
	public final Listeners getListeners() {
		return mListeners;
	}

    @Override
    public void setListeners(Listeners listeners) {
        mListeners = listeners;
    }

	@Override
	public void setModel(TModel model) {
		mModel = model;
		onModelSet(model);
	}

	/**
	 * Called after the model has been set to the view. Subclass can override
	 * this method to register event handlers or so on.
	 * 
	 * @param model
	 *            The model.
	 */
	protected void onModelSet(TModel model) {

	}

	@Override
	public SharedPreferences getSharedPreferences(String name, int mode) {
		return getActivity().getSharedPreferences(name, mode);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		notifyRestore(savedInstanceState);
		notifyReady();
	}

	@Override
	public final Context getContext() {
		return getActivity();
	}

	@Override
	public final ContentResolver getContentResolver() {
		return getActivity().getContentResolver();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ViewHost.attach(this);
	}
	
	@Override
	public void onDestroy() {
        ViewHost.detach(this);
        super.onDestroy();
	}

	@Override
	public final void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		notifyPersist(outState);
	}
	
	private void notifyReady() {
		if (mListeners.has(OnReadyListener.class)) {
			mListeners.get(OnReadyListener.class).onReady();
		}
	}

	private void notifyRestore(Bundle savedInstanceState) {
		if  (mListeners.has(OnPersistRestoreListener.class)){
			mListeners.get(OnPersistRestoreListener.class).onRestoreViewState(savedInstanceState);
		}
	}

	private void notifyPersist(Bundle outState) {
		if  (mListeners.has(OnPersistRestoreListener.class)){
			mListeners.get(OnPersistRestoreListener.class).onPersistViewState(outState);
		}
	}
}
