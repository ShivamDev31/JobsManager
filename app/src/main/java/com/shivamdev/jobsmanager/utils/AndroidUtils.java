package com.shivamdev.jobsmanager.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by shivam on 13/3/17.
 */

public class AndroidUtils {

    private Context context;

    public AndroidUtils(Context context) {
        this.context = context;
    }

    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!(context instanceof Activity)) {
            return;
        }
        // check if no view has focus:
        View view = ((Activity) context).getCurrentFocus();
        if (view == null)
            return;
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}