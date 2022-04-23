package com.geekbrains.githubrx.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.geekbrains.githubrx.app
import com.geekbrains.githubrx.databinding.FragmentDetailBinding
import com.geekbrains.githubrx.domain.GitProjectEntity

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels { DetailViewModelFactory(app.getRepository) }

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

        detailLoadingFragment()

    }

    private fun detailLoadingFragment() {
        val test = detailTestingArguments()
        val username = binding.usernameEditText.text.toString()
        if (test == null) {
            binding.textTesting.text
        } else {
            viewModel.repos.observe(viewLifecycleOwner) {
                binding.textTesting.text = binding.usernameEditText.toString()
            }
        }
        viewModel.onShowLogin(username)
    }


    private fun detailTestingArguments(): GitProjectEntity? {
        return arguments?.getParcelable(ARGS_KEY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}