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

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * The base class for implementing {@link android.app.ExpandableListActivity
 * ExpandableListActivity}.
 * 
 * @author robo-admin
 * 
 */
public abstract class AbstractExpandableListActivity<TParent, TChild> extends ExpandableListActivity
		implements ExpandableListView<TParent, TChild>, LoaderManagerProvider, ContextProvider {

	protected ExpandableListView.ViewModel<TParent, TChild> mModel;
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
	public void setModel(ExpandableListView.ViewModel<TParent, TChild> model) {
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
	protected void onModelSet(ExpandableListView.ViewModel<TParent, TChild> model) {

	}

	@Override
	public final Context getContext() {
		return this;
	}

	@Override
	public boolean onChildClick(android.widget.ExpandableListView parent, View v, int groupPosition, int childPosition,
			long id) {
		if (mListeners.has(OnChildClickListener.class)) {
			return mListeners.get(OnChildClickListener.class).onChildClick(groupPosition, childPosition, id);
		}
		return super.onChildClick(parent, v, groupPosition, childPosition, id);
	}

	@Override
	public void onGroupCollapse(int groupPosition) {
		if (mListeners.has(OnGroupCollapseExpandListener.class)) {
			mListeners.get(OnGroupCollapseExpandListener.class).onGroupCollapse(groupPosition);
		}
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		if (mListeners.has(OnGroupCollapseExpandListener.class)) {
			mListeners.get(OnGroupCollapseExpandListener.class).onGroupExpand(groupPosition);
		}
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
