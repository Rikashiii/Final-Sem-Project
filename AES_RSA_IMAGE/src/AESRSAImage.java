import javax.crypto.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import javax.crypto.spec.SecretKeySpec;

public class AESRSAImage {

    public static void main(String[] args) {
        try {
            // Generate RSA key pair (public and private keys) for key exchange
            KeyPair keyPair = generateKeyPair();

            // Generate AES key for encrypting image data
            SecretKey aesKey = generateAESKey();

            // Specify the paths for the input and output images
            String inputImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\input-files\\inputImage.png";
            String encryptedImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_RSA_IMAGE\\src\\output\\encOutImage.png";
            String decryptedImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_RSA_IMAGE\\src\\output\\decOutImage.png";

            // Encrypt the image using AES with a randomly generated key
            encryptImage(inputImagePath, encryptedImagePath, aesKey);

            // Encrypt the AES key using the RSA public key
            byte[] encryptedAESKey = encryptAESKey(aesKey, keyPair.getPublic());

            // Decrypt the AES key using the RSA private key
            SecretKey decryptedAESKey = decryptAESKey(encryptedAESKey, keyPair.getPrivate());

            // Decrypt the image using the decrypted AES key
            decryptImage(encryptedImagePath, decryptedImagePath, decryptedAESKey);

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
        keyGenerator.init(128); // You can adjust the key size as needed (128, 192, or 256 bits)
        return keyGenerator.generateKey();
    }

    private static void encryptImage(String inputImagePath, String outputImagePath, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] inputBytes = Files.readAllBytes(Paths.get(inputImagePath));
        byte[] encryptedBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
            outputStream.write(encryptedBytes);
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

    private static void decryptImage(String inputImagePath, String outputImagePath, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        byte[] encryptedBytes = Files.readAllBytes(Paths.get(inputImagePath));
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
            outputStream.write(decryptedBytes);
        }
    }
}
