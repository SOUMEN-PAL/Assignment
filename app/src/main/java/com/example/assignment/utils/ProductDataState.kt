package com.example.assignment.utils

import com.example.assignment.data.models.Product

sealed class ProductDataState {
    class Success(val data : Product) : ProductDataState()
    class Loading() : ProductDataState()
    class Error(val message : String) : ProductDataState()
}