package com.example.zencartest.utils

fun generateImageNameForStorage(): String {
    return "cover_${System.currentTimeMillis()}.jpg"
}
