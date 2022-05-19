package com.geekbrains.githubrx.ui.main.main.adapter.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.geekbrains.githubrx.R
import com.geekbrains.githubrx.databinding.RecycleItemLoginListBinding
import com.geekbrains.githubrx.domain.GitProjectEntity

class ViewHolderListUsers(itemView: View) : ViewHolder(itemView) {
    private val binding = RecycleItemLoginListBinding.bind(itemView)

    companion object {
        fun createView(parent: ViewGroup): ViewHolderListUsers {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_item_login_list, parent, false)
            return ViewHolderListUsers(view)
        }
    }

    override fun bind(item: GitProjectEntity, listener: (GitProjectEntity) -> Unit) {
        val avatarUrl = "https://avatars.githubusercontent.com/u/${item.id}?v=4"
        binding.avatarUrl.load(avatarUrl) {
            precision(Precision.EXACT)
            scale(Scale.FILL)
        }
        binding.itemGitId.text = item.id.toString()
        binding.itemGitLogin.text = item.login
        binding.cardViewLoginContainer.setOnClickListener {
            listener.invoke(item)
        }
    }
}