package hy.uth.bttltuan06.api

import hy.uth.bttltuan06.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("m1/890655-872447-default/v2/product")
    suspend fun getProduct(): ProductResponse
}