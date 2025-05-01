package com.example.mobileiip02504.MOBILEIIP02504.util

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptionUtils {
    private val secretKey = "my-super-secret-key".padEnd(32, '0').toByteArray()

    fun encrypt(input: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(secretKey, "AES")
        val iv = ByteArray(16)
        java.util.Random().nextBytes(iv)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec)
        val encrypted = cipher.doFinal(input.toByteArray())
        val ivAndEncrypted = iv + encrypted
        return android.util.Base64.encodeToString(
            ivAndEncrypted,
            android.util.Base64.DEFAULT
        )
    }

    fun decrypt(input: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(secretKey, "AES")

        val decodedInput = android.util.Base64.decode(input, android.util.Base64.DEFAULT)
        val iv = decodedInput.copyOfRange(0, 16)
        val encryptedData = decodedInput.copyOfRange(16, decodedInput.size)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec)
        val decrypted = cipher.doFinal(encryptedData)
        return String(decrypted)
    }


}
