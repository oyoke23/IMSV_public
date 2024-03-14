package com.oyoke.iara.home

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oyoke.iara.R

object EnvelopeRepository {
    private const val PREFS_NAME = "EnvelopePrefs"
    private lateinit var envelopeList: List<EnvelopeModel>

    fun markEnvelopeAsOpened(context: Context, envelopeId: Int) {
        val envelope = getEnvelope(context, envelopeId)
        envelope.opened = true

        // Actualizar la lista de sobres: reemplazar el sobre existente con el modificado
        envelopeList = envelopeList.map {
            if (it.id == envelopeId) envelope else it
        }

        // Ahora guarda la lista actualizada en las preferencias compartidas
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
                Hola, cariño... sinceramente que voy a decirte que no te haya dicho ya. Quería escribir esta carta para darte la bienvenida a la aplicación y para explicarte lo que siento por ti, además de lo agradecido que estoy por tenerte.

                Primero de todo me gustaría expresarte que este es el mayor regalo que le he dado a cualquier persona con una diferencia abismal, algo que nunca le había hecho a nadie, algo a lo que le he dedicado muchísimo tiempo que quise hacértelo a ti simplemente porque sé que te lo mereces.
                
            """.trimIndent(), """
                Sé que ya te lo he dicho muchas veces, pero no me voy a cansar nunca de decírtelo. Estoy muy feliz de que seas parte de mi vida y prácticamente cualquier cosa que hagas me causa muchísima felicidad. De hecho, a veces me da hasta vergüenza porque me cuesta entender como por algo tan tonto puedo sentirme tan feliz.
              
                Simplemente, tienes la capacidad de hacerme feliz con la más mínima cosa, incluso a veces sin quererlo, la verdad es que eso es algo que valoro mucho y que me hace estar cada día más enamorado de ti.
            
            """.trimIndent(), """
                Espero que disfrutes mucho de la aplicación y que te haga reír, ya que como sabes perfectamente la he hecho con muchísimo cariño. 

                Y que más decirte, eres increíble, simplemente me encantas, a veces siento que las palabras no son suficientes para demostrarte lo mucho que te valoro, por eso es algo que te voy a estar demostrando toda la vida día tras día.

                Te amo muchísimo❤️.


                - Kevin
            """.trimIndent()
                ), false
            ), EnvelopeModel(
                1, R.drawable.home_envelope_valentines_day, listOf(
                    """
                Hola amor, escribo esta carta para expresarte una vez más lo mucho que te quiero y valoro, así como todo lo que siento por ti. Eres esencial en mi vida y desde que llegaste, has cambiado mi existencia para mejor.

                Bendigo el día en que conocí la aplicación MEEFF, ya que gracias a ello y a mi curiosidad, tuve la fortuna de conocer a una mujer maravillosa, alguien que desde el primer momento en que la vi, despertó mi interés.

                Un interés que a medida que te conocía, crecía aún más. Además, lograste lo que varias personas intentaron sin éxito: hiciste que replanteara mi pensamiento sobre tener una relación a larga distancia.
            """.trimIndent(), """
                Durante varios días, reflexioné sobre qué hacer. Sentí que quería estar contigo, que no quería perderte, que te necesitaba y que eras la persona indicada.

                Valoré la conexión que sentía contigo lo suficiente como para querer intentarlo, pese a las dudas y el miedo a que nuestra relación no prosperase y resultara en una pérdida de tiempo y mucho dolor, deseaba estar contigo.

                Confié en ti y tomé la decisión de proponerte comenzar una relación a pesar la distancia, confiando en que podríamos llegar lejos y teniendo en cuenta que quería que fueras la persona con la que pasar el resto de mi vida.
            """.trimIndent(), """
                Traté de hacerlo lo mejor posible porque sabía que valías la pena, y como predije, no me equivoqué. Te considero una mujer que me gusta en todos los aspectos, ya sea física o mentalmente. Al pensar en ti, experimento una felicidad difícil de explicar con palabras. Has logrado que replantee mi perspectiva sobre no querer casarme, sintiendo que te mereces todo y más.
 
                Estoy orgulloso de tenerte como pareja, feliz por lo que hemos logrado y expectante por ver hasta dónde llegaremos, esperando que sea hasta el fin de nuestras vidas.
            """.trimIndent(), """
                Eres la mejor y te quiero mucho, Iara. Te considero la mujer de mi vida, un pilar fundamental, alguien que me a despertando emociones que nadie más ha logrado hacerme sentir.
                
                Eres muy importante para mí y te lo voy a estar recordando por siempre, cariño.
                
                Feliz San Valentín. Te amo❤️

                
                - Kevin
            """.trimIndent()
                ), false
            )
        )
    }
}