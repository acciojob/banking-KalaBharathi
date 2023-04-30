package com.driver;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public static int minBalance=5000;

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,minBalance);
        if(balance<minBalance){
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId=tradeLicenseId;
        validateLicenseId();
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!tradeLicenseId.equals(tradeLicenseId.toUpperCase())){
            throw new Exception("Valid License can not be generated");
        }
        HashMap<Character,Integer> hm=new HashMap<>();
        int n=tradeLicenseId.length();
        for(int i=0;i<n;i++){
            hm.put(tradeLicenseId.charAt(i),hm.getOrDefault(tradeLicenseId.charAt(i),0)+1);
        }
        int maxFre=Collections.max(hm.values());
        if((maxFre>(n+1)/2) || (maxFre>n/2)){
            throw new Exception("Valid License can not be generated");
        }
        while(!isValidId(tradeLicenseId)) {
            tradeLicenseId=rearrange(tradeLicenseId);
        }
            return;
    }
    private Boolean isValidId(String tradeLicenseId){
        int n=tradeLicenseId.length();
        for (int i = 0; i < n - 1; i++) {
            if (tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i + 1)) {
               return false;
            }
        }
        return true;
    }
    private String rearrange(String tradeLicenseId){
        StringBuilder sb = new StringBuilder(tradeLicenseId);
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == sb.charAt(i-1)) {
                // Find the first character in the string that is different from the current character
                char temp = sb.charAt(i);
                for (int j = 0; j < i; j++) {
                    if (sb.charAt(j) != temp) {
                        // Swap the current character with the different character
                        sb.setCharAt(i, sb.charAt(j));
                        sb.setCharAt(j, temp);
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

}
