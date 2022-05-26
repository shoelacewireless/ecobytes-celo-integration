package com.shoelacewireless.ecobytessdk.backend.response;

import com.google.gson.annotations.SerializedName;

public class TestCeloBalanceResponse extends BaseResponse{

    @SerializedName("celoBalance") private int mCeloBalance;
    @SerializedName("cUSDBalance") private int mcUSDBalance;
    @SerializedName("EBSCommunity") private int mEBSCommunity;

    public int getCeloBalance() {
        return mCeloBalance;
    }
    public int getcUSDBalance() {
        return mcUSDBalance;
    }
    public int getEBSCommunity() {return mEBSCommunity; }

}
