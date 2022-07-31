package com.saulmaos.deliapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saulmaos.deliapp.R
import com.saulmaos.deliapp.databinding.FragmentCategoriesBinding
import com.saulmaos.deliapp.utils.RANDOM_MEAL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private val viewModel: CategoriesViewModel by viewModels()
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesAdapter = CategoriesAdapter {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToMealsFragment(it)
            findNavController().navigate(action)
        }
        binding.rvCategories.adapter = categoriesAdapter
        observeCategories()

        viewModel.getCategories()
        binding.fabRandom.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToMealsDetailFragment(RANDOM_MEAL)
            findNavController().navigate(action)
        }
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner) {
            when (it) {
                CategoriesState.Failure -> {
                    binding.tvError.visibility = View.VISIBLE
                    binding.rvCategories.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                is CategoriesState.Success -> {
                    categoriesAdapter.submitList(it.categories)
                    binding.rvCategories.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                CategoriesState.Loading -> {
                    binding.tvError.visibility = View.GONE
                    binding.rvCategories.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}
