package com.shoelacewireless.ecobytessdk.backend;

import android.util.Log;

import com.shoelacewireless.ecobytessdk.backend.response.BaseResponse;
import com.shoelacewireless.ecobytessdk.util.SDKLoggerHelper;
import com.shoelacewireless.ecobytessdk.util.SDKMutableSingleEvent;
import com.shoelacewireless.ecobytessdk.util.SDKServerError;
import com.shoelacewireless.ecobytessdk.util.SDKServerOperation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SDKCustomCallback<T extends BaseResponse> implements Callback<T> {
    private static final String TAG = "CustomCallback";

    private final SDKMutableSingleEvent<SDKServerOperation> mEvent;
    private final OnSuccessfulResponse<T> mCallback;

    public SDKCustomCallback(SDKMutableSingleEvent<SDKServerOperation> event, OnSuccessfulResponse<T> callback) {
        mEvent = event;
        mCallback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> res) {
        if (res.isSuccessful()) {
            if (mCallback != null) {
                mCallback.onSuccessfulResponse(res.body());
            }
            mEvent.setEventResult(SDKServerOperation.successfulOperation());
        } else {
            BaseResponse errorResponse = BaseResponse.fromErrorBody(res.errorBody());
            SDKServerError serverError = SDKServerError.fromCode(errorResponse.getCode());
            Log.d(TAG, SDKLoggerHelper.LOG_TAG_ECOBYTES_SDK_BACKEND + "onResponse failure (" + res.code() + "): " + errorResponse.getMessage() + " (Internal code " + errorResponse.getCode() + ")");
            mEvent.setEventResult(new SDKServerOperation(false, serverError));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d(TAG, SDKLoggerHelper.LOG_TAG_ECOBYTES_SDK_BACKEND + "Failure communicating with EcoBytes backend.");
        t.printStackTrace();
        mEvent.setEventResult(new SDKServerOperation(false, SDKServerError.ERROR_NO_COMMUNICATION));
    }

    public interface OnSuccessfulResponse<T extends BaseResponse> {
        void onSuccessfulResponse(T responseBody);
    }
}
