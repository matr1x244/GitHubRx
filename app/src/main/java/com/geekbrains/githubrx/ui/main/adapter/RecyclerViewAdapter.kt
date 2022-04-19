package com.geekbrains.githubrx.ui.main.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.geekbrains.githubrx.databinding.RecycleItemLoginListBinding
import com.geekbrains.githubrx.databinding.RecycleItemRepositoryDetailBinding
import com.geekbrains.githubrx.domain.GitProjectEntity


class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder>() {

    private var userList: MutableList<GitProjectEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUsersList: List<GitProjectEntity>) {
        userList.clear()
        userList.addAll(newUsersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            0 -> {
                val binding = RecycleItemLoginListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewHolderListUsers(binding.root)
            } else -> {
                val binding = RecycleItemRepositoryDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewHolderRepositoryUser(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): GitProjectEntity = userList[position]

    override fun getItemCount() = userList.size

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(users: GitProjectEntity)
    }

    inner class ViewHolderListUsers(view: View) : BaseViewHolder(view) {
        override fun bind(users: GitProjectEntity) {
           RecycleItemLoginListBinding.bind(itemView).apply {
               Log.d("avatarUrl", "avatarUrl")
//                val avatarUrl = "https://avatars.githubusercontent.com/u/${users.id}?v=4"
//               bindingListUser.avatarUrl.load(avatarUrl) {
//                    precision(Precision.EXACT)
//                    scale(Scale.FILL)
//                }
               Log.d("itemGitId", "itemGitId")
                itemGitId.text = users.id.toString()
               Log.d("itemGitLogin", "itemGitLogin")
                itemGitLogin.text = users.login

                cardViewMovie.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "клик: ${itemGitLogin.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    inner class ViewHolderRepositoryUser(view: View) : BaseViewHolder(view) {
        override fun bind(users: GitProjectEntity) {
            RecycleItemRepositoryDetailBinding.bind(itemView).apply {
//                val avatarUrl = "https://avatars.githubusercontent.com/u/${users.id}?v=4"
//                bindingRepositoryUser.avatarUrl.load(avatarUrl) {
//                    precision(Precision.EXACT)
//                    scale(Scale.FILL)
//                }
                Log.d("itemGitRepoId", "itemGitRepoId")
                itemGitRepoId.text = users.id.toString()
                Log.d("itemGitRepoName", "itemGitRepoName")
                itemGitRepoName.text = users.name

                cardViewMovie.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "клик: ${itemGitRepoName.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}