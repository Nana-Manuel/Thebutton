package com.example.thebutton

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            Display()
        }
    }
}


@Composable
fun Display() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundImageBox()
        Box(modifier = Modifier.align(Alignment.CenterEnd)){
            EmergencyButton()
        }
    }
}

@Composable
fun BackgroundImageBox() {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.sigtrack),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Display()
}