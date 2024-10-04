package com.example.zencartest.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.zencartest.R
import com.example.zencartest.utils.fontDimensionResource

@Composable
fun TextMainApp(nameApp: String) {
    Text(
        modifier = Modifier.padding(top = 80.dp, bottom = 50.dp),
        text = nameApp,
        color = Color.Black,
        fontSize = fontDimensionResource(id = R.dimen.text_size_26sp),
    )
}
