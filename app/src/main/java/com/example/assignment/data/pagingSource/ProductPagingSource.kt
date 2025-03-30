package com.example.assignment.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment.data.api.ProductsService
import com.example.assignment.data.models.Product
import com.example.assignment.data.models.Products
import kotlinx.coroutines.delay

class ProductPagingSource(
    private val apiService: ProductsService
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(state.config.pageSize)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val skip = params.key ?: 0
        val limit = params.loadSize
        delay(2000)
        return try{
            val response = apiService.getProducts(limit, skip)
            if(response.isSuccessful){
                val productResponse : Products? = response.body()
                val productsList = productResponse?.products ?: emptyList()
                val total = productResponse?.total ?: 0

                val nextKey = if(skip + limit >= total) null else skip + limit
                val prevKey = if(skip == 0) null else skip - limit
                LoadResult.Page(
                    data = productsList,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            else {
                LoadResult.Error(Exception("Error code: ${response.code()}"))
            }
        }catch (e : Exception){
            LoadResult.Error(e)
        }

    }

}