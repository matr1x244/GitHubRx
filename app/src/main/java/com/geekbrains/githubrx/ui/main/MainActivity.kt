package com.geekbrains.githubrx.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubrx.R
import com.geekbrains.githubrx.databinding.ActivityMainBinding
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.ui.main.detail.DetailFragment
import com.geekbrains.githubrx.ui.main.fragment.FragmentMain
import com.geekbrains.githubrx.ui.main.fragment.MainViewModel

class MainActivity : AppCompatActivity(), MainViewModel.Controller {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.containerMain)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, FragmentMain.newInstance())
                .commitNow()
        }
    }

    override fun openDetailFragment(gitProjectEntity: GitProjectEntity) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container_main, DetailFragment.newInstance(gitProjectEntity))
            .commit()
    }
}