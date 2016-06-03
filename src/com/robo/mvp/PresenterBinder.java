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

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Intended for binding presenter to view at runtime.
 *
 * @author robo-admin
 */
final class PresenterBinder {

    private PresenterBuilder mPresenterBuilder;
    private Map<View, Presenter> mViewPresenters;

    public PresenterBinder(PresenterBuilder presenterBuilder) {
        mPresenterBuilder = presenterBuilder;
        mViewPresenters = new WeakHashMap<>();
    }

    @SuppressWarnings("unchecked")
    boolean bindPresenter(View view, Class<? extends Presenter> presenterType) {
        if (!mViewPresenters.containsKey(view)) {
            @SuppressWarnings("rawtypes")
            Presenter presenter = mPresenterBuilder.buildPresenter(presenterType);
            mViewPresenters.put(view, presenter);
            presenter.setView(view);
            return true;
        }
        return false;
    }

    boolean unbindPresenter(View view) {
        if (mViewPresenters.containsKey(view)) {
            destroyPresenter(mViewPresenters.remove(view));
            return true;
        }
        return false;
    }

    private void destroyPresenter(Presenter presenter) {
        presenter.destroy();
        presenter.setView(null);
        presenter.getMessageBus().unsubscribeAll();
        presenter.setMessageBus(null);
        presenter.setApplicationController(null);
    }
}
