package br.com.projeto.mygitrepositories.data.di

import android.util.Log
import br.com.projeto.mygitrepositories.data.repository.RepoRepository
import br.com.projeto.mygitrepositories.data.repository.RepoRepositoryImpl
import br.com.projeto.mygitrepositories.data.service.GitHubService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val OK_HTTP = "OkHttp"
    private const val BASE_URL = "https://api.github.com/"

    fun load() {
        loadKoinModules(networModules() + repositoryModules())
    }

    private fun repositoryModules(): Module {
        return module {
            single<RepoRepository> {
                RepoRepositoryImpl(get())
            }
        }
    }

    private fun networModules(): Module {
        return module {

            single {
                val interceptor =  HttpLoggingInterceptor {
                    Log.d(OK_HTTP, it)
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<GitHubService>(get(), get())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)

    }
}