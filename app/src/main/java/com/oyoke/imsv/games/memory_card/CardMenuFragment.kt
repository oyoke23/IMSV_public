package com.oyoke.imsv.games.memory_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyoke.imsv.MainActivity
import com.oyoke.imsv.R
import com.oyoke.imsv.databinding.FragmentCardMenuBinding
import com.oyoke.imsv.util.music.PlayMusic

class CardMenuFragment : Fragment() {

    private var _binding: FragmentCardMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardFragment: CardFragment
    private var numCardsAndColumns: Pair<Int, Int> = Pair(-1, -1)
    private var speed: Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardMenuBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.updateFragment("Memory Menu")

        if (PlayMusic.getMediaPlayer() == null) {
            PlayMusic.playRandomSongs(requireContext(), "Card")
        }

        setupButtonClickListeners()

        binding.cbUndiscoveredMode.setOnClickListener {
            if (binding.cbUndiscoveredMode.isChecked) {
                binding.cbUndiscoveredMode.text = "✓"
            } else {
                binding.cbUndiscoveredMode.text = ""
            }
        }

        return binding.root
    }
    private fun setupButtonClickListeners() {
        binding.btnStart.setOnClickListener {
            numCardsAndColumns = when (binding.rgBoardSize.checkedRadioButtonId) {
                R.id.cardMenuRb3x4 -> Pair(6, 3)
                R.id.cardMenuRb4x5 -> Pair(10, 4)
                R.id.cardMenuRb5x6 -> Pair(15, 5)
                R.id.cardMenuRb6x7 -> Pair(21, 6)
                else -> Pair(-1, -1)
            }

            speed = when (binding.rgSpeed.checkedRadioButtonId) {
                R.id.cardMenuRbSlow -> 1
                R.id.cardMenuRbFast -> 2
                else -> -1
            }

            if (numCardsAndColumns != Pair(-1, -1) && speed != -1) {
                goFragment(numCardsAndColumns.first, numCardsAndColumns.second, speed)
            } else {
                showErrorMessage()
            }
        }

        binding.cbUndiscoveredMode.setOnClickListener {
            binding.cbUndiscoveredMode.text = if (binding.cbUndiscoveredMode.isChecked) "✓" else ""
        }
    }
    private fun showErrorMessage() {
        val message = when {
            binding.rgBoardSize.checkedRadioButtonId == -1 -> getString(R.string.fragment_card_menu_toast_board_size)
            binding.rgSpeed.checkedRadioButtonId == -1 -> getString(R.string.fragment_card_menu_toast_adjust_speed)
            else -> "Unknown error"
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun goFragment(numCards: Int, numColumns: Int, speed: Int) {
        PlayMusic.pause()
        cardFragment = CardFragment().apply {
            arguments = Bundle().apply {
                putInt("numCards", numCards)
                putInt("numColumns", numColumns)
                if (binding.cbUndiscoveredMode.isChecked) {
                    putBoolean("undiscoveredMode", true)
                }
                putInt("speed", speed)
            }
        }


        val navController = findNavController()
        navController.navigate(R.id.cardFragment, cardFragment.arguments)

    }

    override fun onResume() {
        super.onResume()
        PlayMusic.resume()
        numCardsAndColumns = Pair(0, 0)
        speed = 0
        binding.rgSpeed.clearCheck()
        binding.rgBoardSize.clearCheck()
        binding.cbUndiscoveredMode.isChecked = false
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