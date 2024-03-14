package com.oyoke.iara.games.would_he_rather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyoke.iara.MainActivity
import com.oyoke.iara.R
import com.oyoke.iara.databinding.FragmentWouldHeRatherMenuBinding
import com.oyoke.iara.util.music.PlayMusic


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

        binding.btnLeftArrow.setOnClickListener {
            decreaseDifficulty()
        }

        binding.btnRightArrow.setOnClickListener {
            increaseDifficulty()
        }
        binding.btnStart.setOnClickListener {
            if (category.isNotEmpty()) {
                if (binding.rgAnswerTime.checkedRadioButtonId != -1) {

                    when (binding.rgAnswerTime.checkedRadioButtonId) {
                        R.id.wouldHeRatherMenuRbSlow -> {
                            answerTime = 8000

                        }
                        R.id.wouldHeRatherMenuRbMedium -> {
                            answerTime = 15000
                        }
                        R.id.wouldHeRatherMenuRbFast -> {
                            answerTime = 20000
                        }
                    }
                    wouldHeRatherFragment = WouldHeRatherFragment().apply {
                        arguments = Bundle().apply {
                            putString("category", category)
                            putLong("answerTime", answerTime)
                            putInt("difficulty", currentDifficulty)
                        }
                    }

                    findNavController().navigate(
                        R.id.wouldHeRatherFragment, wouldHeRatherFragment.arguments
                    )

                } else {
                    Toast.makeText(context, "Selecciona el tiempo de respuesta", Toast.LENGTH_SHORT)
                        .show()
                }

            } else {
                //mensaje de seleccionar una categoria
                Toast.makeText(context, "Selecciona una categoria", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
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
            // Desmarcar el último RadioButton si existe
            lastClickedRadioButton?.isChecked = false
            // Actualizar el último RadioButton con el recién clicado
            lastClickedRadioButton = clickedRadioButton
        }
        when (clickedRadioButton.id) {
            R.id.wouldHeRatherMenuRbCasual -> category = "Casual"
            R.id.wouldHeRatherMenuRbFunny -> category = "Funny"
            R.id.wouldHeRatherMenuRbGore -> category = "Gore"
            R.id.wouldHeRatherMenuRbCouple -> category = "Couple"
            R.id.wouldHeRatherMenuRbSexual -> category = "Sexual"
            R.id.wouldHeRatherMenuRbMixed -> category = "Mixed"
        }
    }

    private fun decreaseDifficulty() {
        if (currentDifficulty > 1) {
            currentDifficulty--
            updateDifficultyText()
        }
    }

    private fun increaseDifficulty() {
        // Puedes ajustar el límite superior según tu necesidad
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