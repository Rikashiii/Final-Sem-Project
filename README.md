# 🚀 Comparative Analysis of Hybrid Encryption Models for Multimedia Security

This repository contains the implementation and analysis for the Bachelor of Technology thesis, **"Comparative Analysis of Hybrid Models of AES-RSA and AES-Triple DES Algorithms for Encryption of Images and Videos"**.

---

## 🎯 Project Summary

The core objective was to conduct a comparative study of two hybrid encryption approaches, **AES-RSA** and **AES-Triple DES (TDES)**, to identify the most effective and efficient method for securing large multimedia files.

Hybrid encryption combines the strengths of both symmetric and asymmetric encryption.

* **Symmetric Encryption (AES):** Used for efficient, fast encryption of the bulk data (the image/video file).
* **Asymmetric Encryption (RSA/TDES):** Used for secure key exchange of the much smaller symmetric AES key.

---

## 🛡️ Key Results and Conclusion

### **Complexity Comparison**

The analysis compared the performance based on computational complexities:

| Hybrid Model | Time Complexity | Space Complexity |
| :--- | :--- | :--- |
| **AES-RSA** | $O(m + k^3 + n^3)$ or $O(n^3 + m + k^3)$ | $O(m + k + n)$ |
| **AES-TDES** | $O(m)$ | $O(m)$ |

> *Note: $m$ is file size, $n$ is the RSA key size, and $k$ is the AES key size.*

### **Conclusion**

The study concluded that the **AES-Triple DES hybrid model offers superior advantages** over the AES-RSA combination. It ensures a solid foundation for safeguarding sensitive data due to its efficiency, simplicity, performance, and robust security level.

---

## 🛠️ Usage and Implementation

The project was implemented in **JAVA** and includes dedicated files for both image and video encryption using the two hybrid models.

| Model | Image File | Video File |
| :--- | :--- | :--- |
| **AES-RSA** | `AESRSAImage.java` | `AESRSAVideo.java` |
| **AES-TDES** | `AESTDESImage.java` | `AESTDESVideo.java` |

### **How to Run**

1.  **Prerequisite:** Ensure you have the Java Development Kit (JDK) installed.
2.  **Edit Paths:** Modify the placeholder file paths (e.g., `<input-image-path-goes-here>`) directly
