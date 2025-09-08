package com.example.notes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun CallScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier,
            color = Color.Red,
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Phone,
                contentDescription = "Call",
                modifier = Modifier.padding(16.dp).rotate(135.0f),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "(051) 7080507",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier.padding(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = "Pakistan",
                    color = Color.LightGray,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Surface(
            modifier = Modifier,
            color = Color.Green,
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Phone,
                contentDescription = "Call",
                modifier = Modifier.padding(16.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}


@PreviewLightDark
@Composable
fun CallScreenPreview() {
    CallScreen()
}