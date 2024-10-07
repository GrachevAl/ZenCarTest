package com.example.zencartest.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorCode: Int, val message: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}
