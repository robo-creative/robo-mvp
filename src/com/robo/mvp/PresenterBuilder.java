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

import com.robo.messaging.MessageBus;
import com.robo.navigation.ApplicationController;

/**
 * Provides a method for building presenters.
 * 
 * @author robo-admin
 *
 */
final class PresenterBuilder {

	private MessageBus mMessageBus;
	private ApplicationController mApplicationController;
	private PresenterFactory mPresenterFactory;

	public PresenterBuilder(PresenterFactory presenterFactory, MessageBus messageBus,
			ApplicationController applicationController) {
		mMessageBus = messageBus;
		mApplicationController = applicationController;
		mPresenterFactory = presenterFactory;
	}

	public Presenter buildPresenter(Class<? extends Presenter> presenterType) {
		Presenter presenter = mPresenterFactory.create(presenterType);
		presenter.setApplicationController(mApplicationController);
		presenter.setMessageBus(new MessageBusCoordinatorImp(mMessageBus));
		return presenter;
	}
}
