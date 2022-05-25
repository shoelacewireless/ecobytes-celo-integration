package com.shoelacewireless.ecobytessdk.backend;

import com.shoelacewireless.ecobytessdk.backend.request.TestCeloMintTokensRequest;
import com.shoelacewireless.ecobytessdk.backend.request.TestCeloWalletRequest;
import com.shoelacewireless.ecobytessdk.backend.response.AlfajoresTokenTransactionResponse;
import com.shoelacewireless.ecobytessdk.backend.response.BaseResponse;
import com.shoelacewireless.ecobytessdk.backend.response.TestCeloBalanceResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

// Interface used by Retrofit to generate HTTP calls.
public interface SDKBackendApi {

    //TODO: Make them only for dev or remove in the future, this calls work only with celo alfajores test-network

    @POST("user/test-celo-wallet")
    Call<BaseResponse> addCeloWallet(@Body TestCeloWalletRequest req, @Header("Authorization") String authHeader);

    @POST("user/test-celo-mint-tokens")
    Call<BaseResponse> redeemCeloTokens(@Body TestCeloMintTokensRequest req, @Header("Authorization") String authHeader);

    @GET("user/test-celo-activity-history")
    Call<AlfajoresTokenTransactionResponse> getTestCeloActivityHistory(@Header("Authorization") String authHeader);

    @GET("user/test-celo-balance")
    Call<TestCeloBalanceResponse> getTestCeloBalance(@Header("Authorization") String authHeader);

}