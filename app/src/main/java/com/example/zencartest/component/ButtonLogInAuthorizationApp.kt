package com.example.zencartest.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLogInAuthorizationApp(
    onClick: () -> Unit,
    colors: ButtonColors,
    text: String,
    iconResource: Painter,
) {
    Button(
        onClick = { onClick() },
        colors = colors,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(bottom = 24.dp)
            .size(height = 48.dp, width = 218.dp),
    ) {
        Icon(
            painter = iconResource,
            contentDescription = text,
            modifier = Modifier.padding(end = 10.dp),
        )
        Text(text = text, color = Color.White)
    }
}
