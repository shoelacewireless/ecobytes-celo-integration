package com.shoelacewireless.ecobytessdk.util;

import android.util.Log;

import com.shoelacewireless.ecobytessdk.R;

import java.util.Arrays;

public enum SDKServerError {
    ERROR_NO_COMMUNICATION("", R.string.backend_error_no_communication),
    ERROR_000("000", R.string.backend_error_000);

    private static final String TAG = "SDKServerError";

    public final String mErrorCode;
    public final int mMessageResId;

    SDKServerError(String code, int stringId) {
        mErrorCode = code;
        mMessageResId = stringId;
    }

    public static SDKServerError fromCode(String code) {
        return Arrays.stream(SDKServerError.values())
                .filter(serverError -> serverError.mErrorCode.equals(code))
                .findFirst()
                .orElseGet(() -> {
                    Log.e(TAG, SDKLoggerHelper.LOG_TAG_ECOBYTES_SDK_BACKEND + "Couldn't identify error code from server.");
                    return ERROR_000;
                });
    }
}