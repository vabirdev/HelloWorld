package ir.vab.dev.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ir.vab.dev.helloworld.ui.navigation.AppNavigation
import ir.vab.dev.helloworld.ui.screen.AboutScreen
import ir.vab.dev.helloworld.ui.screen.NameInputScreen
import ir.vab.dev.helloworld.ui.theme.SimpleTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleTemplateTheme {
                AppNavigation()
            }
        }
    }
}


