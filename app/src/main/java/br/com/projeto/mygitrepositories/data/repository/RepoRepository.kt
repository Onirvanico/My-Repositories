package br.com.projeto.mygitrepositories.data.repository

import br.com.projeto.mygitrepositories.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun retrieveRepositoriesUser(user: String): Flow<List<Repo>>
}