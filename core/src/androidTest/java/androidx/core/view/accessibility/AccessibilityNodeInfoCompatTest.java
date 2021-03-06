/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.core.view.accessibility;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class AccessibilityNodeInfoCompatTest {
    @Test
    public void testSetCollectionInfoIsNullable() throws Exception {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = obtainedWrappedNodeCompat();
        accessibilityNodeInfoCompat.setCollectionInfo(null);
    }

    @Test
    public void testSetCollectionItemInfoIsNullable() throws Exception {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = obtainedWrappedNodeCompat();
        accessibilityNodeInfoCompat.setCollectionItemInfo(null);
    }

    @Test
    public void testGetSetHintText() {
        final CharSequence hintText = (Build.VERSION.SDK_INT >= 19) ? "hint text" : null;
        AccessibilityNodeInfoCompat nodeCompat = obtainedWrappedNodeCompat();
        nodeCompat.setHintText(hintText);
        assertThat(nodeCompat.getHintText(), equalTo(hintText));
    }

    @Test
    public void testGetSetPaneTitle() {
        final CharSequence paneTitle = (Build.VERSION.SDK_INT >= 19) ? "pane title" : null;
        AccessibilityNodeInfoCompat nodeCompat = obtainedWrappedNodeCompat();
        nodeCompat.setPaneTitle(paneTitle);
        assertThat(nodeCompat.getPaneTitle(), equalTo(paneTitle));
    }

    @Test
    public void testGetSetTooltipText() {
        final CharSequence tooltipText = (Build.VERSION.SDK_INT >= 19) ? "tooltip" : null;
        AccessibilityNodeInfoCompat nodeCompat = obtainedWrappedNodeCompat();
        nodeCompat.setTooltipText(tooltipText);
        assertThat(nodeCompat.getTooltipText(), equalTo(tooltipText));
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test
    public void testGetSetShowingHintText() {
        AccessibilityNodeInfoCompat nodeCompat = obtainedWrappedNodeCompat();
        nodeCompat.setShowingHintText(true);
        assertThat(nodeCompat.isShowingHintText(), is(true));
        nodeCompat.setShowingHintText(false);
        assertThat(nodeCompat.isShowingHintText(), is(false));
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test
    public void testGetSetScreenReaderFocusable() {
        AccessibilityNodeInfoCompat nodeCompat = obtainedWrappedNodeCompat();
        nodeCompat.setScreenReaderFocusable(true);
        assertThat(nodeCompat.isScreenReaderFocusable(), is(true));
        nodeCompat.setScreenReaderFocusable(false);
        assertThat(nodeCompat.isScreenReaderFocusable(), is(false));
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test
    public void testGetSetHeading() {
        AccessibilityNodeInfoCompat nodeCompat = obtainedWrappedNodeCompat();
        nodeCompat.setHeading(true);
        assertThat(nodeCompat.isHeading(), is(true));
        nodeCompat.setHeading(false);
        assertThat(nodeCompat.isHeading(), is(false));
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo =
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, 0, 1, true);
        nodeCompat.setCollectionItemInfo(collectionItemInfo);
        assertThat(nodeCompat.isHeading(), is(true));
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test
    public void testAccessibilityActionsNotNull() {
        try {
            AccessibilityActionCompat actionCompat;
            actionCompat = AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionShowOnScreen)));
            actionCompat = AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionScrollToPosition)));
            actionCompat = AccessibilityActionCompat.ACTION_SCROLL_UP;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionScrollUp)));
            actionCompat = AccessibilityActionCompat.ACTION_SCROLL_LEFT;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionScrollLeft)));
            actionCompat = AccessibilityActionCompat.ACTION_SCROLL_DOWN;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionScrollDown)));
            actionCompat = AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionScrollRight)));
            actionCompat = AccessibilityActionCompat.ACTION_CONTEXT_CLICK;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionContextClick)));
            actionCompat = AccessibilityActionCompat.ACTION_SET_PROGRESS;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionSetProgress)));
            actionCompat = AccessibilityActionCompat.ACTION_MOVE_WINDOW;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionMoveWindow)));
            actionCompat = AccessibilityActionCompat.ACTION_SHOW_TOOLTIP;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionShowTooltip)));
            actionCompat = AccessibilityActionCompat.ACTION_HIDE_TOOLTIP;
            assertThat(actionCompat.getId(),
                    is(getExpectedActionId(android.R.id.accessibilityActionHideTooltip)));
        } catch (NullPointerException e) {
            Assert.fail("Expected no NullPointerException, but got: " + e.getMessage());
        }
    }

    private AccessibilityNodeInfoCompat obtainedWrappedNodeCompat() {
        AccessibilityNodeInfo accessibilityNodeInfo = AccessibilityNodeInfo.obtain();
        return AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
    }

    private int getExpectedActionId(int id) {
        return Build.VERSION.SDK_INT >= 21 ? id : 0;
    }
}
