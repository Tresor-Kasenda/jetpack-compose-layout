package com.example.notes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
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
fun AppelScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.Black)
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier,
            shape = CircleShape,
            color = Color.Red
        ) {
            Icon(
                Icons.Default.Call,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.padding(16.dp).rotate(135f)
            )
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "(051) 7080507",
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "",
                    tint = Color.Blue,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Pakistan",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Surface(
            modifier = Modifier,
            shape = CircleShape,
            color = Color.Green
        ) {
            Icon(
                Icons.Default.Call,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@PreviewLightDark()
@Composable
fun AppelScreenPreview() {
    AppelScreen()
}