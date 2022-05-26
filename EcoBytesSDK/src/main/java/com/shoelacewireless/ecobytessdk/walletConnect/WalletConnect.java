package com.shoelacewireless.ecobytessdk.walletConnect;

import android.content.Intent;
import android.net.Uri;

//TODO: Rename to WalletConnectAPI?
//Class to implement functions to interact with Crypto Wallets
public class WalletConnect {

    //TODO: Handle More wallets like Metamask
    public static Intent intentToConnectValoraWallet() {
        String deepUrl = "celo://wallet/dappkit?type=account_address&requestId=test1&dappName=EcoBytesSDK&callback=ecobytessdk://res";
        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(deepUrl));
        return intent;
    }


}
