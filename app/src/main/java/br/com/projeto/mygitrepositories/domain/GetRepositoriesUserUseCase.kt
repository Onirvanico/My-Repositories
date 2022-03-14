package br.com.projeto.mygitrepositories.domain

import br.com.projeto.mygitrepositories.core.UseCase
import br.com.projeto.mygitrepositories.data.model.Repo
import br.com.projeto.mygitrepositories.data.repository.RepoRepository
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUserUseCase(private val repository: RepoRepository):
    UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.retrieveRepositoriesUser(param)
    }
}