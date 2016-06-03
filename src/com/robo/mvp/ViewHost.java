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

import com.robo.Guard;

/**
 * The view host object intended for populating/destroying presenter for attached/detached views.
 *
 * @author robo-admin
 */
public final class ViewHost {

    private static PresenterBinder sPresenterBinder;
    private static PresenterMappingCollector sPresenterMappingCollector;

    private ViewHost() {

    }

    /**
     * Sets presenter binder.
     *
     * @param binder The binder.
     */
    static void setPresenterBinder(PresenterBinder binder) {
        sPresenterBinder = binder;
    }

    /**
     * Sets presenter mapping collector.
     *
     * @param mappingCollector The mapping collector.
     */
    static void setPresenterMappingCollector(PresenterMappingCollector mappingCollector) {
        sPresenterMappingCollector = mappingCollector;
    }

    /**
     * Attaches a specified view to this host.
     *
     * @param view The view.
     */
    public static boolean attach(View view) {
        PresenterMapping mapping = sPresenterMappingCollector.collectPresenterMapping(view);
        Guard.isNotNull(mapping, MvpException.class, "Could not find presenter for " + view.getClass().getName());
        initViewListeners(view);
        if (!sPresenterBinder.bindPresenter(view, mapping.presenterType)) {
            releaseViewListeners(view);
            return false;
        }
        return true;
    }

    /**
     * Detaches a specified view from this host.
     *
     * @param view The view.
     */
    public static boolean detach(View view) {
        if (sPresenterBinder.unbindPresenter(view)) {
            releaseViewListeners(view);
            return true;
        }
        return false;
    }

    private static void initViewListeners(View view) {
        view.setListeners(new Listeners());
    }

    private static void releaseViewListeners(View view) {
        view.getListeners().flush();
        view.setListeners(null);
    }
}
