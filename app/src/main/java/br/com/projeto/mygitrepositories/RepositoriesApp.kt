package br.com.projeto.mygitrepositories

import android.app.Application
import br.com.projeto.mygitrepositories.data.di.DataModule
import br.com.projeto.mygitrepositories.domain.di.DomainModule
import br.com.projeto.mygitrepositories.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RepositoriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RepositoriesApp)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}