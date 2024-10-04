package com.example.zencartest.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.SecureRandom
import java.util.Base64
import java.util.UUID

fun getUniqueIdentifier(): String {
    return UUID.randomUUID().toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateRandomToken(length: Int = 16): String {
    val random = SecureRandom()
    val bytes = ByteArray(length)
    random.nextBytes(bytes)
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
}