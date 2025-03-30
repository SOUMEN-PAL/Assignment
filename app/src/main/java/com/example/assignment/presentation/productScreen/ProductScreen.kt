package com.example.assignment.presentation.productScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.assignment.presentation.viewModels.ProductViewModel
import com.example.assignment.utils.ProductDataState

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    id: Int,
    viewModel: ProductViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getProductById(id)
    }

    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when(state.value){
            is ProductDataState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = (state.value as ProductDataState.Error).message.toString())
                }
            }
            is ProductDataState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProductDataState.Success -> {
                val product = (state.value as ProductDataState.Success).data
                ProductScreenData(product = product , navController)
            }
        }
    }
}


