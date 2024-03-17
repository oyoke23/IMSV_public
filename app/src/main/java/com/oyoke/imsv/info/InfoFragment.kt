package com.oyoke.imsv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.oyoke.imsv.MainActivity
import com.oyoke.imsv.R
import com.oyoke.imsv.databinding.FragmentInfoBinding
import com.oyoke.imsv.util.SpeechSimulator


class InfoFragment : Fragment(), SpeechSimulator.OnSimulationCompleteListener {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var speechSimulator: SpeechSimulator
    private var currentTextIndex = 0
    private lateinit var textSpeechList: List<String>
    private var firstText = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view?.startAnimation(fadeIn)

        (activity as? MainActivity)?.hideToolbar()
        (activity as? MainActivity)?.hideBottomNavigationView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectText()
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.startAnimation(fadeIn)
        binding.introActivity.setOnClickListener {
            simulateText()
        }
        if (firstText) {
            speechSimulator = SpeechSimulator(binding.textViewSpeech, this)
            speechSimulator.startSimulation(textSpeechList[currentTextIndex])
            firstText = false
            binding.introActivity.isClickable = false
        }
    }

    private fun selectText() {
        when (arguments?.getString("currentFragmentName")) {
            "First Time" -> {
                textSpeechList = listOf(
                    "Example First Time text 1",
                    "Example First Time text 2",
                    "Example First Time text 3",
                    "Example First Time text 4"
                )
            }

            "Home" -> {
                textSpeechList = listOf(
                    "Example Home text 1",
                    "Example Home text 2",
                )
            }

            "Games" -> {
                textSpeechList = listOf(
                    "Example games text 1",
                    "Example games text 2",
                    "Example games text 3"
                )
            }

            "Quote" -> {
                textSpeechList = listOf(
                    "Example Quote text 1",
                    "Example Quote text 2",
                    "Example Quote text 3"
                )

            }

            "Quiz Menu" -> {
                textSpeechList = listOf(
                    "Example Quiz Menu text 1",
                    "Example Quiz Menu text 2",
                )
            }

            "Quiz" -> {
                textSpeechList = listOf(
                    "Example Quiz text 1",
                )
            }

            "Memory Menu" -> {
                textSpeechList = listOf(
                    "Example Memory Menu text 1",
                    "Example Memory Menu text 2",
                )
            }

            "Memory" -> {
                textSpeechList = listOf(
                    "Example Memory text 1",
                    "Example Memory text 2",
                    "Example Memory text 3"
                )
            }

            "Puzzle Menu" -> {
                textSpeechList = listOf(
                    "Example Puzzle Menu text 1",
                )
            }

            "Puzzle" -> {
                textSpeechList = listOf(
                    "Example Puzzle text 1",
                    "Example Puzzle text 2",
                )
            }

            "WHR Menu" -> {
                textSpeechList = listOf(
                    "Example WHR Menu text 1",
                    "Example WHR Menu text 2",
                )
            }

            "WHR" -> {
                textSpeechList = listOf(
                    "Example WHR text 1",
                )
            }
            "Win" -> {
                textSpeechList = listOf(
                    "Example win text 1",
                    "Example win text 2",
                )
            }
            "Lose" -> {
                textSpeechList = listOf(
                    "Example lose text 1",
                    "Example lose text 2",
                )
            }

            "First Time Valentine's Day" -> {
                textSpeechList = listOf(
                    "Example First Time Valentine's Day text 1",
                    "Example First Time Valentine's Day text 2",
                    "Example First Time Valentine's Day text 3"
                )
            }

            "Valentine's Day" -> {
                textSpeechList = listOf(
                    "Example Home Valentine's Day text 1",
                    "Example Home Valentine's Day text 2",
                    "Example Home Valentine's Day text 3"
                )
            }

            else -> {
                textSpeechList = listOf(
                    "No text for this fragment"
                )
            }
        }
    }

    private fun simulateText() {
        binding.introActivity.isClickable = false
        if (currentTextIndex < textSpeechList.size - 1) {
            currentTextIndex++
            speechSimulator.stopSimulation()
            speechSimulator.startSimulation(textSpeechList[currentTextIndex])
        } else {
            speechSimulator.stopSimulation()
            val fadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
            view?.startAnimation(fadeOut)
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    parentFragmentManager.popBackStack()
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onDestroy()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    // Not necessary
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? MainActivity)?.showToolbar()
    }

    override fun onSimulationComplete() {
        binding.introActivity.isClickable = true
    }
}

