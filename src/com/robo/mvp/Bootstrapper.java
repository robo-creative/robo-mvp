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

import com.robo.messaging.MessageBus;
import com.robo.messaging.MessageBusImp;
import com.robo.navigation.ApplicationController;
import com.robo.navigation.ApplicationControllerImp;
import com.robo.navigation.SimpleCommandContainer;

/**
 * Intended for starting Robo MVP engine.
 *
 * @author robo-admin
 *
 */
public class Bootstrapper {

    /**
     * Starts Robo MVP engine.
     */
    public void start() {
        ViewHost.setPresenterBinder(new PresenterBinder(new PresenterBuilder(createPresenterFactory(),
                createMessageBus(), createApplicationController())));
        ViewHost.setPresenterMappingCollector(createPresenterMappingCollector());
    }

    /**
     * Creates and returns an instance of
     * {@link com.robo.navigation.ApplicationController ApplicationController}.
     * Subclass can override this method to return a different implementation.
     */
    protected ApplicationController createApplicationController() {
        return new ApplicationControllerImp(new SimpleCommandContainer());
    }

    /**
     * Creates and returns an instance of {@link com.robo.messaging.MessageBus
     * MessageBus}. Subclass can override this method to return a
     * different implementation.
     */
    protected MessageBus createMessageBus() {
        return new MessageBusImp();
    }

    /**
     * Creates and returns an instance of {@link PresenterFactory}. Subclass can
     * override this method to return a different implementation.
     */
    protected PresenterFactory createPresenterFactory() {
        return new PresenterFactoryImp();
    }

    /**
     * Creates and returns an instance of {@link PresenterMappingCollector}.
     * Subclass can override this method to return a different implementation.
     */
    protected PresenterMappingCollector createPresenterMappingCollector() {
        return new PresenterMappingCollectorImp();
    }
}
