package ir.vab.dev.helloworld.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.vab.dev.helloworld.R

@Composable
fun GreetCard(name: String, vibeText: String) {
    val shape = remember { RoundedCornerShape(10.dp) }
    val surfaceShadow = remember {
        Shadow(
            color = Color.Black.copy(alpha = 0.05f),
            radius = 20.dp,
            spread = (-2).dp,
            offset = DpOffset(x = 0.dp, y = 4.dp)
        )
    }

    // مقدار متن خوش‌آمدگویی با توجه به خالی بودن یا نبودن vibeText
    val currentVibeText = if (vibeText.isEmpty()) stringResource(R.string.welcome_back) else vibeText

    Surface(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .fillMaxWidth()
            .dropShadow(
                shape = shape,
                shadow = surfaceShadow
            ),
        shape = shape,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            // ۱. انیمیشن جابه‌جایی متن خوش‌آمدگویی/احساس
            AnimatedContent(
                targetState = currentVibeText,
                transitionSpec = {
                    (slideInVertically { height -> height / 2 } + fadeIn(tween(350)))
                        .togetherWith(slideOutVertically { height -> -height / 2 } + fadeOut(tween(350)))
                },
                label = "VibeTextAnimation"
            ) { targetText ->
                Text(
                    text = targetText,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(4.dp))

            // ۲. انیمیشن جابه‌جایی اسم کاربر
            AnimatedContent(
                targetState = name,
                transitionSpec = {
                    (slideInVertically { height -> height / 2 } + fadeIn(tween(350)))
                        .togetherWith(slideOutVertically { height -> -height / 2 } + fadeOut(tween(350)))
                },
                label = "NameTextAnimation"
            ) { targetName ->
                Text(
                    text = "$targetName ✨",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}