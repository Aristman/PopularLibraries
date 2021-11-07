package ru.marslab.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.marslab.popularlibraries.databinding.ItemRepoBinding
import ru.marslab.popularlibraries.domain.adapter.RepoItemView
import ru.marslab.popularlibraries.domain.presenter.IRepoListPresenter

class ReposRVAdapter(val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<ReposRVAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(
                    this
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int =
        presenter.getCount()

    inner class RepoViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root), RepoItemView {
        override var pos = -1

        override fun setName(name: String) {
            binding.tvRepoName.text = name
        }


    }
}