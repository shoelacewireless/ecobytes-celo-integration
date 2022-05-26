package com.shoelacewireless.ecobytessdk.util;

public class SDKServerOperation {
    private static final String TAG = "ServerOperation";

    private final boolean mIsSuccessful;
    private final SDKServerError mServerError;

    public SDKServerOperation(boolean isSuccessful, SDKServerError serverError) {
        mIsSuccessful = isSuccessful;
        mServerError = serverError;
    }

    public boolean isSuccessful() {
        return mIsSuccessful;
    }

    public SDKServerError getServerError() {
        return mServerError;
    }

    public static SDKServerOperation successfulOperation() {
        return new SDKServerOperation(true, null);
    }
}