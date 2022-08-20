package com.gavinferiancek.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.gavinferiancek.tasky.auth.presentation.login.LoginScreen
import com.gavinferiancek.tasky.auth.presentation.login.LoginViewModel
import com.gavinferiancek.tasky.auth.presentation.register.RegisterScreen
import com.gavinferiancek.tasky.auth.presentation.register.RegisterViewModel
import com.gavinferiancek.tasky.core.presentation.navigation.Screens
import com.gavinferiancek.tasky.core.presentation.theme.TaskyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            TaskyTheme {
                val navController = rememberAnimatedNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                ) {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = viewModel.startDestination.collectAsState().value,
                        builder = {
                            addLoginScreen(
                                navController = navController,
                                scaffoldState = scaffoldState,
                            )
                            addRegisterScreen(
                                navController = navController,
                                scaffoldState = scaffoldState,
                            )
                            addAgendaScreen(
                                navController = navController,
                            )
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addLoginScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = Screens.Login.route
    ) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(
            state = viewModel.state,
            scaffoldState = scaffoldState,
            events = viewModel::onTriggerEvent,
            onNavigateToRegister = { navController.navigate(Screens.Register.route) },
            onNavigateToAgenda = {
                navController.navigate(Screens.Agenda.route) {
                    popUpTo(Screens.Login.route) { inclusive = true }
                }
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addRegisterScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
) {
    composable(
        route = Screens.Register.route
    ) {
        val viewModel: RegisterViewModel = hiltViewModel()
        RegisterScreen(
            state = viewModel.state,
            scaffoldState = scaffoldState,
            events = viewModel::onTriggerEvent,
            onNavigateUp = { navController.popBackStack() },
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addAgendaScreen(
    navController: NavController,
) {
    composable(
        route = Screens.Agenda.route,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text("This is the Agenda Screen!")
        }
    }
}