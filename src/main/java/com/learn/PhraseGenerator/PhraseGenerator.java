package com.learn.PhraseGenerator;


import java.security.PrivateKey;

public class PhraseGenerator {

    public static void main(String[] args) {
        try {
            // Step 1: Generate key
            PrivateKey privateKey = KeyGeneratorUtil.generateAndPrintKey();

            // Step 2: Generate PHRASE
            String fecPassword = "YourFecPassword@123";
            String phrase = PhraseUtil.generatePhrase(fecPassword, privateKey);
            System.out.println("\n Generated PHRASE:\n" + phrase);

            // Step 3: Convert to Byte Array
            PhraseUtil.printByteArray(phrase);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//import java.io.FileInputStream;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.security.spec.MGF1ParameterSpec;
//import java.security.spec.PSSParameterSpec;
//import java.time.Instant;
//import java.util.Base64;
//
//public class PhraseGenerator {
//
//    // Hardcoded inputs
//    private static final String KEYSTORE_PATH = "lib/keystore.pfx";
//    private static final String KEYSTORE_PASSWORD = "changeit";
//    private static final String KEY_ALIAS = "mykey";
//    private static final String FEC_PASSWORD = "YourSecureFECPassword123!";
//
//    public static void main(String[] args) {
//        try {
//            // 1. Load private key
//            PrivateKey privateKey = loadPrivateKeyFromPFX(KEYSTORE_PATH, KEYSTORE_PASSWORD, KEY_ALIAS);
//
//            // 2. Generate PHRASE
//            String phrase = generatePhrase(FEC_PASSWORD, privateKey);
//            System.out.println("Generated PHRASE:\n" + phrase);
//
//            // 3. Convert PHRASE to byte[]
//            byte[] phraseBytes = phrase.getBytes(StandardCharsets.UTF_8);
//            System.out.println("\nPHRASE Byte Array:");
//            for (byte b : phraseBytes) {
//                System.out.print(String.format("0x%02X ", b));
//            }
//            System.out.println();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static PrivateKey loadPrivateKeyFromPFX(String path, String password, String alias) throws Exception {
//        KeyStore keystore = KeyStore.getInstance("PKCS12");
//        try (FileInputStream fis = new FileInputStream(path)) {
//            keystore.load(fis, password.toCharArray());
//        }
//        Key key = keystore.getKey(alias, password.toCharArray());
//        if (key instanceof PrivateKey) {
//            return (PrivateKey) key;
//        }
//        throw new Exception("Private key not found for alias: " + alias);
//    }
//
//    private static String generatePhrase(String fecPassword, PrivateKey privateKey) throws Exception {
//        // Get current epoch time
//        long epoch = Instant.now().getEpochSecond();
//        String epochStr = Long.toString(epoch);
//
//        // Hash FEC password with SHA-256
//        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
//        byte[] hashBytes = sha256.digest(fecPassword.getBytes(StandardCharsets.UTF_8));
//
//        // Base64 encode the hashed password
//        String hb64 = Base64.getEncoder().encodeToString(hashBytes);
//
//        // Concatenate: epoch:base64(sha256(password))
//        String phraseBase = epochStr + ":" + hb64;
//        byte[] phraseBytes = phraseBase.getBytes(StandardCharsets.UTF_8);
//
//        // Sign using RSASSA-PSS with SHA-512
//        Signature signer = Signature.getInstance("RSASSA-PSS");
//        PSSParameterSpec pssSpec = new PSSParameterSpec(
//            "SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1);
//        signer.setParameter(pssSpec);
//        signer.initSign(privateKey);
//        signer.update(phraseBytes);
//        byte[] signature = signer.sign();
//
//        // Final PHRASE = Base64 of signature
//        return Base64.getEncoder().encodeToString(signature);
//    }
//}
//
