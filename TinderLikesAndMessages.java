package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


@RunWith(AndroidJUnit4.class)
public class TinderLikesAndMessages {

    private static final String TINDER_APP = "com.tinder";
    private static final int LAUNCH_TIMEOUT = 500;
    private int swipeN = 0;
    private UiDevice mDevice;

    @Before
    public void launchTheApp() {
        Context context = getInstrumentation().getContext();

        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(TINDER_APP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        mDevice = UiDevice.getInstance(getInstrumentation());
        mDevice.wait(Until.hasObject(By.pkg(TINDER_APP).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void autoLike() {
        boolean askPremium = true;
//      free swipe amount per 12h - 100
        while (askPremium) {
            mDevice = UiDevice.getInstance(getInstrumentation());

            if (mDevice.hasObject(By.res(TINDER_APP, "ad_icon"))) {
                swipeLeft();
            } else if (mDevice.hasObject(By.res(TINDER_APP, "recs_card_user_headline_name").text(""))) {
                swipeLeft();
            } else if (mDevice.hasObject(By.res(TINDER_APP, "recs_card_user_headline_name"))) {
                swipeRight();
            } else if (mDevice.hasObject(By.res(TINDER_APP, "its_match_keep_dismiss_button"))) {
                mDevice.findObject(By.res(TINDER_APP, "its_match_keep_dismiss_button"))
                        .click();
            } else if (mDevice.hasObject(By.res(TINDER_APP, "secret_admirer_skip"))) {
                mDevice.findObject(By.res(TINDER_APP, "secret_admirer_skip"))
                        .click();
            } else if (mDevice.hasObject(By.res(TINDER_APP, "secret_admirer_upsell_dismiss_button"))) {
                mDevice.findObject(By.res(TINDER_APP, "secret_admirer_upsell_dismiss_button"))
                        .click();
            } else if (mDevice.hasObject(By.res(TINDER_APP, "subscribe_button"))) {
                askPremium = !askPremium;
            }
            swipeN++;
        }
        Log.i("TOTAL:", String.valueOf(swipeN));
    }

//    @Test
//    public void massMessage() {
//        String message = "Hello there, how are u doing";
//        mDevice = UiDevice.getInstance(getInstrumentation());
//
//        try {
//            mDevice.findObject(new UiSelector().index(2)
//                    .className("android.widget.LinearLayout")).clickAndWaitForNewWindow();
//        } catch (UiObjectNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        UiObject2 view = mDevice.findObject(By.res(TINDER_APP, "newMatchesRecyclerView"));
//
//        for (int i = 1; i < 10; i++) {
//            try {
//                mDevice.findObject(new UiSelector()
//                        .index(i)
//                        .className("android.widget.RelativeLayout")).clickAndWaitForNewWindow();
//
//                if (i % 4 == 0) {
//                    mDevice.swipe(view.getVisibleBounds().centerX(), view.getVisibleBounds().centerY(),
//                            view.getVisibleBounds().left, view.getVisibleBounds().centerY(), 12);
//                }
//
//                mDevice.findObject(By.res(TINDER_APP, "textMessageInput")).setText(message);
////                mDevice.findObject(By.res(TINDER_APP, "textMessageSendButton")).click();
//            } catch (UiObjectNotFoundException e) {
//                break;
//            }
//            mDevice.pressBack();
//        }
//    }

    @After
    public void quit(){
        getInstrumentation().getUiAutomation().executeShellCommand("am force-stop " + TINDER_APP);
    }

    public int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public void swipeLeft(){
        mDevice.swipe(getScreenWidth() / 2, getScreenHeight() / 2,
                0, getScreenHeight() / 2, 12);
    }

    public void swipeRight(){
        mDevice.swipe(getScreenWidth() / 2, getScreenHeight() / 2,
                getScreenWidth(), getScreenHeight() / 2, 10);
    }

}
