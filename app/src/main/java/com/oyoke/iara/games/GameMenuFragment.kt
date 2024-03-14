package com.oyoke.iara.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyoke.iara.MainActivity
import com.oyoke.iara.databinding.FragmentGameMenuBinding
import com.oyoke.iara.util.music.PlayMusic

class GameMenuFragment : Fragment(), GameMenuAdapter.OnGameItemClickListener {

    private var _binding: FragmentGameMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameMenuAdapter: GameMenuAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameMenuBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.showBottomNavigationView()

        (activity as? MainActivity)?.updateFragment("Games")
        PlayMusic.releaseMediaPlayer()

        binding.rvGames.layoutManager = LinearLayoutManager(requireContext())
        gameMenuAdapter =
            GameMenuAdapter(GameRepository.games, this)
        binding.rvGames.adapter = gameMenuAdapter

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onGameItemClick(game: Game) {
        (activity as? MainActivity)?.hideBottomNavigationView()
        val navController = findNavController()

        navController.navigate(game.fragment)
    }
}