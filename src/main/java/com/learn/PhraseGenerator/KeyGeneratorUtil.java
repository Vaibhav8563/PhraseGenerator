package com.learn.PhraseGenerator;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.Base64;

public class KeyGeneratorUtil {

    public static PrivateKey generateAndPrintKey() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        String base64PrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        System.out.println("🔐 Generated RSA Private Key (Base64):");
        System.out.println(base64PrivateKey);
        return privateKey;
    }
}
