package com.oyoke.iara.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.oyoke.iara.MainActivity
import com.oyoke.iara.R
import com.oyoke.iara.databinding.FragmentInfoBinding
import com.oyoke.iara.util.SpeechSimulator


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
                    "¡Hola bonita! Ya te empezaba a echar de menos, ¡por fin nos volvemos a ver!",
                    "Hoy es el día que tanto estabas esperando, el momento de mostrarte a lo que realmente le ha estado dedicando tanto tiempo.",
                    "Probablemente, estés nerviosa, tranquila, yo también lo estoy, no por algo somos la misma persona, pero no te preocupes, ya que él también lo está.",
                    "Siéntate, ponte cómoda y disfruta. Que eso es lo importante y la razón principal por la que hizo todo esto.",
                    "Por último, también me ha dicho que te entregue esta carta. ¿Viste? Esta vez no se me ha olvidado jajaja",
                    "¡Espero que la disfrutes!"
                )
            }

            "Home" -> {
                textSpeechList = listOf(
                    "Esta es la carta que te ha hecho Kevin para expresarte sus sentimientos durante el transcurso de la creación de la aplicación.",
                )
            }

            "Games" -> {
                textSpeechList = listOf(
                    "Desde aquí puedes acceder a los diferentes juegos, voy a explicarte de que tratan.",
                    "Quiz: Como el propio nombre indica, consistirá en una serie de preguntas de diferentes niveles de dificultad relacionadas con tu pareja.",
                    "Memory Card: Este es el que más tiempo ha llevado, un juego de memoria en el que tendrás que emparejar cartas personalizadas de Bruno, Giovanni y Simba.",
                    "Puzzles: Aquí tendrás que resolver diferentes acertijos y misterios con la gran parte de los enunciados personalizados y tratando de hacerte reír.",
                    "Would He Rather: Algo similar al Quiz, tendrás múltiples categorías para elegir en las que saldrán preguntas con 2 opciones y tendrás que estar rápida para tratar de adivinar cuál prefiere."
                )
            }

            "Quote" -> {
                textSpeechList = listOf(
                    "En este apartado encontrarás más de 300 frases que te ha querido dedicar, muchas de ellas románticas, otras algo picantes y algunas graciosas.",
                    "Como me imagino que te habrá dicho, este apartado iba a tener una función totalmente distinta, pero al final fue remplazada.",
                    "Espero que te guste y te quedes un buen rato pulsando para ver que va saliendo igual que hice yo jajaja."
                )

            }

            "Quiz Menu" -> {
                textSpeechList = listOf(
                    "Desde este menú puedes seleccionar la dificultad a la que te quieres enfrentar en el quiz, dispones de 4 dificultades, lógicamente cada una más difícil que la anterior.",
                    "Conforme vayas descubriendo nuevas preguntas y explorando las distintas dificultades, te darás cuenta de muchas cosas que antes no sabías de él.",
                    "Yo nunca he conseguido superar la dificultad 'Experto' así que te deseo mucha suerte, veremos cuál de las dos Iaras le conoce mejor.",
                    "¡Suerte!"
                )
            }

            "Quiz" -> {
                textSpeechList = listOf(
                    "¿No pensarás que voy a decirte la respuesta de las preguntas, no?",
                    "Pensaba que le conocías más, pero ya veo que no..."
                )
            }

            "Memory Menu" -> {
                textSpeechList = listOf(
                    "Aquí es donde puedes configurar la partida de cartas. Todas las cartas estan basadas en diferentes versiones de Bruno, Giovanni y Simba.",
                    "Lo primero es el tamaño, donde el primer número indica las columnas y el segundo las filas.",
                    "Además tienes la opción de marcar las cartas que nunca han sido volteadas, para facilitar las cosas un poco.",
                    "Bueno eso es todo, espero que te gusten.",
                )
            }

            "Memory" -> {
                textSpeechList = listOf(
                    "¿Pretendes que te diga donde está cada pareja?",
                    "¡Pues no! eso no va a pasar, así que espabila y ponte a hacer memoria."
                )
            }

            "Puzzle Menu" -> {
                textSpeechList = listOf(
                    "Aquí puedes acceder a los distintos puzzles, lógicamente los que tienen un candado los desbloquearás al hacer el anterior.",
                    "Todos tienen un enunciado personalizado, espero que lo pases bien y te hagan reír."
                )
            }

            "Puzzle" -> {
                textSpeechList = listOf(
                    "Aquí podría haber alguna pista sobre el puzzle en el que te encuentras.",
                    "Pero teniendo en cuenta que eran muchos textos que añadir, mejor pregúntale a él y ya te dará la pista.",
                    "Sinceramente, era mucho trabajo extra jajaja"
                )
            }

            "WHR Menu" -> {
                textSpeechList = listOf(
                    "Menú para configurar el Would He Rather, trataré de resumirte las opciones.",
                    "Primero puedes elegir entre las 6 categorías para que salgan preguntas de distintas temáticas.",
                    "La segunda opción es para configurar si quieres más o menos tiempo para responder.",
                    "Y por último, la dificultad a elegir entre 'Casual' y 'Reto' donde en este último en el caso de fallar pierdes.",
                )
            }

            "WHR" -> {
                textSpeechList = listOf(
                    "Si lo que quieres es la respuesta, te lo diré...",
                    "Es la primera.",
                    "O quizá te estoy engañando y es la segunda."
                )
            }
            "Win" -> {
                textSpeechList = listOf(
                    "Siéndote sincera, me ha sorprendido lo bien que lo has hecho, pareces saber más de lo que creía.",
                    "Pero eso no significa que vaya a decir que lo hiciste mejor que yo, mi orgullo no lo permite."
                )
            }
            "Lose" -> {
                textSpeechList = listOf(
                    "Veo que has perdido... así no vas a conseguir superarme, pero te deseo suerte para la próxima vez.",
                    "Quién sabe, quizá algún día lo hagas mejor que yo."
                )
            }

            "First Time Valentine's Day" -> {
                textSpeechList = listOf(
                    "¡Hola, Iara! ¡Veo que finalmente ha llegado el momento que tanto esperabas, bienvenida! Estaba deseando poder conocerte.",
                    "Probablemente te estés preguntando quién soy, ¡y es comprensible! Soy una versión digital de ti misma, diseñada por Kevin para guiarte a través de esta aplicación.",
                    "Si quieres tener más detalles sobre alguna sección de la aplicación o saber cómo funciona, simplemente haz clic en el símbolo de interrogación que siempre encontrarás en la esquina superior derecha.",
                    "Como ya sabrás, esto es algo que te ha hecho con mucho cariño y a lo que también ha dedicado mucho tiempo y esfuerzo, así que ojalá la disfrutes.",
                    "Y por cierto, de momento no podrás hacer muchas cosas, ya que esto es solo una pequeña parte de la aplicación, un adelanto como regalo por el día de hoy.",
                    "¡Espera!, casi se me olvida lo más importante, también me encargaron entregarte esta carta, no sé por qué me habrán programado tan olvidadiza jajaja",
                    "¡Feliz San Valentín, princesa!"
                )
            }

            "Valentine's Day" -> {
                textSpeechList = listOf(
                    "Aquí es donde se encuentra la carta que te ha hecho Kevin para San Valentín. Dedicó bastante tiempo para expresar lo que sentía, así que espero que te haya gustado mucho.",
                    "Y quién sabe, quizá más adelante aparecerán nuevas cartas..."
                )
            }

            else -> {
                textSpeechList = listOf(
                    "NO HAY UN TEXTO PARA ESTE FRAGMENT"
                )
            }
        }
    }

    private fun simulateText() {
        binding.introActivity.isClickable = false
        if (currentTextIndex < textSpeechList.size - 1) {
            currentTextIndex++
            // Detener la simulación actual y comenzar una nueva
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

                override fun onAnimationEnd(animation: Animation?) {/*not necessary*/
                    onDestroy()
                }

                override fun onAnimationRepeat(animation: Animation?) {/*not necessary*/
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

