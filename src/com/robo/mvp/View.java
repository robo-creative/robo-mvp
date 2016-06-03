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

import android.os.Bundle;

/**
 * Defines a view. The view can be used to display a strongly typed model.
 *
 * @param <TModel> Model type.
 * @author robo-admin
 */
public interface View<TModel> extends EventBag {

    /**
     * Sets model to this view.
     *
     * @param model The model.
     */
    void setModel(TModel model);

    /**
     * Listens to Ready event on this view.
     */
    interface OnReadyListener {

        /**
         * Called when the view is ready to use.
         */
        void onReady();
    }

    /**
     * Listens to persistent event on this view.
     */
    interface OnPersistRestoreListener {
        /**
         * Called when the view requests to save its state.
         *
         * @param viewStateBundle
         */
        void onPersistViewState(Bundle viewStateBundle);

        /**
         * Called when the view requests to restore itself from the previous
         * saved state.
         *
         * @param savedViewStateBundle
         */
        void onRestoreViewState(Bundle savedViewStateBundle);
    }
}