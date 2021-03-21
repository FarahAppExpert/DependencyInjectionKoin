package com.example.koin

import com.example.koin.apikeys.UserApi
import com.example.koin.repository.UserRepository
import com.example.koin.viewmodel.ViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Model
{
    val viewModelModule = module {
        viewModel {
            ViewModel(get())
        }
    }

    val repositoryModule = module {
        single {
            UserRepository(get())
        }
    }

    val apiModule = module {
        fun provideUseApi(retrofit: Retrofit): UserApi {
            return retrofit.create(UserApi::class.java)
        }

        single { provideUseApi(get()) }
    }

    val retrofitModule = module {

        fun provideGson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        }

        fun provideHttpClient(): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()

            return okHttpClientBuilder.build()
        }

        fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
                .build()
        }

        single { provideGson() }
        single { provideHttpClient() }
        single { provideRetrofit(get(), get()) }
    }
}