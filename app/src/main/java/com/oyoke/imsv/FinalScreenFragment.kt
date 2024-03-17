package com.oyoke.imsv

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyoke.imsv.databinding.FragmentFinalScreenBinding

class FinalScreenFragment : Fragment() {
    private var _binding: FragmentFinalScreenBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var fragment: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalScreenBinding.inflate(inflater, container, false)
        val type = arguments?.getString("type")!!
        val time = (activity as? MainActivity)?.getTimer()
        (activity as? MainActivity)?.hideCVTimer()

        when (type) {
            "Lose" -> {
                (activity as? MainActivity)?.updateFragment("Lose")
                binding.tvPrimary.text = getString(R.string.fragment_final_screen_lose_main_text)
                binding.tvSecondary.text =
                    getString(R.string.fragment_final_screen_lose_secondary_text)
                fragment = R.id.action_finalScreenFragment_to_gameMenuFragment
            }

            "Win - Quiz" -> {
                (activity as? MainActivity)?.updateFragment("Win")
                binding.tvPrimary.text =
                    getString(R.string.fragment_final_screen_win_quiz_main_text)
                binding.tvSecondary.text =
                    getString(R.string.fragment_final_screen_win_quiz_secondary_text)
                fragment = R.id.action_finalScreenFragment_to_quizMenuFragment
            }

            "Win - MC" -> {
                (activity as? MainActivity)?.updateFragment("Win")
                binding.tvPrimary.text =
                    getString(R.string.fragment_final_screen_win_memory_card_main_text)
                binding.tvSecondary.text =
                    getString(R.string.fragment_final_screen_win_memory_card_secondary_text, time)
                fragment = R.id.action_finalScreenFragment_to_cardMenuFragment
            }

            "Win - Puzzle" -> {
                (activity as? MainActivity)?.updateFragment("Win")
                binding.tvPrimary.text =
                    getString(R.string.fragment_final_screen_win_puzle_main_text)
                binding.tvSecondary.text =
                    getString(R.string.fragment_final_screen_win_puzzle_secondary_text, time)
                fragment = R.id.action_finalScreenFragment_to_puzzleMenuFragment
            }

            "Win - WHR" -> {
                (activity as? MainActivity)?.updateFragment("Win")
                binding.tvPrimary.text = getString(R.string.fragment_final_screen_win_whr_main_text)
                binding.tvPrimary.text =
                    getString(R.string.fragment_final_screen_win_whr_secondary_text)
                fragment = R.id.action_finalScreenFragment_to_wouldHeRatherMenuFragment
            }
        }
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener { _, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP
        }
        // Detecta el evento de toque en la vista
        binding.root.setOnClickListener {

            findNavController().navigate(fragment!!)

        }

        return binding.root
    }
}