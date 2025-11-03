package hy.uth.bttltuan06.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hy.uth.bttltuan06.model.Product
import hy.uth.bttltuan06.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            try {
                _product.value = repository.getProduct()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}