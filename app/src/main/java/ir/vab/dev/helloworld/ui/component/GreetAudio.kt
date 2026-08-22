package ir.vab.dev.helloworld.ui.component

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import ir.vab.dev.helloworld.R

@Composable
fun WelcomeAudioPlayer(audioTriggerKey: Boolean) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val welcomePlayer = remember(context) {
        MediaPlayer.create(context, R.raw.hello)?.apply {
            setVolume(0.95f, 0.95f)
        }
    }

    DisposableEffect(welcomePlayer, lifecycleOwner) {
        welcomePlayer?.setOnCompletionListener { mp ->
            mp.seekTo(0)
        }

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE || event == Lifecycle.Event.ON_STOP) {
                if (welcomePlayer?.isPlaying == true) {
                    welcomePlayer.pause()
                    welcomePlayer.seekTo(0)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            try {
                if (welcomePlayer?.isPlaying == true) {
                    welcomePlayer.stop()
                }
                welcomePlayer?.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    LaunchedEffect(audioTriggerKey) {
        welcomePlayer?.let { player ->
            if (audioTriggerKey) {
                if (player.isPlaying) {
                    player.pause()
                    player.seekTo(0)
                }
                player.start()
            } else {
                if (player.isPlaying) {
                    player.pause()
                    player.seekTo(0)
                }
            }
        }
    }
}

@Composable
fun WelcomeBackgroundAudioPlayer(audioTriggerKey: Boolean) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // ۱. همگام‌سازی همیشگی مقدار audioTriggerKey با داخل Observer
    val currentAudioTriggerKey by rememberUpdatedState(audioTriggerKey)

    val bgPlayer = remember(context) {
        MediaPlayer.create(context, R.raw.bg)?.apply {
            isLooping = true
            setVolume(0.25f, 0.25f)
        }
    }

    DisposableEffect(bgPlayer, lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP -> {
                    if (bgPlayer?.isPlaying == true) {
                        bgPlayer.pause()
                    }
                }
                Lifecycle.Event.ON_RESUME -> {
                    // ۲. استفاده از آخرین مقدار به‌روزرسانی شده
                    if (currentAudioTriggerKey && bgPlayer?.isPlaying == false) {
                        bgPlayer.start()
                    }
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            try {
                if (bgPlayer?.isPlaying == true) {
                    bgPlayer.stop()
                }
                bgPlayer?.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    LaunchedEffect(audioTriggerKey) {
        bgPlayer?.let { player ->
            if (audioTriggerKey) {
                if (!player.isPlaying) {
                    player.start()
                }
            } else {
                if (player.isPlaying) {
                    player.pause()
                    player.seekTo(0)
                }
            }
        }
    }
}