package com.billapp.billapp.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

//To generate key
public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey(){

        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair=keyPairGenerator.generateKeyPair();
        }catch (Exception e){
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
