package com.geekbrains.githubrx.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.geekbrains.githubrx.databinding.RecycleItemBinding
import com.geekbrains.githubrx.domain.GitProjectEntity


class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var userList: MutableList<GitProjectEntity> = ArrayList()
    private lateinit var binding: RecycleItemBinding // макет

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUsersList: List<GitProjectEntity>) {
        userList.clear()
        userList.addAll(newUsersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): GitProjectEntity = userList[position]

    override fun getItemCount() = userList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(users: GitProjectEntity) {
            val avatarUrl = "https://avatars.githubusercontent.com/u/${users.id}?v=4"
            binding.avatarUrl.load(avatarUrl) {
                precision(Precision.EXACT)
                scale(Scale.FILL)
            }
            binding.itemGitRepoId.text = users.id.toString()
            binding.itemGitRepoName.text = users.name
            binding.itemGitLogin.text = users.login

            binding.cardViewMovie.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "клик: ${binding.itemGitRepoName.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}