package com.shoelacewireless.ecobytessdk.backend.request;

import com.google.gson.annotations.SerializedName;

public class TestCeloWalletRequest {

    @SerializedName("walletAddress") private String mWalletAddress;

    public TestCeloWalletRequest( String walletAddress) {
        mWalletAddress = walletAddress;
    }
    public String getWalletAddress() {
        return mWalletAddress;
    }

}
