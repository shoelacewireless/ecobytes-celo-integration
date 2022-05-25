package com.shoelacewireless.ecobytessdk.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.shoelacewireless.ecobytessdk.BuildConfig;

/**
 * Helper class to receive a SharedPreferences object built using the same
 * file key and keep the preferences' keys in a single place.
 */
public class SDKSharedPreferencesHelper {
    //TODO: What is this for?
    public static final String PREFERENCE_FILE_KEY = BuildConfig.LIBRARY_PACKAGE_NAME + ".PREFERENCE_FILE_KEY";

    // Key used to locally save the user's registered information.
    public static final String KEY_CRYPTO_WALLET = "key_crypto_wallet";

    public static SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }
}
