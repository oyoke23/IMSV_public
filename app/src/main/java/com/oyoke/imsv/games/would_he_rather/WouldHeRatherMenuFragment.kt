package com.oyoke.imsv.games.would_he_rather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyoke.imsv.MainActivity
import com.oyoke.imsv.R
import com.oyoke.imsv.databinding.FragmentWouldHeRatherMenuBinding
import com.oyoke.imsv.util.music.PlayMusic


class WouldHeRatherMenuFragment : Fragment() {
    private var _binding: FragmentWouldHeRatherMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var wouldHeRatherFragment: WouldHeRatherFragment

    private var category: String = ""
    private var answerTime: Long = 0

    private var lastClickedRadioButton: RadioButton? = null
    private var currentDifficulty = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWouldHeRatherMenuBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.updateFragment("WHR Menu")

        if (PlayMusic.getMediaPlayer() == null) {
            PlayMusic.playRandomSongs(requireContext(), "QuizWhr")
        }
        gridLayout()
        updateDifficultyText()

        setupClickListeners()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.btnLeftArrow.setOnClickListener {
            decreaseDifficulty()
        }

        binding.btnRightArrow.setOnClickListener {
            increaseDifficulty()
        }

        binding.btnStart.setOnClickListener {
            onStartButtonClicked()
        }
    }

    private fun onStartButtonClicked() {
        if (category.isNotEmpty()) {
            if (binding.rgAnswerTime.checkedRadioButtonId != -1) {
                val answerTime = when (binding.rgAnswerTime.checkedRadioButtonId) {
                    R.id.wouldHeRatherMenuRbSlow -> 8000L
                    R.id.wouldHeRatherMenuRbMedium -> 15000L
                    R.id.wouldHeRatherMenuRbFast -> 20000L
                    else -> throw IllegalStateException("Invalid radio button id")
                }

                navigateToWouldHeRatherFragment(category, answerTime)
            } else {
                showToast(getString(R.string.fragment_would_he_rather_menu_toast_select_response_time))
            }
        } else {
            showToast(getString(R.string.fragment_would_he_rather_menu_toast_select_category))
        }
    }

    private fun navigateToWouldHeRatherFragment(category: String, answerTime: Long) {
        val args = Bundle().apply {
            putString("category", category)
            putLong("answerTime", answerTime)
            putInt("difficulty", currentDifficulty)
        }

        val wouldHeRatherFragment = WouldHeRatherFragment().apply {
            arguments = args
        }

        findNavController().navigate(
            R.id.wouldHeRatherFragment, wouldHeRatherFragment.arguments
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    private fun gridLayout() {
        val gridLayout: GridLayout = binding.gridLayout

        for (i in 0 until gridLayout.childCount) {
            val child = gridLayout.getChildAt(i)

            if (child is RadioButton) {
                child.isChecked = false
                child.setOnClickListener { v -> onRadioButtonClicked(v as RadioButton) }
            }
        }
    }

    private fun onRadioButtonClicked(clickedRadioButton: RadioButton) {
        val isChecked = clickedRadioButton.isChecked
        if (isChecked) {
            // Uncheck the last radio button
            lastClickedRadioButton?.isChecked = false

            // Update the radio button clicked
            lastClickedRadioButton = clickedRadioButton
        }
        when (clickedRadioButton.id) {
            R.id.wouldHeRatherMenuRbCategory1 -> category = "Category1"
            R.id.wouldHeRatherMenuRbCategory2 -> category = "Category2"
            R.id.wouldHeRatherMenuRbCategory3 -> category = "Category3"
            R.id.wouldHeRatherMenuRbCategory4 -> category = "Category4"
            R.id.wouldHeRatherMenuRbCategory5 -> category = "Category5"
            R.id.wouldHeRatherMenuRbCategoryMixed -> category = "Mixed"
        }
    }

    private fun decreaseDifficulty() {
        if (currentDifficulty > 1) {
            currentDifficulty--
            updateDifficultyText()
        }
    }

    private fun increaseDifficulty() {
        if (currentDifficulty < 2) {
            currentDifficulty++
            updateDifficultyText()
        }
    }

    private fun updateDifficultyText() {

        when (currentDifficulty) {
            1 -> {
                binding.tvDifficultyDescription.text =
                    getString(R.string.fragment_would_he_rather_menu_difficulty_description_text_casual)

                binding.btnLeftArrow.isClickable = false
                binding.btnLeftArrow.visibility = View.INVISIBLE
                binding.btnRightArrow.isClickable = true
                binding.btnRightArrow.visibility = View.VISIBLE
            }

            2 -> {
                binding.tvDifficultyDescription.text =
                    getString(R.string.fragment_would_he_rather_menu_difficulty_description_text_challenge)
                binding.btnLeftArrow.isClickable = true
                binding.btnLeftArrow.visibility = View.VISIBLE
                binding.btnRightArrow.isClickable = false
                binding.btnRightArrow.visibility = View.INVISIBLE
            }
        }
    }
    override fun onResume() {
        super.onResume()
        PlayMusic.resume()
        gridLayout()
        category = ""
        binding.rgAnswerTime.clearCheck()
    }

    override fun onPause() {
        super.onPause()
        PlayMusic.pause()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}