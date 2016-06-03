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

import com.robo.navigation.ApplicationController;

/**
 * The base class for implementing a {@link com.robo.mvp.Presenter Presenter}
 * 
 * @author robo-admin
 * 
 */
public abstract class AbstractPresenter<TView extends View> implements Presenter<TView> {

	protected TView mView;

	protected MessageBusCoordinator mMessageBus;
	protected ApplicationController mAppController;

	@Override
	public final MessageBusCoordinator getMessageBus() {
		return mMessageBus;
	}

	@Override
	public final void setMessageBus(MessageBusCoordinator messageBus) {
		mMessageBus = messageBus;
	}

	@Override
	public void setView(TView view) {
		mView = view;
		if (null != view)
			onViewSet(view);
	}

	@Override
	public final TView getView() {
		return mView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.robo.mvp.Presenter#getApplicationController()
	 */
	@Override
	public final ApplicationController getApplicationController() {
		return mAppController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.robo.mvp.Presenter#setApplicationController(com.robo
	 * .ApplicationController)
	 */
	@Override
	public void setApplicationController(ApplicationController appController) {
		mAppController = appController;
	}

	/**
	 * This method is called when the presenter is being destroyed. Subclasses
	 * can override this method in order to release presenter's resources to
	 * avoid memory leak.
	 */
	@Override
	public void destroy() {
		
	}

	/**
	 * When overwritten in subclass, registers view event handlers and so on
	 * right after the view is set.
	 * 
	 * @param view
	 *            The view.
	 */
	protected abstract void onViewSet(TView view);
}
