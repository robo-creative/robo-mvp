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

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;

/**
 * Provides the base class for activities that display a list of items.
 * 
 * @param <TItem>
 *            Type of item.
 * 
 * @author robo-admin
 * 
 */
public abstract class AbstractListActivity<TItem> extends ListActivity
		implements ListView<TItem>, ContextProvider, LoaderManagerProvider {

	protected TItem[] mModel;
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
	public void setModel(TItem[] model) {
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
	protected void onModelSet(TItem[] model) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public TItem getItemAtPosition(int position) {
		return (TItem) getListAdapter().getItem(position);
	}

	@Override
	public long getItemIdAtPosition(int position) {
		return getListAdapter().getItemId(position);
	}

	@Override
	public final Context getContext() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ViewHost.attach(this);
		onInitLayout(savedInstanceState);
		notifyRestore(savedInstanceState);
		notifyReady();
	}

	/**
	 * This method is called in onCreate(), prior to dispatching Created event
	 * and after calling super.onCreate(). Subclasses must override this method
	 * in order to initialize layout information, for e.g setContentView(int),
	 * finding controls and registering controls' event getListeners.
	 */
	protected abstract void onInitLayout(Bundle savedInstanceState);

	@Override
	protected void onListItemClick(android.widget.ListView l, android.view.View v, int position, long id) {

		if (mListeners.has(OnListItemClickListener.class)) {
			mListeners.get(OnListItemClickListener.class).onListItemClick(getItemAtPosition(position), position, id);
		}
	}

	@Override
	protected final void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		notifyPersist(outState);
	}

	@Override
	protected void onDestroy() {
        ViewHost.detach(this);
		super.onDestroy();
	}

	private void notifyReady() {
		if (mListeners.has(OnReadyListener.class)) {
			mListeners.get(OnReadyListener.class).onReady();
		}
	}

	private void notifyRestore(Bundle savedInstanceState) {
		if (mListeners.has(OnPersistRestoreListener.class)) {
			mListeners.get(OnPersistRestoreListener.class).onRestoreViewState(savedInstanceState);
		}
	}

	private void notifyPersist(Bundle outState) {
		if (mListeners.has(OnPersistRestoreListener.class)) {
			mListeners.get(OnPersistRestoreListener.class).onPersistViewState(outState);
		}
	}
}
