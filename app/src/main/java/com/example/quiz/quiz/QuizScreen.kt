package com.example.quiz.quiz

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quiz.home.HomeScreenViewModel
import com.example.quiz.home.TelaInicial
import com.example.quiz.quiz.PrimeiraPergunta.PrimeiraPerguntaScreen
import com.example.quiz.quiz.SegundaPergunta.SegundaPerguntaScreen

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier,
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(400)
            ) + fadeOut(
                animationSpec = tween(400)
            )
        },
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(400)
            )
        }
    ) {
        composable(route = "home") {
            val homeScreenViewModel: HomeScreenViewModel = viewModel()

            TelaInicial(
                homeScreenViewModel = homeScreenViewModel,
                navController = navController
            )
        }

        composable(
            route = "primeira_pergunta/{nome}",
            arguments = listOf(
                navArgument(name = "nome") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome").orEmpty()

            PrimeiraPerguntaScreen(
                nome = nome
            )
        }

        composable(
            route = "segunda_pergunta/{nome}",
            arguments = listOf(
                navArgument(name = "nome") {
                    type = NavType.StringType
                }
            )
        ) {
                backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome").orEmpty()

            SegundaPerguntaScreen(
                nome = nome
            )
        }
    }
}