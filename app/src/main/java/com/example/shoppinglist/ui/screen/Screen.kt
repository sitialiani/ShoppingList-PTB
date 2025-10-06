package com.example.shoppinglist.ui.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Setting : Screen("setting")
}
