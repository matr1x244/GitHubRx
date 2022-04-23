package com.geekbrains.githubrx.ui.main.adapter.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.geekbrains.githubrx.R
import com.geekbrains.githubrx.databinding.RecycleItemRepositoryDetailBinding
import com.geekbrains.githubrx.domain.GitProjectEntity

class ViewHolderRepositoryUser(itemView: View) : BaseViewHolder(itemView) {
    private val binding = RecycleItemRepositoryDetailBinding.bind(itemView)

    companion object {
        fun createView(parent: ViewGroup): ViewHolderRepositoryUser {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_item_repository_detail, parent, false)
            return ViewHolderRepositoryUser(view)
        }
    }

    override fun bind(item: GitProjectEntity, listener: (GitProjectEntity) -> Unit) {
        val avatarUrl = "https://avatars.githubusercontent.com/u/${item.id}?v=4"
        binding.avatarUrl.load(avatarUrl) {
            precision(Precision.EXACT)
            scale(Scale.FILL)
        }
        binding.itemGitRepoId.text = item.id.toString()
        binding.itemGitRepoName.text = item.name
        binding.cardViewRepositoryContainer.setOnClickListener {
            listener.invoke(item)
        }
    }
}