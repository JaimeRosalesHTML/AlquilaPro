package com.jaimerosales.alquilapro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaimerosales.alquilapro.ui.theme.AlquilaProTheme

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlquilaProTheme {
                AboutScreen()
            }
        }
    }
}

@Composable
fun AboutScreen() {
    val activity = androidx.compose.ui.platform.LocalContext.current
            as ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "About AlquilaPro",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "AlquilaPro helps landlords manage their rental properties and payments.",
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = {
                activity.finish()
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Back")
        }
    }
}