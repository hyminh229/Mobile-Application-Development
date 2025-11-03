package hy.uth.btvntuan06.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hy.uth.btvntuan06.data.api.TaskApiService
import hy.uth.btvntuan06.data.repository.TaskRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Dependency Injection module để khởi tạo các dependencies
 * Tạo Retrofit instance, API service, và Repository
 */
object NetworkModule {
    private const val BASE_URL = "https://amock.io/api/researchUTH/"
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }
    
    private val contentType = "application/json".toMediaType()
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
    
    val apiService: TaskApiService = retrofit.create(TaskApiService::class.java)
    
    val repository: TaskRepository = TaskRepository(apiService)
}

