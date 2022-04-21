package com.geekbrains.githubrx.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.githubrx.app
import com.geekbrains.githubrx.databinding.ActivityMainBinding
import com.geekbrains.githubrx.ui.main.adapter.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(app.getRepository) }
    private val adapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }

    private fun initViews() {
        viewModel.onShowList() // показываем список пользователей
        recyclerViewMain()
    }

    private fun recyclerViewMain() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter.setHasStableIds(true) // сетит список (типа внутр. diffutils)
        binding.recyclerView.adapter = adapter
    }

    private fun initOutgoingEvents() {
        binding.inputLayoutTextWindow.setEndIconOnClickListener{
            val username = binding.usernameEditText.text.toString()
            viewModel.onShowRepository(username)
        }
    }

    private fun initIncomingEvents() {
        viewModel.repos.observe(this) {
            adapter.setData(it)
        }
        progressBar()
    }

    private fun progressBar() {
        viewModel.inProgress.observe(this) { inProgress ->
            binding.inputLayoutTextWindow.isEnabled =!inProgress
            binding.recyclerView.isVisible = !inProgress
            binding.progressBar.isVisible = inProgress
        }
    }
}