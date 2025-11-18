import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class AESTDESVideo {

    public static void main(String[] args) {
        try {
            // Generate AES key for encrypting video data
            SecretKey aesKey = generateAESKey();

            // Generate Triple DES key for encrypting video data
            SecretKey tripleDesKey = generateTripleDesKey();

            // Specify the paths for the input and output videos
            String inputVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\input-files\\inputVideo.mp4";
            String encryptedAesVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Video\\src\\output\\AES_encOutVideo.mp4";
            String encryptedTripleDesVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Video\\src\\output\\TDES_encOutVideo.mp4";
            String decryptedAesVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Video\\src\\output\\AES_decOutVideo.mp4";
            String decryptedTripleDesVideoPath = "C:\\Users\\VARUN\\Desktop\\FINAL SEM PROJECT\\AES_TDES_Video\\src\\output\\TDES_decOutVideo.mp4";

            // Encrypt the video using AES
            encryptVideo(inputVideoPath, encryptedAesVideoPath, aesKey);

            // Encrypt the video using Triple DES
            encryptVideo(inputVideoPath, encryptedTripleDesVideoPath, tripleDesKey);

            // Decrypt the AES encrypted video
            decryptVideo(encryptedAesVideoPath, decryptedAesVideoPath, aesKey);

            // Decrypt the Triple DES encrypted video
            decryptVideo(encryptedTripleDesVideoPath, decryptedTripleDesVideoPath, tripleDesKey);

            System.out.println("Hybrid Video Encryption and Decryption completed successfully!");
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

    private static void encryptVideo(String inputVideoPath, String outputVideoPath, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);

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

    private static void decryptVideo(String inputVideoPath, String outputVideoPath, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);

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
