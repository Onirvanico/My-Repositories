package br.com.projeto.mygitrepositories.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.projeto.mygitrepositories.data.model.Repo
import br.com.projeto.mygitrepositories.domain.GetRepositoriesUserUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getRepositories: GetRepositoriesUserUseCase
) : ViewModel() {



    private val _repositories = MutableLiveData<State>()
    val repositories: LiveData<State> = _repositories

    fun getRepositoriesUser(user: String) {
        viewModelScope.launch {
            getRepositories(user)
                .onStart {
                    _repositories.postValue(State.Loading)
                }
                .catch {
                    _repositories.postValue(State.Failure(it))
                }
                .collect {
                    Log.i("LIST", "getRepositoriesUser: $it")
                    _repositories.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val repositories: List<Repo>) : State()
        data class Failure(val error: Throwable) : State()
    }
}