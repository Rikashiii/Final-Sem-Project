# 🚀 Comparative Analysis of Hybrid Encryption Models for Multimedia Security

This repository contains the implementation and analysis for the Bachelor of Technology thesis, **"Comparative Analysis of Hybrid Models of AES-RSA and AES-Triple DES Algorithms for Encryption of Images and Videos"**.

---

## 🎯 Project Objective

The primary goal of this project was to conduct a thorough comparative analysis of two different hybrid encryption models—**AES-RSA** and **AES-Triple DES (TDES)**—to determine the most effective and efficient method for securing large multimedia files (images and videos).

---

## 🛡️ Hybrid Encryption Methodology

**Hybrid encryption** is a security approach that combines the speed and efficiency of **symmetric encryption** (for efficient data encryption) with the strong key management and security of **asymmetric encryption** (for secure key exchange).

### **1. AES-RSA Hybrid Model**

* **Data Encryption:** **AES** (Advanced Encryption Standard) is used for symmetric encryption of the bulk data (image or video file).
* **Key Exchange:** **RSA** (Rivest-Shamir-Adleman), an asymmetric algorithm, is used to securely encrypt and exchange the much smaller AES symmetric key using the recipient's public key.
* [cite_start]This approach is generally preferred for its strong security and efficient performance[cite: 366].

### **2. AES-Triple DES Hybrid Model**

* **Data Encryption:** **AES** is used for efficient symmetric encryption of the bulk data (image or video file).
* **Key Exchange:** **Triple DES (TDES)**, a symmetric-key algorithm run three times, is used to encrypt the AES symmetric key for secure exchange.
* [cite_start]This approach is less common than AES-RSA due to TDES's slower speed and lower security compared to RSA for key exchange[cite: 363, 365, 367].

---

## 📊 Comparative Results and Conclusion

[cite_start]The hybrid models were implemented in **JAVA** and analyzed based on time complexity ($O$), space complexity ($O$), and security strength[cite: 390, 415, 439, 461, 485].

### **Complexity Comparison**

[cite_start]The table below shows the overall complexity notations found in the study[cite: 487]:

| Hybrid Model | Time Complexity | Space Complexity |
| :--- | :--- | :--- |
| **AES-RSA** | $O(m + k^3 + n^3)$ or $O(n^3 + m + k^3)$ | $O(m + k + n)$ |
| **AES-TDES** | $O(m)$ | $O(m)$ |

> *Note: $m$ is the size of the file; $n$ is the RSA key size; $k$ is the AES key size.*

### **Conclusion**

Based on the overall comprehensive study of hybrid encryption models, the **AES-Triple DES (TDES) hybrid model** was concluded to offer superior advantages over the AES-RSA combination. [cite_start]This is due to its efficiency, simplicity, and performance, while providing a robust level of security that maintains compatibility with existing systems[cite: 489, 490, 491, 493].

---

## 🛠️ Repository Structure and Usage

The main logic and implementations are provided in the source code files:

* **`AESRSAImage.java`**
* **`AESRSAVideo.java`**
* **`AESTDESImage.java`**
* **`AESTDESVideo.java`**

### **Prerequisites**

* Java Development Kit (JDK) 8 or higher.

### **How to Run (Conceptual)**

To replicate the results, you would compile and run the individual Java files. You must replace the placeholder paths (`<input-image-path-goes-here>`) within the source code to point to your input image or video files.

```bash
# Example compilation and run for AES-RSA Image Encryption
javac AESRSAImage.java
java AESRSAImage
# Output: Hybrid Encryption and Decryption completed successfully!
