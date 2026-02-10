package com.youssef.bosta_app.navigation

object NavRoutes {
    const val GAMES_LIST = "games_list"
    const val GAME_DETAILS = "game_details"

    fun gameDetailsWithArgs(gameId: Int): String = "$GAME_DETAILS/$gameId"
}
