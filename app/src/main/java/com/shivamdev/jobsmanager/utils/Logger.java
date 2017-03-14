package com.shivamdev.jobsmanager.utils;

import android.widget.Toast;

import com.shivamdev.jobsmanager.common.JobsApplication;

/**
 * Created by shivam on 13/3/17.
 */

public class Logger {

    public static void toast(String text) {
        Toast.makeText(JobsApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

}