package com.oyoke.iara.util.music

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import com.oyoke.iara.R


object PlayMusic {

    private var mediaPlayer: MediaPlayer? = null
    private val handler = Handler(Looper.getMainLooper())

    private val quizWhrMusicList: List<Int> = listOf(
        R.raw.music_quiz_whr_quiz,
        R.raw.music_quiz_whr_quiz2,
        R.raw.music_quiz_whr_durante,
        R.raw.music_quiz_whr_examen_song,
        R.raw.music_quiz_whr_la_bonne_reponse,
        R.raw.music_quiz_whr_lofi,
        R.raw.music_quiz_whr_quizz_day,
        R.raw.music_quiz_whr_solution,
        R.raw.music_quiz_whr_suspense,
        R.raw.music_quiz_whr_thinking_time,
        R.raw.music_quiz_whr_tumble_tots,
        R.raw.music_quiz_whr_wemida_heartbeat

    )
    private val puzzleMusicList: List<Int> = listOf(
        R.raw.music_puzzle_an_alien_presence,
        R.raw.music_puzzle_criminal,
        R.raw.music_puzzle_instrumental_mystic_song,
        R.raw.music_puzzle_my_cat_is_crazy,
        R.raw.music_puzzle_magic_in_the_air,
        R.raw.music_puzzle_let_the_mystery_unfold,
        R.raw.music_puzzle_mysterious_celesta,
        R.raw.music_puzzle_spirits_of_the_moor,
        R.raw.music_puzzle_mousey_mcmousekewitz,
        R.raw.music_puzzle_mysterious_walking_comedy
    )
    private val cardMusicList: List<Int> = listOf(
        R.raw.music_card_life_of_a_wandering_wizard,
        R.raw.music_card_alicias_theme_organ_brolefilmer,
        R.raw.music_card_be_ready_for_the_hallows_eve_jazz,
        R.raw.music_card_comedy_detective,
        R.raw.music_card_comedy_piano,
        R.raw.music_card_funny,
        R.raw.music_card_swing_it_jack_electro,
        R.raw.music_card_orchestra_of_clowns,
        R.raw.music_card_children_electro_swing_2,
        R.raw.music_card_catchy_swing
    )

    private var currentPlaylist: List<Int> = emptyList()

    fun playRandomSongs(context: Context, songType: String) {
        releaseMediaPlayer()

        currentPlaylist = when (songType) {
            "QuizWhr" -> quizWhrMusicList.shuffled()
            "Puzzle" -> puzzleMusicList.shuffled()
            "Card" -> cardMusicList.shuffled()
            else -> emptyList()
        }

        // Reproducir las canciones en orden aleatorio con un temporizador
        playSongs(context, 0)
    }

    private fun playSongs(context: Context, index: Int) {
        if (index < currentPlaylist.size) {
            mediaPlayer = MediaPlayer.create(context, currentPlaylist[index])
            mediaPlayer?.start()

            // Esperar a que la canción actual termine antes de pasar a la siguiente
            mediaPlayer?.setOnCompletionListener {
                // Liberar recursos del MediaPlayer al finalizar la canción
                releaseMediaPlayer()

                // Programar la reproducción de la siguiente canción después de un breve retraso
                handler.postDelayed({
                    playSongs(context, index + 1)
                }, 100) // Ajusta el tiempo de espera según sea necesario
            }
        }
    }
    fun resume() {
        mediaPlayer?.start()
    }
    fun pause() {
        mediaPlayer?.pause()
    }
    fun getMediaPlayer() = mediaPlayer
    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
