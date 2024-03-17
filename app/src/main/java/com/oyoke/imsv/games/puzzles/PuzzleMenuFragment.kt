package com.oyoke.imsv.games.puzzles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.oyoke.imsv.MainActivity
import com.oyoke.imsv.R
import com.oyoke.imsv.databinding.FragmentPuzzleMenuBinding
import com.oyoke.imsv.util.music.PlayMusic

class PuzzleMenuFragment : Fragment(), PuzzleMenuAdapter.OnPuzzleItemClickListener {

    private var _binding: FragmentPuzzleMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var puzzleMenuAdapter: PuzzleMenuAdapter

    private val puzzleFragment = PuzzleFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPuzzleMenuBinding.inflate(inflater, container, false)

        (activity as? MainActivity)?.updateFragment("Puzzle Menu")

        if(PlayMusic.getMediaPlayer() == null){
            PlayMusic.playRandomSongs(requireContext(), "Puzzle")
        }

        val updatedPuzzles = PuzzleRepository.getPuzzles(requireContext())

        updatedPuzzles.forEachIndexed { index, puzzle ->
            puzzle.isClickable = index == 0 || (index > 0 && updatedPuzzles[index - 1].isSolved)
        }

        puzzleMenuAdapter = PuzzleMenuAdapter(updatedPuzzles, this)
        binding.rvPuzzlesList.adapter = puzzleMenuAdapter
        binding.rvPuzzlesList.layoutManager = GridLayoutManager(requireContext(), 1)

        return binding.root
    }

    override fun onPuzzleItemClick(puzzle: PuzzleModel) {
        PlayMusic.pause()

        puzzleFragment.arguments = Bundle().apply {
            putInt("id", puzzle.id)
            putString("question", puzzle.question)
            putString("answer", puzzle.answer)
            putInt("image", puzzle.image)
            putString("title", puzzle.title)
        }

        val navController = findNavController()
        navController.navigate(R.id.puzzleFragment, puzzleFragment.arguments)

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
        _binding = null
    }
}
