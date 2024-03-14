package com.oyoke.iara.quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oyoke.iara.MainActivity
import com.oyoke.iara.databinding.FragmentQuoteBinding
import com.oyoke.iara.util.music.PlayMusic

class QuoteFragment : Fragment() {



    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.showBottomNavigationView()
        (activity as? MainActivity)?.updateFragment("Quote")
        binding.tvQuote.text = QuoteRepository.random().quote
        binding.fragmentQuoteView.setOnClickListener {
            binding.tvQuote.text = QuoteRepository.random().quote
        }
        PlayMusic.releaseMediaPlayer()
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}