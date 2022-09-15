package com.gavinferiancek.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.gavinferiancek.tasky.agenda.presentation.list.AgendaListViewModel
import com.gavinferiancek.tasky.agenda.presentation.list.AgendaScreen
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
                viewModel.state.isLoading
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
                        startDestination = viewModel.state.startDestination,
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
                            addEventDetailScreen(
                                navController = navController,
                            )
                            addTaskDetailScreen(
                                navController = navController,
                            )
                            addReminderDetailScreen(
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
        route = Screens.Login.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Up,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            if (targetState.destination.route == Screens.AgendaList.route) {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            } else {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            }
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        }
    ) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(
            state = viewModel.state,
            scaffoldState = scaffoldState,
            events = viewModel::onTriggerEvent,
            onNavigateToRegister = { navController.navigate(Screens.Register.route) },
            onNavigateToAgenda = {
                navController.navigate(Screens.AgendaList.route) {
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
        route = Screens.Register.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        }
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
        route = Screens.AgendaList.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            if (targetState.destination.route == Screens.Login.route) {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            } else {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            }
        }
    ) {
        val viewModel: AgendaListViewModel = hiltViewModel()
        AgendaScreen(
            state = viewModel.state,
            events = viewModel::onTriggerEvent,
        )
    }
}

/**
 * Three Detail screens all have hardcoded values just to show that the correct screen is displayed
 * with the intended values for passed arguments. Will update when actually building the screens.
 */

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addEventDetailScreen(
    navController: NavController,
) {
    composable(
        route = "${Screens.EventDetail.route}/{id}/{isEditing}",
        arguments = Screens.EventDetail.arguments,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        }
    ) {

    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addTaskDetailScreen(
    navController: NavController,
) {
    composable(
        route = "${Screens.TaskDetail.route}/{id}/{isEditing}",
        arguments = Screens.TaskDetail.arguments,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        }
    ) {

    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addReminderDetailScreen(
    navController: NavController,
) {
    composable(
        route = "${Screens.ReminderDetail.route}/{id}/{isEditing}",
        arguments = Screens.ReminderDetail.arguments,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        }
    ) {

    }
}
