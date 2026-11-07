package com.jarves.mh.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarves.mh.R
import kotlinx.coroutines.delay

val CyberNeonCyan = Color(0xFF00F2FE)
val CyberNeonPurple = Color(0xFFB5179E)
val CyberElectricBlue = Color(0xFF4FACFE)
val CyberDeepDark = Color(0xFF06090F)
val CyberGold = Color(0xFFFFD166)

@Composable
fun JuhiLaunchScreen(
    onDismiss: () -> Unit = {},
    statusMessage: String? = null,
) {
    val context = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition(label = "cyberGlow")

    // Pulsing glow animation
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "pulseGlow",
    )

    // Holographic gradient rotation
    val rotateAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotateAngle",
    )

    // Shimmer offset for progress bar
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -300f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerOffset",
    )

    // Simulated bootstrap progress
    var progress by remember { mutableFloatStateOf(0.15f) }
    var currentPhase by remember { mutableStateOf("Initializing Juhi Neural Matrix...") }
    var isReady by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val phases = listOf(
            0.30f to "Calibrating 3D Holographic Core...",
            0.55f to "Loading AI Coding Models & Tools...",
            0.80f to "Establishing Secure VIP Workspace...",
            1.00f to "Juhi Agent VIP Online & Ready",
        )
        for ((p, text) in phases) {
            delay(500)
            progress = p
            currentPhase = text
        }
        isReady = true
        delay(2000)
        onDismiss()
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "animatedProgress",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberDeepDark)
            .clickable { onDismiss() },
    ) {
        // Full Size 9:16 Mobile Launch Image
        Image(
            painter = painterResource(id = R.drawable.img_juhi_launch),
            contentDescription = "Juhi Agent VIP Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        // Gradient Scrim Overlays (Dark Vignette + Top & Bottom Shadows for extreme clarity)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.82f),
                            Color.Black.copy(alpha = 0.25f),
                            Color.Black.copy(alpha = 0.50f),
                            Color.Black.copy(alpha = 0.95f),
                        ),
                    ),
                ),
        )

        // Subtle glowing atmospheric background dots
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        color = CyberNeonCyan.copy(alpha = 0.15f * pulseGlow),
                        radius = size.width * 0.45f,
                        center = Offset(size.width * 0.5f, size.height * 0.28f),
                    )
                    drawCircle(
                        color = CyberNeonPurple.copy(alpha = 0.18f * pulseGlow),
                        radius = size.width * 0.55f,
                        center = Offset(size.width * 0.5f, size.height * 0.70f),
                    )
                },
        )

        // Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Top VIP Badge
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.65f))
                    .border(
                        BorderStroke(
                            1.dp,
                            Brush.horizontalGradient(listOf(CyberNeonCyan, CyberNeonPurple)),
                        ),
                        RoundedCornerShape(20.dp),
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Stars,
                    contentDescription = null,
                    tint = CyberGold,
                    modifier = Modifier.size(16.dp),
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "JUHI AGENT VIP EDITION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 1.2.sp,
                )
                Spacer(Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(CyberNeonCyan, CircleShape)
                        .shadow(4.dp, CircleShape, spotColor = CyberNeonCyan),
                )
            }

            Spacer(Modifier.height(10.dp))

            // Center: 3D Holographic Avatar & Title Card
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                // 3D Avatar Crest with Pulsing Holographic Ring
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(140.dp),
                ) {
                    // Outer rotating cyber ring
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .rotate(rotateAngle)
                            .border(
                                width = 2.dp,
                                brush = Brush.sweepGradient(
                                    listOf(
                                        CyberNeonCyan,
                                        Color.Transparent,
                                        CyberNeonPurple,
                                        Color.Transparent,
                                        CyberNeonCyan,
                                    ),
                                ),
                                shape = CircleShape,
                            ),
                    )

                    // Glowing backdrop blur
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .scale(pulseGlow)
                            .blur(20.dp)
                            .background(CyberNeonCyan.copy(alpha = 0.35f), CircleShape),
                    )

                    // Avatar Image
                    Surface(
                        modifier = Modifier
                            .size(105.dp)
                            .shadow(16.dp, CircleShape, spotColor = CyberNeonCyan),
                        shape = CircleShape,
                        color = CyberDeepDark,
                        border = BorderStroke(
                            2.dp,
                            Brush.linearGradient(listOf(CyberNeonCyan, CyberNeonPurple)),
                        ),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_juhi_icon),
                            contentDescription = "Juhi Agent Icon",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                // App Title with Neon Glow Look
                Text(
                    text = "JUHI AGENT",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.5.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                )

                // Typing effect subtitle
                Spacer(Modifier.height(8.dp))
                JuhiTypingEffect(
                    texts = listOf(
                        "VIP AI Coding Assistant & Terminal",
                        "Powered by Multi-Model Intelligence",
                        "Ultra 3D Cybernetic Android Engine",
                        "Private Local Workspace & Compiler",
                    ),
                )
            }

            // Bottom Area: Loading Bar, Developer Contact & Enter Button
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // High-tech Loading Progress Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(12.dp, RoundedCornerShape(18.dp), spotColor = CyberNeonPurple),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.72f),
                    ),
                    border = BorderStroke(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(
                                CyberNeonCyan.copy(alpha = 0.4f),
                                CyberNeonPurple.copy(alpha = 0.4f),
                            ),
                        ),
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Bolt,
                                    contentDescription = null,
                                    tint = CyberNeonCyan,
                                    modifier = Modifier.size(16.dp),
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = statusMessage ?: currentPhase,
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                            Text(
                                text = "${(animatedProgress * 100).toInt()}%",
                                color = CyberNeonCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                            )
                        }

                        Spacer(Modifier.height(10.dp))

                        // Glowing Futuristic Progress Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.White.copy(alpha = 0.12f)),
                        ) {
                            // Progress Fill
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(animatedProgress)
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(CyberNeonCyan, CyberElectricBlue, CyberNeonPurple),
                                        ),
                                    ),
                            )

                            // Shimmer highlight moving through the progress bar
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .background(
                                        Brush.linearGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.White.copy(alpha = 0.45f),
                                                Color.Transparent,
                                            ),
                                            start = Offset(shimmerOffset, 0f),
                                            end = Offset(shimmerOffset + 120f, 0f),
                                        ),
                                    ),
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Developer Contact VIP Banner
                DeveloperContactCard(context = context)

                Spacer(Modifier.height(14.dp))

                // Enter Workspace Button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(16.dp, RoundedCornerShape(16.dp), spotColor = CyberNeonCyan),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    border = BorderStroke(
                        1.5.dp,
                        Brush.horizontalGradient(
                            listOf(CyberNeonCyan, CyberNeonPurple, CyberGold),
                        ),
                    ),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        CyberNeonCyan.copy(alpha = 0.85f),
                                        CyberNeonPurple.copy(alpha = 0.85f),
                                    ),
                                ),
                                RoundedCornerShape(16.dp),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp),
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (isReady) "ENTER JUHI AGENT VIP" else "INITIALIZING...",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Developer Contact VIP Card
 * Displays developer Telegram ID @hardhackar009 with one-tap open & copy.
 */
@Composable
fun DeveloperContactCard(context: Context, modifier: Modifier = Modifier) {
    val telegramId = "@hardhackar009"
    val telegramUrl = "https://t.me/hardhackar009"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                runCatching {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
                    context.startActivity(intent)
                }.onFailure {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("Telegram", telegramId))
                    Toast.makeText(context, "Telegram copied: $telegramId", Toast.LENGTH_SHORT).show()
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0F172A).copy(alpha = 0.88f),
        ),
        border = BorderStroke(
            1.dp,
            Brush.horizontalGradient(
                listOf(CyberNeonCyan.copy(alpha = 0.6f), CyberNeonPurple.copy(alpha = 0.6f)),
            ),
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Telegram Icon Badge
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF229ED9), Color(0xFF0088CC))),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Telegram",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp),
                    )
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "DEVELOPER CONTACT",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = CyberNeonCyan,
                            letterSpacing = 1.sp,
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Verified",
                            tint = CyberGold,
                            modifier = Modifier.size(11.dp),
                        )
                    }
                    Text(
                        text = "Telegram: $telegramId",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                }
            }

            // Quick Copy Button
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Telegram", telegramId))
                        Toast.makeText(context, "Telegram ID copied: $telegramId", Toast.LENGTH_SHORT).show()
                    },
                color = Color.White.copy(alpha = 0.12f),
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        tint = CyberNeonCyan,
                        modifier = Modifier.size(13.dp),
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Copy",
                        fontSize = 11.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}

/**
 * Animated Typing Effect
 * Types out phrases character-by-character with a blinking cursor.
 */
@Composable
fun JuhiTypingEffect(
    texts: List<String>,
    modifier: Modifier = Modifier,
) {
    var textIndex by remember { mutableIntStateOf(0) }
    var displayedLength by remember { mutableIntStateOf(0) }
    var isDeleting by remember { mutableStateOf(false) }

    val currentFullText = texts.getOrElse(textIndex) { "" }

    // Blinking cursor
    val infiniteTransition = rememberInfiniteTransition(label = "cursor")
    val cursorVisible by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "cursorBlink",
    )

    LaunchedEffect(textIndex, isDeleting) {
        if (!isDeleting) {
            // Typing forward
            while (displayedLength < currentFullText.length) {
                delay(40)
                displayedLength++
            }
            delay(1800) // Pause at end of sentence
            isDeleting = true
        } else {
            // Deleting backward
            while (displayedLength > 0) {
                delay(20)
                displayedLength--
            }
            isDeleting = false
            textIndex = (textIndex + 1) % texts.size
        }
    }

    val visibleText = currentFullText.take(displayedLength)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = visibleText,
            color = CyberNeonCyan,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
        )
        Text(
            text = "|",
            color = if (cursorVisible > 0.5f) CyberNeonPurple else Color.Transparent,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
