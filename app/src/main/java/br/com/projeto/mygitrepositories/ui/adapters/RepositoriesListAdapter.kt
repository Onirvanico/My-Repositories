package br.com.projeto.mygitrepositories.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.mygitrepositories.data.model.Repo
import br.com.projeto.mygitrepositories.databinding.RepositoryItemBinding
import com.bumptech.glide.Glide

class RepositoriesListAdapter:
    ListAdapter<Repo, RepositoriesListAdapter.RepositoryViewHolder>(RepoDiff()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindRepository(getItem(position))
    }

    class RepositoryViewHolder(private val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindRepository(repository: Repo) {
            binding.textRepositoryName.text = repository.name
            binding.textRepositoryDescription.text = repository.description
            binding.languageRepository.text = repository.language
            binding.startsRepository.rating = repository.stargazersCount.toFloat()

            Glide.with(binding.root)
                .load(repository.owner.avatarURL)
                .into(binding.repositoryImage)
        }
    }
}


class RepoDiff: DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return  oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

}
