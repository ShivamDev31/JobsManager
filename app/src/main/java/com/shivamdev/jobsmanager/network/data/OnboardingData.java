package com.shivamdev.jobsmanager.network.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by shivam on 13/3/17.
 */

@Parcel
public class OnboardingData {

    @SerializedName("company_name")
    public String companyName;

    @SerializedName("manager_name")
    public String managerName;

    @SerializedName("role")
    public String role;

}