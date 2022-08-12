package com.gavinferiancek.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.gavinferiancek.tasky.auth.presentation.AuthScreen
import com.gavinferiancek.tasky.auth.presentation.AuthViewModel
import com.gavinferiancek.tasky.core.presentation.navigation.Screens
import com.gavinferiancek.tasky.core.presentation.theme.TaskyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                // TODO Check if user token is valid
                false
            }
        }
        setContent {
            TaskyTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screens.Login.route,
                    builder = {
                        addLoginScreen(
                            navController = navController,
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addLoginScreen(
    navController: NavController,
) {
    composable(
        route = Screens.Login.route
    ) {
        val viewModel: AuthViewModel = hiltViewModel()
        AuthScreen(
            state = viewModel.state,
            events = viewModel::onTriggerEvent,
        )
    }
}