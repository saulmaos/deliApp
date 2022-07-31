package com.saulmaos.deliapp.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.saulmaos.deliapp.databinding.FragmentMealsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : Fragment() {
    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MealsViewModel by viewModels()
    private lateinit var mealsAdapter: MealsAdapter
    private val args: MealsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealsAdapter = MealsAdapter {
            val action = MealsFragmentDirections.actionMealsFragmentToMealsDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.rvMeals.adapter = mealsAdapter
        observeMealsState()

        viewModel.getMeals(args.category)
    }

    private fun observeMealsState() {
        viewModel.mealsState.observe(viewLifecycleOwner) {
            when(it) {
                MealsState.Failure -> {
                    binding.tvError.visibility = View.VISIBLE
                    binding.rvMeals.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                is MealsState.Success -> {
                    mealsAdapter.submitList(it.meals)
                    binding.rvMeals.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                MealsState.Loading -> {
                    binding.tvError.visibility = View.GONE
                    binding.rvMeals.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}