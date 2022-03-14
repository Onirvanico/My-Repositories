package br.com.projeto.mygitrepositories.data.service

import br.com.projeto.mygitrepositories.data.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun searchRepositoriesUser(@Path("user") user: String): List<Repo>
}