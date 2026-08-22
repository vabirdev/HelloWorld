package ir.vab.dev.helloworld.ui.component

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.vab.dev.helloworld.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun QuoteCard(onFinished: (isFinished: Boolean) -> Unit) {
    val shape = remember { RoundedCornerShape(10.dp) }
    val surfaceShadow = remember {
        Shadow(
            color = Color.Black.copy(alpha = 0.05f),
            radius = 20.dp,
            spread = (-2).dp,
            offset = DpOffset(x = 0.dp, y = 4.dp)
        )
    }

    val quotes = stringArrayResource(R.array.motivational_quotes).toList()
    var index by remember { mutableIntStateOf(0) }

    // ۱. انیمیشن روان برای پر شدن Progress Bar
    val targetProgress = if (quotes.isNotEmpty()) (index + 1).toFloat() / quotes.size else 0f
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 600), // مدت زمان حرکت نوار (۶۰۰ میلی‌ثانیه)
        label = "ProgressBarAnimation"
    )

    Surface(
        modifier = Modifier
            .padding(vertical = 30.dp, horizontal = 24.dp)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Rounded.Star, "Star", tint = MaterialTheme.colorScheme.tertiary)

                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .padding(horizontal = 8.dp),
                    color = Color(0xFF10B981),
                    trackColor = Color(0xFFE5E7EB),
                    strokeCap = StrokeCap.Round
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (quotes.isNotEmpty()) {
                // ۲. انیمیشن افکت محو شدن و انیمیت عمودی متن
                AnimatedContent(
                    targetState = quotes[index],
                    transitionSpec = {
                        // متن جدید از پایین می‌آید و محو ظاهر می‌شود
                        // متن قدیم به سمت بالا می‌رود و محو می‌شود
                        (slideInVertically { height -> height / 2 } + fadeIn(tween(400)))
                            .togetherWith(slideOutVertically { height -> -height / 2 } + fadeOut(tween(400)))
                    },
                    label = "QuoteTextAnimation"
                ) { targetQuote ->
                    Text(
                        text = targetQuote,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 28.sp
                    )
                }
            }

            LaunchedEffect(quotes) {
                if (quotes.isEmpty()) {
                    //onFinished(false)
                    return@LaunchedEffect
                }
                while (true) {
                    delay(2800.milliseconds)
                    index = (index + 1) % quotes.size

                    if(index == quotes.size-1){
                        onFinished(true)
                    } else {
                        onFinished(false)
                    }

                }
            }
        }
    }
}