package br.com.projeto.mygitrepositories

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RepositoriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RepositoriesApp)
        }
    }
}