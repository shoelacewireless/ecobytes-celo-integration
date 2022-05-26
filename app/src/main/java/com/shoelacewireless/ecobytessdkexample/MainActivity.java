package com.shoelacewireless.ecobytessdkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.shoelacewireless.ecobytessdk.walletConnect.WalletConnect;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button walletConnectBtn = findViewById(R.id.wallet_connect_btn);
        TextView walletAddress =  findViewById(R.id.wallet_address);

        /* //Example of getting arguments if in a fragment
        Bundle args = getArguments();
        String accountAddress = fragment_name_args.fromBundle(args).getAccountAddress();
        if (args != null) {
            Log.d(TAG, "Received wallet address: " + accountAddress);
            walletAddress.setText(accountAddress);
            args.clear();
        }*/

        walletConnectBtn.setOnClickListener( v -> {
            try {
                startActivity(WalletConnect.intentToConnectValoraWallet());
            } catch (ActivityNotFoundException e) {
                // TODO: Maybe show prompt or error message that there is no supported wallet in the user phone.
            }
        });

        walletAddress.setText(handleValoraIntent(getIntent()));

    }

    private String handleValoraIntent(Intent intent) {
        Log.d(TAG, "Checking if intent contains data from Alfajores.");
        String action = intent.getAction();
        Uri data = intent.getData();

        if (action != null && action.equals(Intent.ACTION_VIEW)) {
            if (data != null) {
                String accountAddress = data.getQueryParameter("account");
                if (accountAddress != null) {
                    Log.d(TAG,  "Walled address received from Alfajores.");

                    //Example to send data to another fragment with Navigation TODO: change return type to void for this

                    /*NavGraphMainDirections.ActionToRedeemCelo navAction = NavGraphMainDirections.actionToRedeemCelo();
                    navAction.setAccountAddress(accountAddress);
                    mNavController.navigate(navAction);*/
                    return accountAddress;

                }
            }
        }

        return "No Wallet Address Received";
    }
}