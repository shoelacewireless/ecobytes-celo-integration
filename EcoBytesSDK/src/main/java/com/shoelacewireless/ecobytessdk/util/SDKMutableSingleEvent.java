package com.shoelacewireless.ecobytessdk.util;

//SingleEvent that exposes the ability to set the value of the inner MutableLiveData.
public class SDKMutableSingleEvent<T> extends SDKSingleEvent<T> {
    @Override
    public void setEventResult(T result) {
        super.setEventResult(result);
    }
}
