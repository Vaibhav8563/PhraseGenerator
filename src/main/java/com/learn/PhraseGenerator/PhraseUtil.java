package com.learn.PhraseGenerator;


import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.time.Instant;
import java.util.Base64;

public class PhraseUtil {

    public static String generatePhrase(String fecPassword, PrivateKey privateKey) throws Exception {
        // 1. Get epoch time
        long epoch = Instant.now().getEpochSecond();
        String epochStr = Long.toString(epoch);

        // 2. SHA-256 hash of password
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(fecPassword.getBytes(StandardCharsets.UTF_8));
        String hashBase64 = Base64.getEncoder().encodeToString(hash);

        // 3. Format message: epoch:Base64(SHA-256(password))
        String message = epochStr + ":" + hashBase64;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // 4. Sign using RSASSA-PSS (SHA-512, MGF1, saltLen=64)
        Signature signature = Signature.getInstance("RSASSA-PSS");
        PSSParameterSpec spec = new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1);
        signature.setParameter(spec);
        signature.initSign(privateKey);
        signature.update(messageBytes);
        byte[] signedBytes = signature.sign();

        // 5. Base64 encode signature
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    public static void printByteArray(String phrase) {
        byte[] bytes = phrase.getBytes(StandardCharsets.UTF_8);
        System.out.println("\n🔢 PHRASE as Byte Array:");
        for (byte b : bytes) {
            System.out.print(String.format("0x%02X ", b));
        }
        System.out.println();
    }
}

