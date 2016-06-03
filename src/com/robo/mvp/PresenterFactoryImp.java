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

import com.robo.reflect.Factory;

/**
 * Default implementation of {@link PresenterFactory}, creates instance
 * of {@link Presenter} using reflection.
 *
 * @author robo-admin
 *
 */
class PresenterFactoryImp implements PresenterFactory {

    @Override
    public Presenter create(Class<? extends Presenter> type) {
        return Factory.createObject(type);
    }
}
