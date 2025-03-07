package com.example.thebutton

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun EmergencyButton() {
    var expanded by remember { mutableStateOf(false) }

    // Animate button width & height smoothly
    val width by animateDpAsState(
        targetValue = if (expanded) 180.dp else 100.dp, // Expand width
        animationSpec = tween(150), label = "width animation"
    )
    val height by animateDpAsState(
        targetValue = if (expanded) 300.dp else 100.dp, // Expand height
        animationSpec = tween(150), label = "height animation"
    )

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        expanded = true // Expand when pressed
                        tryAwaitRelease()
                        expanded = false // Shrink when released
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier
                .size(width, height) // Animate size smoothly
                .clip(
                    RoundedCornerShape(
                    topStart = if (expanded) 200.dp else 50.dp,
                    topEnd = if (expanded) 0.dp else 50.dp,
                    bottomStart = if (expanded) 200.dp else 50.dp,
                    bottomEnd = if (expanded) 0.dp else 50.dp
                )
                )
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = expanded, label = "crossfade transition") { targetExpanded ->
                if (targetExpanded) {
                    Expanded()
                } else {
                    ContentIcon()
                }
            }
        }
    }
}

@Composable
fun Expanded() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black)
                    .size(80.dp)
                    .clickable { println("Sending SOS") },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Call",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp))
                    Text(text = "Send SOS", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black)
                    .size(80.dp)
                    .clickable { println("Recording Audio") },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Icon(painter = painterResource(R.drawable.microphone),
                        contentDescription = "Call",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )

                    Text(text = "Send Audio", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ContentIcon() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.alert))

    Surface(
        modifier = Modifier
            .clip(CircleShape)
            .size(100.dp),
        color = Color.Transparent, // Removes unwanted background
        shadowElevation = 0.dp
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(80.dp)
        )
    }
}
