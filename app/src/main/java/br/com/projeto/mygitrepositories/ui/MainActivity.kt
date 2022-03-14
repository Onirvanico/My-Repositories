package br.com.projeto.mygitrepositories.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.projeto.mygitrepositories.R
import br.com.projeto.mygitrepositories.core.createDialog
import br.com.projeto.mygitrepositories.core.createProgressDialog
import br.com.projeto.mygitrepositories.core.hidSoftKeyBoard
import br.com.projeto.mygitrepositories.databinding.ActivityMainBinding
import br.com.projeto.mygitrepositories.presentation.MainViewModel
import br.com.projeto.mygitrepositories.ui.adapters.RepositoriesListAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { RepositoriesListAdapter() }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getRepositoriesUser("Onirvanico")

        binding.recyclerRepositories.setHasFixedSize(true)
        binding.recyclerRepositories.adapter = adapter

        retrieveRepositories()
    }

    private fun retrieveRepositories() {
        viewModel.repositories.observe(this) {
            when (it) {

                MainViewModel.State.Loading -> {
                    dialog.show()
                }

                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.repositories)
                }

                is MainViewModel.State.Failure -> {
                    dialog.dismiss()

                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(result: String): Boolean {

                binding.root.hidSoftKeyBoard()

                result?.let {
                    viewModel.getRepositoriesUser(it)
                }

                return true;

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}