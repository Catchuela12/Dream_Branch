package com.example.dream_branch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dream_branch.ui.theme.Dream_BranchTheme
import kotlin.random.Random

class StudentThreeNew : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dream_BranchTheme {
                StudentThreeScreen(
                    name = "Bejay Dayao",
                    bio = "It's Hard actually",
                    photoRes = R.drawable.pic_ko,
                    onBack = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentThreeScreen(
    name: String,
    bio: String,
    photoRes: Int,
    onBack: () -> Unit
) {
    // Animations
    val infiniteTransition = rememberInfiniteTransition(label = "effects")

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val shimmerTranslate by infiniteTransition.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(4.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.1f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A2E).copy(alpha = 0.95f)
                )
            )
        },
        containerColor = Color(0xFF0F0F23)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Animated background gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFF1A1A4E),
                                Color(0xFF0F0F23),
                                Color.Black
                            ),
                            center = Offset(shimmerTranslate, 500f),
                            radius = 800f
                        )
                    )
            )

            // Floating particles
            FloatingParticles()

            // Main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Rotating ring behind profile
                Box(
                    modifier = Modifier.size(240.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(220.dp)
                            .rotate(rotationAngle)
                    ) {
                        drawArc(
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0xFF00D4FF),
                                    Color(0xFF9D4EDD),
                                    Color(0xFFFF006E),
                                    Color.Transparent
                                )
                            ),
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }

                    // Profile image with glow
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .shadow(
                                elevation = (30 * glowIntensity).dp,
                                shape = CircleShape,
                                spotColor = Color(0xFF00D4FF)
                            )
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF9D4EDD).copy(alpha = glowIntensity),
                                        Color(0xFF2A2A4A)
                                    )
                                ),
                                shape = CircleShape
                            )
                            .border(
                                width = 3.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF00D4FF),
                                        Color(0xFF9D4EDD),
                                        Color(0xFFFF006E)
                                    )
                                ),
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = photoRes),
                            contentDescription = "$name photo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Name with scale animation
                Text(
                    text = name,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier.scale(pulseScale),
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Subtitle with gradient
                Text(
                    text = "⭐ Dream Team Member ⭐",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFFD700)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Bio card with shimmer effect
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 30.dp,
                            shape = RoundedCornerShape(28.dp),
                            spotColor = Color(0xFF9D4EDD)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF000080),
                                        Color(0xFF4C1D95),
                                        Color(0xFF7C3AED),
                                        Color(0xFF4C1D95),
                                        Color(0xFF000080)
                                    ),
                                    start = Offset(shimmerTranslate - 500f, 0f),
                                    end = Offset(shimmerTranslate + 500f, 0f)
                                ),
                                shape = RoundedCornerShape(28.dp)
                            )
                            .border(
                                width = 2.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF00D4FF).copy(alpha = glowIntensity),
                                        Color(0xFF9D4EDD).copy(alpha = glowIntensity)
                                    )
                                ),
                                shape = RoundedCornerShape(28.dp)
                            )
                            .padding(vertical = 32.dp, horizontal = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "💬",
                                fontSize = 32.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = bio,
                                fontSize = 22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 32.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Stats row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatBadge("💪", "Strong")
                    StatBadge("🧠", "Smart")
                    StatBadge("⚡", "Fast")
                }
            }
        }
    }
}

@Composable
fun StatBadge(emoji: String, label: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A4A).copy(alpha = 0.6f)
        ),
        modifier = Modifier
            .shadow(8.dp, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = emoji, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun FloatingParticles() {
    val particles = remember {
        List(20) {
            ParticleState(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 4f + 2f,
                speed = Random.nextFloat() * 0.5f + 0.2f
            )
        }
    }

    particles.forEach { particle ->
        val infiniteTransition = rememberInfiniteTransition(label = "particle")
        val yOffset by infiniteTransition.animateFloat(
            initialValue = particle.y,
            targetValue = particle.y - 0.3f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = (3000 / particle.speed).toInt(),
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "particleY"
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val x = size.width * particle.x
            val y = size.height * (yOffset % 1f)
            drawCircle(
                color = Color(0xFF00D4FF).copy(alpha = 0.3f),
                radius = particle.size,
                center = Offset(x, y)
            )
        }
    }
}

data class ParticleState(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)