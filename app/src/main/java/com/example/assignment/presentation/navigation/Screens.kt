package com.example.assignment.presentation.navigation

sealed class Screens(val route : String) {
    data object HomeScreen : Screens("HomeScreen")
    data object ProductScreen : Screens("ProductScreen")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}