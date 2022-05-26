package com.shoelacewireless.ecobytessdk.data.cryptoWallet;

//TODO: Maybe we need to create a class that holds multiple CryptoWallet like UserCryptoWallets for when we support multiple wallets and chains
public class CryptoWallet {

    private final String mWalletAddress;

    public CryptoWallet(String walletAddress) {
        mWalletAddress = walletAddress;
    }

    public String getWalletAddress() { return mWalletAddress; }

}
