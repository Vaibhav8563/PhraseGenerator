package com.learn.PhraseGenerator;

import java.util.Base64;
import java.security.MessageDigest;

public class Phrase {
    public static String generatePhrase(String fecPassword) throws Exception {
        // Step 1: SHA256 of password
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] h = digest.digest(fecPassword.getBytes());

        // Step 2: Base64 encode
        String hb64 = Base64.getEncoder().encodeToString(h);

        // Step 3: Get epoch
        long epoch = System.currentTimeMillis() / 1000;

        // Step 4: Concatenate
        String r = epoch + hb64;

        // Step 5: SHA256 again
        byte[] h1 = digest.digest(r.getBytes());

        // Step 6: Base64 encode final hash
        String hb64Prime = Base64.getEncoder().encodeToString(h1);

        // Step 7: Create final PHRASE
        String phrase2 = epoch + " " + hb64Prime;
        return  phrase2;
    }

    public static void main(String[] args) throws Exception {
        String phrase = generatePhrase("YourFECPasswordHere");
        System.out.println("PHRASE: " + phrase);
    }
}
