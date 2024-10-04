package com.example.zencartest.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TextFieldLogin(
    textHint: String,
    color: Color,
    modifier: Modifier,
    keyOption: KeyboardOptions,
    keyboardActions: KeyboardActions,
    icon: @Composable () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = textHint, color = color) },
        leadingIcon = icon,
        keyboardOptions = keyOption,
        keyboardActions = keyboardActions,

    )
}
