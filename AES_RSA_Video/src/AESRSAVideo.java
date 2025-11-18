import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class AESRSAVideo {

    public static void main(String[] args) {
        try {
            // Generate RSA key pair (public and private keys) for key exchange
            KeyPair keyPair = generateKeyPair();

            // Generate AES key for encrypting video data
            SecretKey aesKey = generateAESKey();

            // Specify the paths for the input and output videos
            String inputVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\input-files\\inputVideo.mp4";
            String encryptedVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_RSA_Video\\src\\output\\encOutVideo.mp4";
            String decryptedVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_RSA_Video\\src\\output\\decOutVideo.mp4";

            // Encrypt the video using AES with a randomly generated key
            encryptVideo(inputVideoPath, encryptedVideoPath, aesKey);

            // Encrypt the AES key using the RSA public key
            byte[] encryptedAESKey = encryptAESKey(aesKey, keyPair.getPublic());

            // Decrypt the AES key using the RSA private key
            SecretKey decryptedAESKey = decryptAESKey(encryptedAESKey, keyPair.getPrivate());

            // Decrypt the video using the decrypted AES key
            decryptVideo(encryptedVideoPath, decryptedVideoPath, decryptedAESKey);

            System.out.println("Hybrid Encryption and Decryption completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Adjusted to 256-bit AES key size
        return keyGenerator.generateKey();
    }

    private static void encryptVideo(String inputVideoPath, String outputVideoPath, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        try (InputStream inputStream = new FileInputStream(inputVideoPath);
             OutputStream outputStream = new FileOutputStream(outputVideoPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                if (encryptedBytes != null) {
                    outputStream.write(encryptedBytes);
                }
            }

            byte[] finalBytes = cipher.doFinal();
            if (finalBytes != null) {
                outputStream.write(finalBytes);
            }
        }
    }

    private static byte[] encryptAESKey(SecretKey aesKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(aesKey.getEncoded());
    }

    private static SecretKey decryptAESKey(byte[] encryptedAESKey, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedAESKey);
        return new SecretKeySpec(decryptedBytes, "AES");
    }

    private static void decryptVideo(String inputVideoPath, String outputVideoPath, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        try (InputStream inputStream = new FileInputStream(inputVideoPath);
             OutputStream outputStream = new FileOutputStream(outputVideoPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                if (decryptedBytes != null) {
                    outputStream.write(decryptedBytes);
                }
            }

            byte[] finalBytes = cipher.doFinal();
            if (finalBytes != null) {
                outputStream.write(finalBytes);
            }
        }
    }
}
