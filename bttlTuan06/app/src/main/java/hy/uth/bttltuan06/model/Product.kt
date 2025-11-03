package hy.uth.bttltuan06.model

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val image: String
)

data class ProductResponse(val data: Product)