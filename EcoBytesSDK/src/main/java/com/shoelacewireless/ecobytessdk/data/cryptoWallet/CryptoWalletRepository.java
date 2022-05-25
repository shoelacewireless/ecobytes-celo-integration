package com.shoelacewireless.ecobytessdk.data.cryptoWallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.shoelacewireless.ecobytessdk.backend.SDKBackendApi;
import com.shoelacewireless.ecobytessdk.backend.SDKRetrofitService;
import com.shoelacewireless.ecobytessdk.data.SDKSharedPreferencesHelper;
import com.shoelacewireless.ecobytessdk.util.SDKLoggerHelper;

//TODO: Move Wallet related stuff from User here for SDK.
public class CryptoWalletRepository {

    private static final String TAG = "CryptoWalletRepository";
    private static CryptoWalletRepository _instance = null;

    public static CryptoWalletRepository getRepository(Context context) {
        Log.d(TAG, "Fetching UserAccountRepository");
        if (_instance == null) {
            synchronized (CryptoWallet.class) {
                if (_instance == null) {
                    _instance = new CryptoWalletRepository(context);
                }
            }
        }
        return _instance;
    }

    private final SDKBackendApi mSDKBackendApi;
    private final SharedPreferences mSDKSharedPreferences;
    private final Gson mGson;

    //Data
    private CryptoWallet mCryptoWallet = null;

    private CryptoWalletRepository(Context context) {
        Log.d(TAG, SDKLoggerHelper.LOG_TAG_ECOBYTES_SDK_UI + "Creating CryptoWalletRepository");
        mSDKBackendApi = SDKRetrofitService.getService(context).getApi();
        mSDKSharedPreferences = SDKSharedPreferencesHelper.getInstance(context);
        mGson = new Gson();

        String userJson = mSDKSharedPreferences.getString(SDKSharedPreferencesHelper.KEY_CRYPTO_WALLET, null);
        if (userJson != null) {
            mCryptoWallet = mGson.fromJson(userJson, CryptoWallet.class);
            mCryptoWalletLiveData.postValue(mCryptoWallet);
        }

    }

    //Crypto Wallet Address
    private final MutableLiveData<CryptoWallet> mCryptoWalletLiveData = new MutableLiveData<>(null);
    public LiveData<CryptoWallet> getUserSession() {return mCryptoWalletLiveData;}

    //TODO: Sebastian says it would be better to store wallet client only and make user connect wallet again if re-install or new device.
    public void setWalletAddress(CryptoWallet cryptoWallet) {
        mCryptoWallet = cryptoWallet;
        mSDKSharedPreferences
                .edit()
                .putString(SDKSharedPreferencesHelper.KEY_CRYPTO_WALLET, mGson.toJson(cryptoWallet))
                .apply();
        mCryptoWalletLiveData.postValue(mCryptoWallet);
    }
}
