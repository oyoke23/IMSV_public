package com.oyoke.imsv.games.memory_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.oyoke.imsv.MainActivity
import com.oyoke.imsv.databinding.FragmentCardBinding
import com.oyoke.imsv.util.music.PlayMusic

class CardFragment : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.updateFragment("Memory")

        val numCards = arguments?.getInt("numCards")
        val numColumns = arguments?.getInt("numColumns")
        val undiscoveredMode = arguments?.getBoolean("undiscoveredMode")
        val speed = arguments?.getInt("speed")

        binding.rvMemoryCard.layoutManager = numColumns?.let { GridLayoutManager(requireContext(), it) }
        binding.rvMemoryCard.setHasFixedSize(true)

        val cardAdapter = numCards?.let { CardRepository.getRandomCards(it) }?.let { CardAdapter(it,numCards,speed?:0, undiscoveredMode?:false,findNavController()) } ?: throw IllegalArgumentException("Invalid arguments")

        binding.rvMemoryCard.adapter = cardAdapter

        setTimer()

        return binding.root
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
        _binding = null
        (activity as? MainActivity)?.removeTimer("count")
    }
}