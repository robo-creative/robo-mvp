/**
 * Copyright (c) 2016 Robo Creative - https://robo-creative.github.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.robo.mvp;

import com.robo.navigation.ApplicationController;

/**
 * Defines a presenter that acts upon a view.
 *
 * @param TView View type.
 * @author robo-admin
 */
public interface Presenter<TView extends View> {

    /**
     * Sets view to this presenter.
     *
     * @param view The view.
     */
    void setView(TView view);

    /**
     * Gets the view of this presenter
     *
     * @return The view
     */
    TView getView();

    /**
     * Gets the message bus of this presenter.
     *
     * @return The message bus.
     */
    MessageBusCoordinator getMessageBus();

    /**
     * Sets the message bus for this presenter.
     *
     * @param messageBus The message bus.
     */
    void setMessageBus(MessageBusCoordinator messageBus);

    /**
     * Gets the application controller for use in navigating and controlling
     * application flow.
     *
     * @return The application controller.
     */
    ApplicationController getApplicationController();

    /**
     * Sets the application controller for use in navigating and
     * controlling application flow.
     *
     * @param appController
     */
    void setApplicationController(ApplicationController appController);

    /**
     * Destroys this presenter.
     */
    void destroy();
}