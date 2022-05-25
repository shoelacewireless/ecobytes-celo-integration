package com.shoelacewireless.ecobytessdk.backend.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.shoelacewireless.ecobytessdk.util.SDKServerError;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public class BaseResponse {
    private final static String MESSAGE_FIELD = "message";
    private final static String CODE_FIELD = "code";

    // When a request fails, EcoBytes backend should respond with an error code and message
    @SerializedName(CODE_FIELD) private String mCode = SDKServerError.ERROR_000.mErrorCode;
    @SerializedName(MESSAGE_FIELD) private String mMessage = "";

    public String getCode() { return mCode; }
    public String getMessage() { return mMessage; }

    //Helper method to deserialize a BaseResponse from a unsuccessful raw response body.
    public static BaseResponse fromErrorBody(ResponseBody errorBody) {
        if (errorBody != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<BaseResponse>() {}.getType();
            return gson.fromJson(errorBody.charStream(), type);
        } else {
            return new BaseResponse();
        }
    }
}