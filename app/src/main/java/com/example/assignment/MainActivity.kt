package com.example.assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignment.presentation.homeScreen.HomeScreen
import com.example.assignment.presentation.homeScreen.HomeScreenItem
import com.example.assignment.presentation.navigation.Navigation
import com.example.assignment.presentation.viewModels.ProductViewModel
import com.example.assignment.presentation.viewModels.ProductViewModelFactory
import com.example.assignment.ui.theme.AssignmentTheme
import com.example.assignment.utils.ProductDataState
import com.example.assignment.utils.ProductsListState

class MainActivity : ComponentActivity() {
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = (application as ProductApplication).repository
        setContent {
            productViewModel = viewModel(factory = ProductViewModelFactory(repository))

            AssignmentTheme {
                Navigation(productViewModel)
            }


        }
    }
}

