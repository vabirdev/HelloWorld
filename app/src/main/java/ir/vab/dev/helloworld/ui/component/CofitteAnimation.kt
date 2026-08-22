package ir.vab.dev.helloworld.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.vab.dev.helloworld.R

@Composable
fun ConfettiAnimation()
{

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.confetti)

    )

    // 2. Render the animation
    LottieAnimation(
        composition = composition,
        modifier = Modifier.fillMaxSize()
    )

}
