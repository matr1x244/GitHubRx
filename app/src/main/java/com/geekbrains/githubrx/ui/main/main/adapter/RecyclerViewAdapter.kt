package com.geekbrains.githubrx.ui.main.main.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.ui.main.main.adapter.RecyclerViewAdapter.DbConstantsViewHolder.ListUsers
import com.geekbrains.githubrx.ui.main.main.adapter.RecyclerViewAdapter.DbConstantsViewHolder.RepositoryUser
import com.geekbrains.githubrx.ui.main.main.adapter.viewHolder.ViewHolder
import com.geekbrains.githubrx.ui.main.main.adapter.viewHolder.ViewHolderListUsers
import com.geekbrains.githubrx.ui.main.main.adapter.viewHolder.ViewHolderRepositoryUser


class RecyclerViewAdapter(private val itemClick : (GitProjectEntity) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    object DbConstantsViewHolder {
        const val ListUsers = 0
        const val RepositoryUser = 1
    }

    private var userList: MutableList<GitProjectEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUsersList: List<GitProjectEntity>) {
        userList.clear()
        userList.addAll(newUsersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ListUsers -> {
                ViewHolderListUsers.createView(parent)
            }
            else -> {
                ViewHolderRepositoryUser.createView(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClick)
    }

    override fun getItemViewType(position: Int): Int { // это для двух ViewHolder метод переключения
        val zero = null
        return if (userList[position].login == zero) {
            RepositoryUser // ViewHolderRepositoryUser
        } else {
            ListUsers // ViewHolderListUsers
        }
    }

    private fun getItem(position: Int): GitProjectEntity = userList[position]

    override fun getItemCount() = userList.size
}