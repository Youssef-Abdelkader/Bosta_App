package com.youssef.bosta_app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.youssef.bosta_app.ui.gameslist.GamesListScreen
import com.youssef.bosta_app.ui.gamedetails.GameDetailsScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.GAMES_LIST
    ) {
        composable(NavRoutes.GAMES_LIST) {
            val viewModel = hiltViewModel<com.youssef.bosta_app.ui.gameslist.GamesListViewModel>()
            GamesListScreen(
                viewModel = viewModel,
                onGameClicked = { gameId ->
                    navController.navigate(NavRoutes.gameDetailsWithArgs(gameId))
                }
            )
        }

        composable(
            route = "${NavRoutes.GAME_DETAILS}/{gameId}",
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
            val viewModel = hiltViewModel<com.youssef.bosta_app.ui.gamedetails.GameDetailsViewModel>()
            GameDetailsScreen(
                viewModel = viewModel,
                gameId = gameId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
