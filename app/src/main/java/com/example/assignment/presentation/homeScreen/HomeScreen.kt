package com.example.assignment.presentation.homeScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignment.presentation.navigation.Screens
import com.example.assignment.presentation.viewModels.ProductViewModel
import com.example.assignment.utils.NetworkDialogBox
import com.example.assignment.utils.NetworkUtils
import com.example.assignment.utils.ProductsListState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel
) {
    LaunchedEffect(Unit) {
        productViewModel.getProductList()
    }
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val productListState = productViewModel.productListState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Products")
                }
            )
        }
    ) { innerPadding ->
        when (val state = productListState.value) {
            is ProductsListState.Error -> {
                showDialog = !NetworkUtils.isNetworkAvailable(context) // Update showDialog

                if (showDialog) {
                    NetworkDialogBox(
                        showDialog = showDialog,
                        onClick = {
                            if (NetworkUtils.isNetworkAvailable(context)) {
                                showDialog = false
                                productViewModel.productListState.value = ProductsListState.Loading()
                                productViewModel.getProductList()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Still no internet connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }, context = context, viewModel = productViewModel
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = state.message)
                    }
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
                if(pagingItems.itemCount == 0){
                    showDialog = !NetworkUtils.isNetworkAvailable(context) // Update showDialog

                    if (showDialog) {
                        NetworkDialogBox(
                            showDialog = showDialog,
                            onClick = {
                                if (NetworkUtils.isNetworkAvailable(context)) {
                                    showDialog = false
                                    productViewModel.productListState.value = ProductsListState.Loading()
                                    productViewModel.getProductList()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Still no internet connection",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }, context = context, viewModel = productViewModel
                        )
                    } else {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Fetching Data")
                        }
                    }
                }else {


                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(pagingItems.itemCount) { index ->
                            pagingItems[index]?.let { product ->
                                HomeScreenItem(product = product, onClick = { id ->
                                    navController.navigate(Screens.ProductScreen.withArgs(id.toString()))
                                })
                            }
                        }

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
}
