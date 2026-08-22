package ir.vab.dev.helloworld.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.vab.dev.helloworld.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun GreetAnimation(onAnimChanged:(text: String)-> Unit)
{
    var animationStep by remember { mutableStateOf(0) }

    val composition by rememberLottieComposition(
        spec = when(animationStep) {
            0 -> {
                LottieCompositionSpec.RawRes(R.raw.hi_hand)
            }
            1 -> {
                LottieCompositionSpec.RawRes(R.raw.dance_cat)
            }
            2 -> {
                LottieCompositionSpec.RawRes(R.raw.run)
            }
            else -> {
                LottieCompositionSpec.RawRes(R.raw.discuss)
            }
        }
    )

    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl
    val rtlRotate = if (isRtl) -1f else 1f

    // 2. Render the animation
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever, // Loops infinitely
        modifier = Modifier.size(200.dp).graphicsLayer(scaleX = rtlRotate)
    )
    val welcome  = stringResource(R.string.welcome_back)
    val dance = stringResource(R.string.dance)
    val run = stringResource(R.string.run)
    LaunchedEffect(Unit) {
        while(animationStep < 3) {
            onAnimChanged(welcome)
            //delay(9500.milliseconds) // تأخیر ۳ ثانیه‌ای (غیر مسدودکننده)
            delay(2000.milliseconds) // تأخیر ۳ ثانیه‌ای (غیر مسدودکننده)
            onAnimChanged(dance)
            animationStep++
            delay(7500.milliseconds)
            //delay(9500.milliseconds)
            onAnimChanged(run)
            animationStep++
            delay(22000.milliseconds)
            //delay(13000.milliseconds)
            animationStep++
            onAnimChanged("Quote")
        }
    }
}
