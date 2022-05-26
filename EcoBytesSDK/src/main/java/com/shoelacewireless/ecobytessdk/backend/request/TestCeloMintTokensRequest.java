package com.shoelacewireless.ecobytessdk.backend.request;

import com.google.gson.annotations.SerializedName;

public class TestCeloMintTokensRequest {

    public static final String ECOBYTES_GOLD_POINTS = "GOLD";
    public static final String ECOBYTES_SILVER_POINTS  = "SILVER";

    public static final String ECOBYTES_GOLD_TOKEN = "EBG";
    public static final String ECOBYTES_SILVER_TOKEN  = "EBS";
    public static final String ALFAJORES_TEST_CELO_TOKEN = "CELO";
    public static final String ALFAJORES_TEST_CUSD_TOKEN  = "cUSD";

    @SerializedName("amount") private int mAmount;
    @SerializedName("pointType") private String mPointType;
    @SerializedName("tokenType") private String mTokenType;

    public TestCeloMintTokensRequest(int amount, String pointType, String tokenType) {
        mAmount = amount;
        mPointType = pointType;
        mTokenType = tokenType;
    }

    public int getAmount() {
        return mAmount;
    }
    public String getPointType() {
        return mPointType;
    }
    public String getTokenType() {
        return mTokenType;
    }

}
