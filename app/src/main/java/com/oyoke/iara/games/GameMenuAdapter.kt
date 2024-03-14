package com.oyoke.iara.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oyoke.iara.databinding.ItemGameButtonBinding

class GameMenuAdapter(
    private val games: List<Game>,
    private val onGameItemClickListener: OnGameItemClickListener
) : RecyclerView.Adapter<GameMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameMenuAdapter.ViewHolder {
        val binding = ItemGameButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameMenuAdapter.ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)

    }

    override fun getItemCount(): Int = games.size

    inner class ViewHolder(private val binding: ItemGameButtonBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onGameItemClickListener.onGameItemClick(games[adapterPosition])
            }
        }

        fun bind(game: Game) {
            binding.imageViewGame.setImageResource(game.image)
            binding.textViewGame.text = game.name
        }
    }

    fun interface OnGameItemClickListener {
        fun onGameItemClick(game: Game)
    }
}
