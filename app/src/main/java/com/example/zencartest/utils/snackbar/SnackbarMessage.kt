package com.example.zencartest.utils.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import com.example.zencartest.R.string as AppText

sealed class SnackbarMessage {
    data class StringSnackbar(val message: String) : SnackbarMessage()
    data class ResourceSnackbar(@StringRes val message: Int) : SnackbarMessage()

    companion object {
        fun SnackbarMessage.toMessage(resources: Resources): String {
            return when (this) {
                is StringSnackbar -> this.message
                is ResourceSnackbar -> resources.getString(this.message)
            }
        }

        fun Throwable.toSnackbarMessage(): SnackbarMessage {
            val message = this.message.orEmpty()
            return if (message.isNotBlank()) {
                StringSnackbar(message)
            } else {
                ResourceSnackbar(AppText.generic_error)
            }
        }
    }
}
