package com.shoelacewireless.ecobytessdk.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

//Utility class to handle HTTP requests as single-time events to be consumed by the UI.
public class SDKSingleEvent<T> {
    private final MutableLiveData<T> mEventResult = new MutableLiveData<>(null);

    public LiveData<T> getEventResult() {
        return mEventResult;
    }

    public void consumeEventResult() {
        mEventResult.postValue(null);
    }

    protected void setEventResult(T result) {
        mEventResult.postValue(result);
    }
}
