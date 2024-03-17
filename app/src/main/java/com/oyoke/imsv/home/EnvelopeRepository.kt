package com.oyoke.imsv.home

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oyoke.imsv.R

object EnvelopeRepository {
    private const val PREFS_NAME = "EnvelopePrefs"
    private lateinit var envelopeList: List<EnvelopeModel>

    fun markEnvelopeAsOpened(context: Context, envelopeId: Int) {
        val envelope = getEnvelope(context, envelopeId)
        envelope.opened = true

        envelopeList = envelopeList.map {
            if (it.id == envelopeId) envelope else it
        }

        saveEnvelope(context, envelopeList)
    }

    fun getEnvelope(context: Context, envelopeId: Int): EnvelopeModel {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString("envelope", null)
        envelopeList = if (json != null) {
            gson.fromJson(json, object : TypeToken<List<EnvelopeModel>>() {}.type)
        } else {
            getDefaultEnvelopes()
        }
        return envelopeList[envelopeId]
    }

    fun saveEnvelope(context: Context, envelope: List<EnvelopeModel>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(envelope)
        editor.putString("envelope", json)
        editor.apply()
    }

    private fun getDefaultEnvelopes(): List<EnvelopeModel> {
        return listOf(
            EnvelopeModel(
                0, R.drawable.home_envelope_love, listOf(
                    """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.

                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                
            """.trimIndent(), """
                Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.
              
                Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.
            
                Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
            """.trimIndent(), """
                Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?

                Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?
            """.trimIndent()
                ), false
            ), EnvelopeModel(
                1, R.drawable.home_envelope_valentines_day, listOf(
                    """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.

                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                
            """.trimIndent(), """
                Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.
              
                Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.
            
                Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
            """.trimIndent(), """
                Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?

                Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?
            """.trimIndent()
                ), false
            )
        )
    }
}