package br.com.projeto.mygitrepositories.domain.di

import br.com.projeto.mygitrepositories.domain.GetRepositoriesUserUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory {
                GetRepositoriesUserUseCase(get())
            }
        }
    }
}