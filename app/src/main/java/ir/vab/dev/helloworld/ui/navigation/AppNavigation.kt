package ir.vab.dev.helloworld.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.vab.dev.helloworld.ui.screen.AboutScreen
import ir.vab.dev.helloworld.ui.screen.NameInputScreen

// ۱. تعریف مسیرهای برنامه (Routes)
sealed class Screen(val route: String) {
    object NameInput : Screen("name_input_screen")
    object About : Screen("about_screen")
}

// ۲. کامپوننت مدیریت مسیریابی
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NameInput.route, // صفحه شروع
        modifier = modifier
    ) {
        // صفحه ورود نام
        composable(Screen.NameInput.route) {
            NameInputScreen(
                onNavigateToAbout = {
                    navController.navigate(Screen.About.route)
                }
            )
        }

        // صفحه درباره برنامه
        composable(Screen.About.route) {
            AboutScreen(onBackClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            })
        }
    }
}