package com.example.zencartest.utils

sealed class TaskResult<out T> {
    data class Success<out T>(val data: T?) : TaskResult<T>()
    data class Error(val errorType: String) : TaskResult<Nothing>()
}