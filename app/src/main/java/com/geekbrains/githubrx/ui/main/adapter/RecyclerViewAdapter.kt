package com.geekbrains.githubrx.ui.main.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.ui.main.adapter.RecyclerViewAdapter.DbConstantsViewHolder.ListUsers
import com.geekbrains.githubrx.ui.main.adapter.RecyclerViewAdapter.DbConstantsViewHolder.RepositoryUser
import com.geekbrains.githubrx.ui.main.adapter.viewHolder.BaseViewHolder
import com.geekbrains.githubrx.ui.main.adapter.viewHolder.ViewHolderListUsers
import com.geekbrains.githubrx.ui.main.adapter.viewHolder.ViewHolderRepositoryUser


class RecyclerViewAdapter() : RecyclerView.Adapter<BaseViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            ListUsers -> {
                ViewHolderListUsers.createView(parent)
            }
            else -> {
                ViewHolderRepositoryUser.createView(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
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

//    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        abstract fun bind(users: GitProjectEntity)
//    }

//    inner class ViewHolderListUsers(view: View) : BaseViewHolder(view) {
//        override fun bind(users: GitProjectEntity) {
//            RecycleItemLoginListBinding.bind(itemView).apply {
//                Log.d("avatarUrl", "avatarUrl")
//                val avatarUrl = "https://avatars.githubusercontent.com/u/${users.id}?v=4"
//               avatarUrl.load(avatarUrl) {
//                    precision(Precision.EXACT)
//                    scale(Scale.FILL)
//                }
//
//                itemGitId.text = users.id.toString()
//                itemGitLogin.text = users.login
//                cardViewMovie.setOnClickListener {
//                    Toast.makeText(
//                        itemView.context,
//                        "клик: ${itemGitLogin.text}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
//
//
//    inner class ViewHolderRepositoryUser(view: View) : BaseViewHolder(view) {
//        override fun bind(users: GitProjectEntity) {
//            RecycleItemRepositoryDetailBinding.bind(itemView).apply {
//                val avatarUrl = "https://avatars.githubusercontent.com/u/${users.id}?v=4"
//                binding.avatarUrl.load(avatarUrl) {
//                    precision(Precision.EXACT)
//                    scale(Scale.FILL)
//                }
//                itemGitRepoId.text = users.id.toString()
//                itemGitRepoName.text = users.name
//                cardViewMovie.setOnClickListener {
//                    Toast.makeText(
//                        itemView.context,
//                        "клик: ${itemGitRepoName.text}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
}