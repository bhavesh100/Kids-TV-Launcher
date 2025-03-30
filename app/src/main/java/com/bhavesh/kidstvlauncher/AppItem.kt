package com.bhavesh.kidstvlauncher

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text

@Composable
fun AppItem(appName: String, onClick: () -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(if (isFocused.value) Color.Yellow else Color.White, RoundedCornerShape(8.dp))
            .focusable()
            .onFocusChanged { isFocused.value = it.isFocused }
            .clickable { onClick() }
            .focusRequester(focusRequester)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = appName, color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}