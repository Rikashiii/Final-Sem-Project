import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class AESTDESImage {

    public static void main(String[] args) {
        try {
            // Generate AES key for encrypting image data
            SecretKey aesKey = generateAESKey();

            // Generate Triple DES key for encrypting image data
            SecretKey tripleDesKey = generateTripleDesKey();

            // Specify the paths for the input and output images
            String inputImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\input-files\\inputImage.png";
            String encryptedAesImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Image\\src\\output\\AES_encOutImage.png";
            String encryptedTripleDesImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Image\\src\\output\\TDES_encOutImage.png";
            String decryptedAesImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Image\\src\\output\\AES_decOutImage.png";
            String decryptedTripleDesImagePath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Image\\src\\output\\TDES_decOutImage.png";

            // Encrypt the image using AES
            encryptImage(inputImagePath, encryptedAesImagePath, aesKey);

            // Encrypt the image using Triple DES
            encryptImage(inputImagePath, encryptedTripleDesImagePath, tripleDesKey);

            // Decrypt the AES encrypted image
            decryptImage(encryptedAesImagePath, decryptedAesImagePath, aesKey);

            // Decrypt the Triple DES encrypted image
            decryptImage(encryptedTripleDesImagePath, decryptedTripleDesImagePath, tripleDesKey);

            System.out.println("Hybrid Image Encryption and Decryption completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // 256-bit key size
        return keyGenerator.generateKey();
    }

    private static SecretKey generateTripleDesKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168); // 168-bit key size
        return keyGenerator.generateKey();
    }

    private static void encryptImage(String inputImagePath, String outputImagePath, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] inputBytes = Files.readAllBytes(Paths.get(inputImagePath));
        byte[] encryptedBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
            outputStream.write(encryptedBytes);
        }
    }

    private static void decryptImage(String inputImagePath, String outputImagePath, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] encryptedBytes = Files.readAllBytes(Paths.get(inputImagePath));
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
            outputStream.write(decryptedBytes);
        }
    }
}
