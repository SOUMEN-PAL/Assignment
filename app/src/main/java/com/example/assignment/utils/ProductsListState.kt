package com.example.assignment.utils

import androidx.paging.PagingData
import com.example.assignment.data.models.Product
import kotlinx.coroutines.flow.Flow

sealed class ProductsListState {
    class Success(val data : Flow<PagingData<Product>>) : ProductsListState()
    class Loading() : ProductsListState()
    class Error(val message : String) : ProductsListState()

}