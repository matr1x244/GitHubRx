package com.geekbrains.githubrx.ui.main.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.githubrx.R
import com.geekbrains.githubrx.app
import com.geekbrains.githubrx.data.local.LocalRequestImpl
import com.geekbrains.githubrx.data.retrofit.RetrofitRequestImpl
import com.geekbrains.githubrx.databinding.FragmentMainBinding
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.RepositoryList
import com.geekbrains.githubrx.ui.main.detail.DetailFragment
import com.geekbrains.githubrx.ui.main.main.adapter.RecyclerViewAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class FragmentMain : Fragment() {

    companion object {
        fun newInstance() = FragmentMain()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: MainViewModel by viewModel()

    @Inject
    lateinit var getRepositoryList: RepositoryList
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(getRepositoryList) }

    private val controller by lazy { activity as MainViewModel.Controller }

    private val adapter = RecyclerViewAdapter {
        controller.openDetailFragment(it)
        Toast.makeText(context, it.login, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is MainViewModel.Controller) {
            throw IllegalStateException("Activity должна наследоваться от Controller")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        app.appDependenciesComponent.inject(this) //получаем доступ из app к appDependenciesComponent

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }

    private fun initViews() {
        viewModel.onShowList() // показываем список пользователей
        recyclerViewMain()
    }

    private fun recyclerViewMain() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//        adapter.setHasStableIds(true) // сетит список (типа внутр. diffutils)
        binding.recyclerView.adapter = adapter

        binding.usernameEditText.setText(app.appDependenciesComponent.getDefaultUserName())
    }

    private fun initOutgoingEvents() {
        binding.inputLayoutTextWindow.setEndIconOnClickListener {
            val username = binding.usernameEditText.text.toString()
            if (username == null) {
                Toast.makeText(context, "Введите nickname", Toast.LENGTH_LONG).show()
            } else {
                viewModel.onShowRepository(username)
            }
        }
        binding.inputLayoutTextWindow.setStartIconOnClickListener {
            activity?.supportFragmentManager?.let { fragment ->
                fragment.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_main, DetailFragment.newInstance(null))
                    .commit()
            }
        }
    }

    private fun initIncomingEvents() {
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.setData(it) // подхватываем обновления и запрашиваем
        }
        progressBar()
    }

    private fun progressBar() {
        viewModel.inProgress.observe(viewLifecycleOwner) { inProgress ->
            binding.inputLayoutTextWindow.isEnabled = !inProgress
            binding.recyclerView.isVisible = !inProgress
            binding.progressBar.isVisible = inProgress
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}