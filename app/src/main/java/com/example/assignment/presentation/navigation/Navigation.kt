package com.example.assignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment.presentation.homeScreen.HomeScreen
import com.example.assignment.presentation.productScreen.ProductScreen
import com.example.assignment.presentation.viewModels.ProductViewModel

@Composable
fun Navigation(
    productViewModel: ProductViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(route = Screens.HomeScreen.route){
            HomeScreen(navController , productViewModel)
        }

        composable(
            route = Screens.ProductScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            it.arguments?.getInt("id")?.let { id ->
                ProductScreen(id = id , viewModel = productViewModel , navController = navController)
            }
        }

    }
}