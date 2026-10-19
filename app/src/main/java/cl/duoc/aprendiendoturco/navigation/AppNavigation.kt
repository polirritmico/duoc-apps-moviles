package cl.duoc.aprendiendoturco.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.aprendiendoturco.ui.screens.HomeScreenCompact
import cl.duoc.aprendiendoturco.ui.screens.LoginScreen
import cl.duoc.aprendiendoturco.ui.screens.ContextoScreen
import cl.duoc.aprendiendoturco.viewmodel.LoginViewModel
import cl.duoc.aprendiendoturco.ui.screens.PreguntaScreen
import cl.duoc.aprendiendoturco.viewmodel.QuizViewModel

// Rutas de las pantallas, siguiendo la estructura de la guía 10.
sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Contexto : Screen("contexto")
    data object Pregunta : Screen("pregunta")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val estado by loginViewModel.estado.collectAsState()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                estado = estado,
                onCorreoChange = loginViewModel::onCorreoChange,
                onClaveChange = loginViewModel::onClaveChange,
                onIngresar = {
                    if (loginViewModel.validarLogin()) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            // Por ahora usamos la bienvenida compacta en todos los tamaños.
            HomeScreenCompact(onComenzar = {
                navController.navigate(Screen.Contexto.route) {
                    launchSingleTop = true
                }
            })
        }
        composable(Screen.Contexto.route) {
            ContextoScreen(onIniciar = {
                navController.navigate(Screen.Pregunta.route) {
                    launchSingleTop = true
                }
            })
        }
        composable(Screen.Pregunta.route) {
            val quizViewModel: QuizViewModel = viewModel()
            val quizEstado by quizViewModel.estado.collectAsState()
            PreguntaScreen(
                estado = quizEstado,
                pregunta = quizViewModel.preguntaActual,
                onResponder = quizViewModel::responder,
                onReintentar = quizViewModel::reintentar,
                onSiguiente = quizViewModel::siguientePregunta,
                onReiniciar = quizViewModel::reiniciarHistoria
            )
        }
    }
}
