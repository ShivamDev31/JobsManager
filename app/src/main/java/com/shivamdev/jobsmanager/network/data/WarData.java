package com.shivamdev.jobsmanager.network.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by shivam on 13/3/17.
 */

@Parcel
public class WarData {

    @SerializedName("wars")
    public List<String> warsList;
}