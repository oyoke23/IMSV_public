package com.oyoke.iara.games.puzzles

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.oyoke.iara.MainActivity
import com.oyoke.iara.R
import com.oyoke.iara.databinding.FragmentPuzzleBinding
import com.oyoke.iara.util.music.PlayMusic
import java.util.Locale
import androidx.navigation.fragment.findNavController

class PuzzleFragment : Fragment() {
    private var _binding: FragmentPuzzleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPuzzleBinding.inflate(inflater, container, false)

        (activity as? MainActivity)?.updateFragment("Puzzle")

        val id = arguments?.getInt("id")
        val question = arguments?.getString("question")
        val answer = arguments?.getString("answer")
        val image = arguments?.getInt("image")
        val title = arguments?.getString("title")

        binding.tvPuzzleTitle.text = title
        binding.tvPuzzleQuestion.text = question
        binding.ivPuzzleImage.setImageResource(image!!)

        setTimer()

        binding.etAnswer.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                checkAnswer(answer, id)
                return@setOnEditorActionListener true
            }
            false
        }
        binding.btnAnswer.setOnClickListener {
            checkAnswer(answer, id)
        }

        return binding.root
    }

    private fun checkAnswer(correctAnswer: String?, puzzleId: Int?) {
        val userAnswer = binding.etAnswer.text.toString().trim().uppercase(Locale.ROOT)
        val correctAnswerLowerCase = correctAnswer?.trim()?.uppercase(Locale.ROOT)

        if (userAnswer == correctAnswerLowerCase) {
            PuzzleRepository.markPuzzleAsSolved(requireContext(), puzzleId!!)
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etAnswer.windowToken, 0)

            val arguments = Bundle().apply {
                putString("type", "Win - Puzzle")
                putString("time", (activity as? MainActivity)?.getTimer())
            }

            findNavController().navigate(R.id.finalScreenFragment, arguments)
        }
    }
    private fun setTimer() {
        (activity as? MainActivity)?.removeTimer("count")
        (activity as? MainActivity)?.updateTimer(null, "count")
    }

    override fun onResume() {
        super.onResume()
        PlayMusic.resume()
    }

    override fun onPause() {
        super.onPause()
        PlayMusic.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? MainActivity)?.removeTimer("count")
        _binding = null
    }
}
