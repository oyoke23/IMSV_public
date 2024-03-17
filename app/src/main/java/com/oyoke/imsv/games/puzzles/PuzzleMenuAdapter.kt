package com.oyoke.imsv.games.puzzles

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.oyoke.imsv.databinding.ItemPuzzleButtonBinding

class PuzzleMenuAdapter(
    private val puzzles: List<PuzzleModel>,
    private val onPuzzleItemClickListener: OnPuzzleItemClickListener

) : RecyclerView.Adapter<PuzzleMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPuzzleButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PuzzleMenuAdapter.ViewHolder, position: Int) {
        val puzzle = puzzles[position]
        holder.bind(puzzle)
    }

    override fun getItemCount(): Int = puzzles.size

    inner class ViewHolder(private val binding: ItemPuzzleButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val clickedPuzzle = puzzles[adapterPosition]

                val isPreviousPuzzleSolved = if (adapterPosition > 0) {
                    puzzles[adapterPosition - 1].isSolved
                } else {
                    true
                }

                if (isPreviousPuzzleSolved && clickedPuzzle.isClickable) {
                    onPuzzleItemClickListener.onPuzzleItemClick(clickedPuzzle)
                }
            }
        }

        fun bind(puzzle: PuzzleModel) {
            val number = puzzle.id

            binding.tvPuzzleNum.text = (number + 1).toString()
            binding.tvPuzzleTitle.text = puzzle.title
            binding.root.isClickable = puzzle.isClickable

            if (puzzle.isClickable) {
                binding.ivPadlock.visibility = View.GONE
                setCardBackgroundColor(com.google.android.material.R.attr.colorPrimaryVariant, binding.cvPuzzle)
                setColorFilter(com.google.android.material.R.attr.colorOnPrimary, binding.ivPadlock)
                setTextColor(com.google.android.material.R.attr.colorOnPrimary, binding.tvPuzzleNum)
                setTextColor(com.google.android.material.R.attr.colorOnPrimary, binding.tvPuzzleTitle)
            } else {
                binding.ivPadlock.visibility = View.VISIBLE
                setCardBackgroundColor(com.google.android.material.R.attr.colorSecondaryVariant, binding.cvPuzzle)
                setColorFilter(com.google.android.material.R.attr.colorOnSecondary, binding.ivPadlock)
                setTextColor(com.google.android.material.R.attr.colorOnSecondary, binding.tvPuzzleNum)
                setTextColor(com.google.android.material.R.attr.colorOnSecondary, binding.tvPuzzleTitle)
            }
        }
    }

    private fun setTextColor( colorAttr: Int, textView: TextView) {
        val typedValue = TypedValue()
        textView.context.theme.resolveAttribute(colorAttr, typedValue, true)
        textView.setTextColor(typedValue.data)
    }
    private fun setCardBackgroundColor(colorAttr: Int, view: View) {
        val typedValue = TypedValue()
        view.context.theme.resolveAttribute(colorAttr, typedValue, true)
        if (view is CardView) {
            view.setCardBackgroundColor(typedValue.data)
        } else {
            view.setBackgroundColor(typedValue.data)
        }
    }
    private fun setColorFilter(colorAttr: Int, imageView: ImageView) {
        val typedValue = TypedValue()
        imageView.context.theme.resolveAttribute(colorAttr, typedValue, true)
        imageView.setColorFilter(typedValue.data)
    }
    fun interface OnPuzzleItemClickListener {
        fun onPuzzleItemClick(puzzle: PuzzleModel)
    }

}
