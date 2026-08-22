package ir.vab.dev.helloworld.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import ir.vab.dev.helloworld.ui.component.ConfettiAnimation
import ir.vab.dev.helloworld.ui.component.GreetAnimation
import ir.vab.dev.helloworld.ui.component.GreetCard
import ir.vab.dev.helloworld.ui.component.IntroInfo
import ir.vab.dev.helloworld.ui.component.IntroLogo
import ir.vab.dev.helloworld.ui.component.NameTextField
import ir.vab.dev.helloworld.ui.component.QuoteCard
import ir.vab.dev.helloworld.ui.component.WelcomeAudioPlayer
import ir.vab.dev.helloworld.ui.component.WelcomeBackgroundAudioPlayer

@Composable
fun NameInputScreen(modifier: Modifier = Modifier, onNavigateToAbout: () -> Unit = {}) {
    var name by rememberSaveable { mutableStateOf("") }
    var submit by rememberSaveable{mutableStateOf(false) }
    var vibeText by rememberSaveable{mutableStateOf("")}
    var isQuotesFinished by rememberSaveable{mutableStateOf(false)}

//    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold() {
        paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize().background(MaterialTheme.colorScheme.background),
            contentAlignment = if(submit) Alignment.BottomCenter else Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.imePadding().padding(bottom = 16.dp).verticalScroll(rememberScrollState())) {
                if(!submit) IntroLogo()

                NameTextField(
                    name = name,
                    onNameChange = { name = it },
                    onSendClick = {
                        keyboardController?.hide()
                        submit = true
                    },
                    onEditClick = {
                        submit = false
                    },
                    modifier = modifier,
                    submit = submit
                )
                if(!submit) IntroInfo()
            }

            if(submit) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(80.dp))
                        GreetAnimation({
                                text -> vibeText = text
                        })
                        if(vibeText == "Quote") {
                            QuoteCard({ isFinished ->
                                isQuotesFinished = isFinished
                            })



                        }else GreetCard(name, vibeText)
                    }
                    if(isQuotesFinished && vibeText == "Quote"){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            ConfettiAnimation()
                        }
                    }


                }
            }

        }

        WelcomeAudioPlayer(submit)
        WelcomeBackgroundAudioPlayer(submit)

    }

    Box(modifier = Modifier.padding(top = 42.dp, start = 24.dp).fillMaxSize(),
        contentAlignment = Alignment.TopStart){
        IconButton(onNavigateToAbout) {
            Icon(Icons.Filled.Info, "Info", tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
        }
    }

}