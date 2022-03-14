package br.com.projeto.mygitrepositories.data.repository

import br.com.projeto.mygitrepositories.core.RemoteException
import br.com.projeto.mygitrepositories.data.service.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val gitHubService: GitHubService): RepoRepository {

    override suspend fun retrieveRepositoriesUser(user: String) = flow {

        try {
            val repositories = gitHubService.searchRepositoriesUser(user)
            emit(repositories)

        }catch (ex: HttpException) {
            if(ex.code() == 404) {
                throw RemoteException("Usuário não encontrado")
            } else {
                throw RemoteException(ex.message ?: "Não foi possível concluir a busca, tente novamente")
            }
        }
    }
}