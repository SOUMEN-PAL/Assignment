package com.example.assignment.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.assignment.data.models.Product
import com.example.assignment.domain.ProductRepository
import com.example.assignment.utils.ProductDataState
import com.example.assignment.utils.ProductsListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val state = MutableStateFlow<ProductDataState>(ProductDataState.Loading())

    val productListState = MutableStateFlow<ProductsListState>(ProductsListState.Loading())

    fun getProductList(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getProductLIst().flow.cachedIn(viewModelScope)
            productListState.value = ProductsListState.Success(data)
        }
    }


    fun getProductById(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repository.getProductsById(id)
                val product = repository.product.value
                if(product != null){
                    state.value = ProductDataState.Success(product)
                }
                else{
                    state.value = ProductDataState.Error("Product not found")
                }
            }catch (e : Exception){
                state.value = ProductDataState.Error(e.message.toString())
            }
        }
    }

}