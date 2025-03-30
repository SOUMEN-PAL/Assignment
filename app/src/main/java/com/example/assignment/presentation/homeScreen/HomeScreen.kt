package com.example.assignment.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignment.presentation.navigation.Screens
import com.example.assignment.presentation.viewModels.ProductViewModel
import com.example.assignment.utils.ProductsListState


@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel
) {
    LaunchedEffect(Unit) {
        productViewModel.getProductList()
    }

    val productListState = productViewModel.productListState.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (val state = productListState.value) {
            is ProductsListState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = state.message)
                }
            }
            is ProductsListState.Loading -> {
                // Full-screen loading indicator during initial load
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProductsListState.Success -> {
                val pagingItems = state.data.collectAsLazyPagingItems()

                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(pagingItems.itemCount) { index ->
                        pagingItems[index]?.let { product ->
                            HomeScreenItem(product = product, onClick = { id ->
                                navController.navigate(Screens.ProductScreen.withArgs(id.toString()))
                            })
                        }
                    }

                    // Display a loading indicator at the end of the list when appending more items
                    if (pagingItems.loadState.append is LoadState.Loading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    // Optionally, display an error message if pagination fails
                    if (pagingItems.loadState.append is LoadState.Error) {
                        val error = (pagingItems.loadState.append as LoadState.Error).error
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Error: ${error.localizedMessage}")
                            }
                        }
                    }
                }
            }
        }
    }
}
