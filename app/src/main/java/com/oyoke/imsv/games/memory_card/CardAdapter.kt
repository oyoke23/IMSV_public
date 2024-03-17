package com.oyoke.imsv.games.memory_card

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.oyoke.imsv.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardAdapter(
    private val cardImages: List<CardModel>,
    private val pairs: Int,
    private val speed: Int,
    private val undiscoveredMode: Boolean,
    private val navController: NavController,

) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var isFlipping = false
    private var isFirst = true
    lateinit var firstCard: CardModel
    lateinit var firstHolder: CardViewHolder
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var correctSoundPlayer: MediaPlayer
    private lateinit var flipCardSoundPlayer: MediaPlayer
    private var cardHeight = 0
    private var flipVelocity: Long = 0

    private var numberOfPairs = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        flipCardSoundPlayer = MediaPlayer.create(parent.context, R.raw.card_flip)
        correctSoundPlayer = MediaPlayer.create(parent.context, R.raw.correct_sound)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardImages[position]
        setCard(holder, card, false)

        holder.imageViewCard.setOnClickListener {
            if (!isFlipping && !card.isFlipped) {
                flip(holder, card, 0f, 180f)
            }
        }

        setupCardDimensions(holder.itemView)
        when (speed) {
            1 -> flipVelocity = 800
            2 -> flipVelocity = 200
        }
        if (undiscoveredMode) {
            holder.imageViewUndiscovered.visibility = View.VISIBLE
        }
    }

    private fun setupCardDimensions(view: View) {

        view.post {
            val cardWidth = view.width
            cardHeight = (cardWidth * 1.5).toInt()
            view.layoutParams = view.layoutParams.apply { height = cardHeight }
        }

    }


    override fun getItemCount(): Int = cardImages.size

    private fun flip(holder: CardViewHolder, card: CardModel, start: Float, end: Float) {
        isFlipping = true
        flipCardSound()
        val valueAnimator = ValueAnimator.ofFloat(start, end)
        valueAnimator.duration = 400
        valueAnimator.addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Float
            when {
                (animatedValue in 90f..180f) && !card.isFlipped -> setCard(holder, card, true)
                (animatedValue in 270f..360f) && card.isFlipped -> setCard(holder, card, false)
            }
            holder.imageViewCard.rotationY = animatedValue
            holder.cardOverlay.rotationY = animatedValue
            holder.imageViewUndiscovered.rotationY = animatedValue
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                isFlipping = false
            }
        })
        valueAnimator.start()
    }

    private fun flipCardSound() {
        flipCardSoundPlayer.seekTo(0)
        flipCardSoundPlayer.start()
    }

    private fun setCard(holder: CardViewHolder, card: CardModel, isFront: Boolean) {
        if (isFront) {
            holder.imageViewUndiscovered.visibility = View.GONE
            holder.imageViewCard.setImageResource(card.image)
            card.isFlipped = true

            if (isFirst) {
                firstCard = card
                firstHolder = holder
                isFirst = false
            } else {
                checkPairs(firstCard, card, firstHolder, holder)
            }

        } else {
            holder.imageViewCard.setImageResource(R.drawable.card_back)
            card.isFlipped = false
        }
    }

    private fun checkPairs(
        firstCard: CardModel,
        card: CardModel,
        firstHolder: CardViewHolder,
        holder: CardViewHolder,
    ) {
        if (firstCard.pairId == card.pairId && firstHolder != holder) {
            val darkColor = Color.argb(100, 0, 0, 0)
            firstHolder.cardOverlay.setBackgroundColor(darkColor)
            holder.cardOverlay.setBackgroundColor(darkColor)

            firstHolder.imageViewCard.isClickable = false
            holder.imageViewCard.isClickable = false

            playCorrectSound()

            numberOfPairs++
            if (pairs == numberOfPairs) {
                val arguments = Bundle().apply {
                    putString("type", "Win - MC")
                }
                navController.navigate(R.id.finalScreenFragment, arguments)
            }
        } else {
            coroutineScope.launch {
                delay(flipVelocity)
                flip(firstHolder, firstCard, 180f, 360f)
                flip(holder, card, 180f, 360f)
            }
        }
        isFirst = true
    }

    private fun playCorrectSound() {
        correctSoundPlayer.seekTo(0)
        correctSoundPlayer.start()
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewCard: ImageView = itemView.findViewById(R.id.ivCard)
        val cardOverlay: View = itemView.findViewById(R.id.cardOverlay)
        val imageViewUndiscovered: ImageView = itemView.findViewById(R.id.ivUndiscovered)
    }

    override fun onViewDetachedFromWindow(holder: CardViewHolder) {
        holder.imageViewCard.clearAnimation()
    }
}