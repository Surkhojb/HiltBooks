package com.surkhojb.daggerhiltsample.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surkhojb.daggerhiltsample.data.local.db.BooksDatabase
import com.surkhojb.daggerhiltsample.data.remote.BooksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private const val BASE_URL = "https://books.googleapis.com/books/v1/"
    private const val DATABASE = "books_db"

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun providesHttpClient(loggingInterceptor: HttpLoggingInterceptor) =  OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun providesBooksApi(retrofit: Retrofit) = retrofit.create(BooksApi::class.java)

    @Provides
    @Singleton
    fun providesBooksDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context,BooksDatabase::class.java,DATABASE).build()

    @Provides
    @Singleton
    fun providesBooksDao(database: BooksDatabase) = database.booksDao()

}