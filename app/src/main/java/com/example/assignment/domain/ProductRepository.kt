package com.example.assignment.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.assignment.data.api.ProductsService
import com.example.assignment.data.models.Product
import com.example.assignment.data.pagingSource.ProductPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductRepository(
    private val api : ProductsService
) {
    private val _product = MutableStateFlow<Product?>(null)
    val product = _product.asStateFlow()


     fun getProductLIst() : Pager<Int , Product>{
        return Pager(
            initialKey = 0,
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ProductPagingSource(api)
            }
        )
    }


    suspend fun getProductsById(id: Int){
        try {
            val response = api.getProductById(id)
            if(response.isSuccessful){
                _product.value = response.body() // Use the passed id
            }
        } catch (e: Exception) {
            _product.value = null
        }
    }

}