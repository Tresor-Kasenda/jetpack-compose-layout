package com.example.notes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CallCenterScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.Black)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier,
            color = Color.Red,
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Call,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(16.dp).rotate(135.0f)
            )
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "(051) 7080507",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "",
                    tint = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Pakistan",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Surface(
            modifier = Modifier,
            color = Color.Green,
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Call,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@PreviewLightDark()
@Composable
fun CallCenterScreenPreview() {
    CallCenterScreen()
}