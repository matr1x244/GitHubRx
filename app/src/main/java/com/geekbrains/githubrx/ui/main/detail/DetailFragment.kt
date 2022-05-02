package com.geekbrains.githubrx.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geekbrains.githubrx.app
import com.geekbrains.githubrx.databinding.FragmentDetailBinding
import com.geekbrains.githubrx.domain.GitProjectEntity
import com.geekbrains.githubrx.domain.GitProjectUserDetail

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels { DetailViewModelFactory(app.getHubDetailUser) }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    companion object {
        const val ARGS_KEY = "ARGS_KEY"

        fun newInstance(gitProjectEntity: GitProjectEntity?) = DetailFragment().apply {
            arguments = Bundle()
            arguments?.putParcelable(ARGS_KEY, gitProjectEntity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initIncomingEvents()
    }

    private fun initViews() {
        detailArguments()
        val avatarUrl = "https://avatars.githubusercontent.com/u/${detailArguments()?.id}?v=4"
        if (detailArguments()?.login != null){
            binding.textNameLogin.text = detailArguments()?.login
        } else{
            binding.textNameLogin.text = detailArguments()?.name
        }
        binding.textLocation.text = detailArguments()?.location
        binding.avatarUrl.load(avatarUrl)
    }

    private fun detailArguments(): GitProjectEntity? {
        return arguments?.getParcelable(ARGS_KEY)
    }

    private fun initIncomingEvents() {
        viewModel.repos.observe(viewLifecycleOwner) {
            val username = binding.textNameLogin.toString()
           viewModel.onShowLogin(username) // подхватываем обновления и запрашиваем
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}