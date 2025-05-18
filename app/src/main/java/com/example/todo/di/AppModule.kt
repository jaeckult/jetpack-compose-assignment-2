package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.local.TodoDao
import com.example.todo.data.local.TodoDatabase
import com.example.todo.data.remote.ApiServiceInterface
import com.example.todo.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface =
        retrofit.create(ApiServiceInterface::class.java)
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TodoDatabase =
        Room.databaseBuilder(appContext, TodoDatabase::class.java, "todo_db").build()

    @Provides
    fun provideTodoDao(db: TodoDatabase): TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(apiService: ApiServiceInterface, todoDao: TodoDao): TodoRepository {
        return TodoRepository(apiService, todoDao)
    }
}