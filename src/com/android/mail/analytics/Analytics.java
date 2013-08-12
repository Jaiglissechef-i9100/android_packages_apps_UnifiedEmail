/*******************************************************************************
 *      Copyright (C) 2013 Google Inc.
 *      Licensed to The Android Open Source Project.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *******************************************************************************/

package com.android.mail.analytics;

import android.app.Activity;

/**
 * Mail wrapper for analytics libraries. Libraries should implement {@link Tracker}, and app
 * configurations that want to enable analytics should call {@link #setTracker(Tracker)} as soon
 * as possible upon application start.
 * <p>
 * {@link #getInstance()} will always return an object, but if the app has not yet (or will not
 * ever) set its own tracker instance, method calls on that tracker will be stubbed out.
 *
 */
public final class Analytics {

    public static String EVENT_CATEGORY_ACTION = "ui_action";

    public static String EVENT_ACTION_MENU_ITEM = "menu_item";

    public static int CD_INDEX_ACCOUNT_TYPE = 1;

    public static int CD_INDEX_ACCOUNT_COUNT = 2;

    private static Tracker sInstance;

    private Analytics() {
    }

    public static Tracker getInstance() {
        synchronized (Analytics.class) {
            if (sInstance == null) {
                sInstance = new StubTracker();
            }
        }
        return sInstance;
    }

    public static void setTracker(Tracker t) {
        synchronized (Analytics.class) {
            sInstance = t;
        }
    }

    private static final class StubTracker implements Tracker {

        @Override
        public void activityStart(Activity a) {}

        @Override
        public void activityStop(Activity a) {}

        @Override
        public void sendEvent(String category, String action, String label, long value) {}

        @Override
        public void sendView(String view) {}

        @Override
        public void setCustomDimension(int index, String value) {}

        @Override
        public void setCustomMetric(int index, Long value) {}

        @Override
        public void debugDispatchNow() {}

    }

}
