package com.shoelacewireless.ecobytessdk.backend.response;

import com.google.gson.annotations.SerializedName;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AlfajoresTokenTransactionResponse extends BaseResponse {

    @SerializedName("history") private List<AlfajoresTokenTransaction> mTransactions;

    public List<AlfajoresTokenTransaction> getTransactions() {
        return mTransactions;
    }

    public static class AlfajoresTokenTransaction {
        @SerializedName("date") private Date mDate;
        @SerializedName("redeemCurrency") private String mRedeemCurrency; //"GOLD" or "SILVER"
        @SerializedName("amountRedeemed") private int mAmountRedeemed;
        @SerializedName("receivedCurrency") private String mReceivedCurrency; //"EBS" or "cUSD"
        @SerializedName("amountReceived") private Double mAmountReceived;

        public Date getDate() {
            return mDate;
        }

        public String getRedeemCurrency() {
            return mRedeemCurrency;
        }

        public int getAmountRedeemed() {
            return mAmountRedeemed;
        }

        public String getReceivedCurrency() {
            return mReceivedCurrency;
        }

        public Double getAmountReceived() {
            return mAmountReceived;
        }

        public String getTransactionListLabel() {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("MMMM dd, yyyy")
                    .withZone(ZoneId.systemDefault());
            String dateString = formatter.format(mDate.toInstant().atZone(ZoneId.systemDefault()));

            return dateString + " redeemed " + mAmountRedeemed + " " + mRedeemCurrency + " for " + mAmountReceived + " " + mReceivedCurrency + ".";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AlfajoresTokenTransaction that = (AlfajoresTokenTransaction) o;
            return mDate == that.mDate &&
                    mRedeemCurrency.equals(that.mRedeemCurrency) &&
                    mAmountRedeemed == that.mAmountRedeemed &&
                    mReceivedCurrency.equals(that.mReceivedCurrency) &&
                    mAmountReceived.equals(that.mAmountReceived);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mDate, mRedeemCurrency, mAmountRedeemed, mReceivedCurrency, mAmountReceived);
        }
    }
}
