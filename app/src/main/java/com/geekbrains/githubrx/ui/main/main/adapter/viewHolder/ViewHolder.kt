package com.geekbrains.githubrx.ui.main.main.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubrx.domain.GitProjectEntity

abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(users: GitProjectEntity, listener: (GitProjectEntity) -> Unit)
}