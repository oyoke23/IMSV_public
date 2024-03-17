package com.oyoke.imsv.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.oyoke.imsv.MainActivity
import com.oyoke.imsv.R
import com.oyoke.imsv.databinding.FragmentHomeBinding
import com.oyoke.imsv.util.music.PlayMusic

class HomeFragment : Fragment(), GestureDetector.OnGestureListener, View.OnTouchListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val valueAnimator = ValueAnimator.ofFloat(-15f, 15f)
    private var currentRotationAngle = 0f

    private var currentTextIndex = 0
    private lateinit var textSpeechList: List<String>

    private lateinit var gestureDetector: GestureDetectorCompat

    //Change depending on the card you want to show
    private val envelopeId = 0
    private val infoFragmentEnvelopeText = "Home"

    //Adjust the duration according to your preferences
    private var animationCorrectionSpeed: Long = 500

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.showBottomNavigationView()
        (activity as? MainActivity)?.updateFragment(infoFragmentEnvelopeText)
        PlayMusic.releaseMediaPlayer()

        val envelopeOpened = getEnvelopeOpenedStatus()

        if (envelopeOpened) {
            binding.envelopeCard.visibility = View.GONE
            binding.card.visibility = View.VISIBLE
            textCard()
        } else {
            binding.envelopeCard.setImageResource(
                EnvelopeRepository.getEnvelope(
                    requireContext(),
                    envelopeId
                ).image
            )

            valueAnimator()
        }

        binding.card.isClickable = true

        gestureDetector = GestureDetectorCompat(requireContext(), this)
        binding.card.setOnTouchListener(this)
        return binding.root
    }

    private fun valueAnimator() {
        valueAnimator.duration = 900
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.repeatMode = ValueAnimator.REVERSE

        valueAnimator.addUpdateListener { animator ->
            currentRotationAngle = animator.animatedValue as Float
            binding.envelopeCard.rotation = currentRotationAngle
        }

        valueAnimator.start()

        binding.envelopeCard.setOnClickListener {
            valueAnimator.cancel()
            realizarCorreccion()
        }
    }

    private fun realizarCorreccion() {
        val animacionCorreccion = ValueAnimator.ofFloat(currentRotationAngle, 0f)
        animacionCorreccion.duration = animationCorrectionSpeed

        animacionCorreccion.addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Float
            binding.envelopeCard.rotation = animatedValue
        }

        animacionCorreccion.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                realizarAnimacionDesaparecer()
            }
        })

        animacionCorreccion.start()

    }

    private fun realizarAnimacionDesaparecer() {
        val animacionDesaparecer = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)

        animacionDesaparecer.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.envelopeCard.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animation?) {
                realizarAnimacionAparecer()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Function not necessary
            }
        })

        binding.envelopeCard.startAnimation(animacionDesaparecer)
        setEnvelopeOpenedStatus()
    }

    private fun realizarAnimacionAparecer() {

        binding.card.visibility = View.VISIBLE

        val animacionAparecer = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

        binding.card.startAnimation(animacionAparecer)
        textCard()

    }

    private fun textCard() {
        textSpeechList = EnvelopeRepository.getEnvelope(requireContext(), envelopeId).texts
        binding.textHome.text = textSpeechList[currentTextIndex]
        binding.pageNumber.text = (currentTextIndex + 1).toString() + "/" + textSpeechList.size
    }

    private fun getEnvelopeOpenedStatus(): Boolean {
        return EnvelopeRepository.getEnvelope(requireContext(), envelopeId).opened
    }

    private fun setEnvelopeOpenedStatus() {
        EnvelopeRepository.markEnvelopeAsOpened(requireContext(), envelopeId)
    }

    private fun showNextPage() {
        if (currentTextIndex < textSpeechList.size - 1) {
            currentTextIndex++
            binding.textHome.text = textSpeechList[currentTextIndex]
            binding.pageNumber.text =
                (currentTextIndex + 1).toString() + "/" + textSpeechList.size
        }
    }

    private fun showPreviousPage() {
        if (currentTextIndex > 0) {
            currentTextIndex--
            binding.textHome.text = textSpeechList[currentTextIndex]
            binding.pageNumber.text =
                (currentTextIndex + 1).toString() + "/" + textSpeechList.size
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        valueAnimator.cancel()
        _binding = null
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent) {
        // Function not necessary
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        // Function not necessary
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val distanceX = e2.x - (e1?.x ?: 0f)
        val distanceY = e2.y - (e1?.y ?: 0f)

        if (Math.abs(distanceX) > Math.abs(distanceY)) {
            // Horizontal sliding
            if (distanceX > 0) {
                // Sliding from left to right
                showPreviousPage()
            } else {
                // Sliding from right to left
                showNextPage()
            }
        } else {
            // Vertical slide
            if (distanceY > 0) {
                // Sliding from top to bottom
                showPreviousPage()
            } else {
                //Sliding from bottom to top
                showNextPage()
            }
        }

        return true
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

}



