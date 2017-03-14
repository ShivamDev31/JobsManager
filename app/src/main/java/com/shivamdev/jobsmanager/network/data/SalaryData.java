package com.shivamdev.jobsmanager.network.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by shivam on 13/3/17.
 */

@Parcel
public class SalaryData {

    @SerializedName("salary_min")
    public String minSalary;

    @SerializedName("salary_max")
    public String maxSalary;

    @SerializedName("predicted_range")
    public String predictedRange;

}