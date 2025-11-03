package hy.uth.bttltuan06.repository

import hy.uth.bttltuan06.api.ProductApi
import hy.uth.bttltuan06.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {
    private val productApi: ProductApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mock.apidog.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productApi = retrofit.create(ProductApi::class.java)
    }

    suspend fun getProduct(): Product {
        return productApi.getProduct().data
    }
}