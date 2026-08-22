package ir.vab.dev.helloworld.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext

// Theme.kt

private val LightColors = lightColorScheme(
    primary = Blue500,
    secondary = Slate800,
    tertiary = Gold,
    background = Slate50,
    surface = White,
    surfaceContainer = Color(0xFFF1F5F9), // برای دکمه‌های غیرفعال یا پس‌زمینه‌های ثانویه
    onPrimary = White,
    onSecondary = White,
    onBackground = Slate900,
    onSurface = Slate900,
    onSurfaceVariant = Slate500 // برای آیکون‌ها و هینت ها
)

private val DarkColors = darkColorScheme(
    primary = BlueDark,
    secondary = Slate800,
    tertiary = Gold,
    background = Slate950,
    surface = Slate900,
    surfaceContainer = Slate800, // Slate800 (برای دکمه غیرفعال در دارک مود)
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    onSurfaceVariant = Slate400
)


@Composable
fun SimpleTemplateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}