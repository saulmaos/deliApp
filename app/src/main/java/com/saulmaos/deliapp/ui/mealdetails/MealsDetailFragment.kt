package com.saulmaos.deliapp.ui.mealdetails

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.saulmaos.deliapp.data.api.responses.MealDetailResponse
import com.saulmaos.deliapp.databinding.FragmentMealsDetailBinding
import com.saulmaos.deliapp.utils.YOUTUBE_PACKAGE
import com.saulmaos.deliapp.utils.useGlide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsDetailFragment : Fragment() {
    private val args: MealsDetailFragmentArgs by navArgs()
    private var _binding: FragmentMealsDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MealDetailsViewModel by viewModels()
    private lateinit var ingredientsAdapter: ChipsAdapter
    private lateinit var measuresAdapter: ChipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientsAdapter = ChipsAdapter()
        measuresAdapter = ChipsAdapter()
        binding.rvIngredients.adapter = ingredientsAdapter
        binding.rvMeasures.adapter = measuresAdapter
        observeState()
        viewModel.getMealDetails(args.mealId)
    }

    private fun observeState() {
        viewModel.mealDetailsState.observe(viewLifecycleOwner) {
            when (it) {
                MealDetailsState.Failure -> {
                    binding.group.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                MealDetailsState.Loading -> {
                    binding.group.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MealDetailsState.Success -> {
                    setViews(it.details, it.ingredients, it.measures)
                    binding.group.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setViews(
        details: MealDetailResponse,
        ingredients: List<String>,
        measures: List<String>
    ) {
        with(binding) {
            ivImage.useGlide(details.mealThumb)
            tvMeal.text = details.meal
            ivYoutube.setOnClickListener {
                openYoutube(details.youtube)
            }
            ivWeb.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(details.source)))
            }
            ingredientsAdapter.submitList(ingredients)
            measuresAdapter.submitList(measures)
            tvInstructions.text = details.instructions
        }
    }

    private fun openYoutube(url: String) {
        try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            intent.`package` = YOUTUBE_PACKAGE
            context?.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }
}
