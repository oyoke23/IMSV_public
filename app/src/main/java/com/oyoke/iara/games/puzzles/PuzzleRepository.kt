package com.oyoke.iara.games.puzzles

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oyoke.iara.R

object PuzzleRepository {
    private const val PREFS_NAME = "PuzzlesPrefs"

    fun markPuzzleAsSolved(context: Context, puzzleId: Int) {
        val puzzles = getPuzzles(context)
        val solvedPuzzle = puzzles.find { it.id == puzzleId }
        solvedPuzzle?.isSolved = true
        savePuzzles(context, puzzles)
    }

    fun getPuzzles(context: Context): List<PuzzleModel> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString("puzzles", null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<List<PuzzleModel>>() {}.type)
        } else {
            getDefaultPuzzles() // Devuelve la lista completa si no hay datos guardados
        }
    }

    fun savePuzzles(context: Context, puzzles: List<PuzzleModel>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(puzzles)
        editor.putString("puzzles", json)
        editor.apply()
    }

    private fun getDefaultPuzzles(): List<PuzzleModel> {
        return listOf(
            PuzzleModel(
                0,
                "Palanca y muesca",
                "En mitad de vuestro viaje en auto llegáis a un lugar extraño en el que un puente corta la ruta y la única forma de cruzar es bajarlo. Para ello, tenéis que encajar la palanca situada en el lateral en la muesca adecuada. Elige la muesca en la que encaja la palanca.",
                R.drawable.puzzle_00,
                "1"
            ), PuzzleModel(
                1,
                "Los peluqueros",
                "Fueron al pueblo del costado a visitar a un familiar. Al pelotudo de tu novio no se le ocurrió mejor idea que querer ir a cortarse el pelo, por lo tanto, te informas y descubres que solo hay dos peluqueros. Observas a los dos hombres en cuestión, los juzgas con la mirada y tratas de elegir:" + "\n" + "\n" + "¿A cuál de ellos tendría que acudir el boludito?",
                R.drawable.puzzle_01,
                "A"
            ), PuzzleModel(
                2,
                "Chisteras",
                "Has ido a comprar una chistera para tu novio, todas ellas tienen la misma altura, pero la anchura de la copa es diferente. Él te ha dicho que quiere que el ala y la copa tengan la misma medida." + "\n" + "\n" + "Teniendo eso en cuenta, ¿cuál deberías comprarle?",
                R.drawable.puzzle_02,
                "A"
            ), PuzzleModel(
                3,
                "Puzzle de una línea 1",
                "Tu novio ha hecho 4 dibujos y te dice que todos ellos los ha dibujado sin levantar el lápiz y sin repetir ninguna línea, exceptuando uno; las líneas sí pueden cruzarse pero no pasar por encima de las líneas ya dibujadas." + "\n" + "\n" + "Observa los dibujos e indica cuál de ellos no se puede dibujar con una sola línea.",
                R.drawable.puzzle_03,
                "C"
            ), PuzzleModel(
                4,
                "Velas",
                "Estabais viendo una película en el salón y de repente se fue la luz, por lo tanto, encendéis 10 velas para tener algo de visibilidad. Os dejáis la ventana abierta y una ráfaga de viento apaga tres de esas velas. Te asustas y te tiras un pedito por lo que apagas otra más. Para asegurarte de que no se apaga ninguna más, cierras la ventana." + "\n" + "\n" + "Si damos por hecho que el viento no apagará más velas y tus peditos tampoco, ¿cuántas velas te quedan al final?",
                R.drawable.puzzle_04,
                "4"
            ), PuzzleModel(
                5,
                "Prescripción médica",
                "El médico te ha prescrito 10 pastillas, ya que te encuentras mal del estómago y tienes que tomar una al día a partir de hoy, pero como la concentración del medicamento es diferente en cada una, tienes que tomártelas en un orden específico. Como las pastillas son todas iguales, tu amorcito ha decidido marcar con un número cada una y así sabrás en qué orden tienes que tomártelas." + "\n" + "\n" + "¿A cuántas pastillas tiene que ponerles número para que no te equivoques con el orden?",
                R.drawable.puzzle_05,
                "8"
            ), PuzzleModel(
                6,
                "El reto de la baraja",
                "Kevin, un mago de alto rango reconocido por todos, te está haciendo un truco de magia con cartas, su plan es adivinar la carta que te ha dado previamente realizando diferentes acciones, en un momento reparte las cartas en cuatro montones sobre la mesa y te percatas de algo, crees haber descubierto cuál es el truco, ya que detectas que uno de los montones se ve diferente al resto ¿puedes identificar cuál es?",
                R.drawable.puzzle_06,
                "D"
            ), PuzzleModel(
                7,
                "El puzzle de las sillas",
                "Tu marido ha comprado un pabellón multiusos, el cual se va a utilizar para todo tipo de espectáculos. Te ha pedido que elijas qué tipo de sillas deberían usarse teniendo en cuenta los siguientes cinco diseños entre los que elegir." + "\n" + "\n" + "¿Podrías utilizar tus conocimientos detectivescos para identificar cuál sería la más adecuada para el pabellón?",
                R.drawable.puzzle_07,
                "E"
            ), PuzzleModel(
                8,
                "Estampados",
                "Te voy a quitar el enunciado en este porque ni lo necesitas, nena \uD83D\uDE0F.",
                R.drawable.puzzle_08,
                "B"
            ), PuzzleModel(
                9,
                "Tres paraguas",
                "Realizaste el jutsu de clonación de Naruto y ahora hay 3 Iaras (quien pudiera), para diferenciaros, Kevin apoda a las otras dos como Iarami y Miara. Además, os compró un paraguas a cada una, ya que está lloviendo y quieren salir." + "\n" + "\n" + "Teniendo en cuenta que cada paraguas tiene una etiqueta para saber de quién es cada uno, si salieran de casa sin mirar las etiquetas, ¿qué probabilidad hay de que solo dos de vosotras se vayan con su propio paraguas?",
                R.drawable.puzzle_09,
                "0"
            ), PuzzleModel(
                10,
                "Un Zoo en Casa",
                "Vuestro futuro hijo, el cual no se va a llamar Lionel, está presumiendo ante sus amigos la cantidad de animalitos que tiene en su casa.\n" + "\n" + "\"Tengo 10 mascotas. No solo canarios y perros, sino también tortugas e incluso serpientes. No sé cuántos hay de cada, pero entre todos tienen 6 alas, 3 caparazones y 26 patas.\"\n" + "\n" + "¿Sabrías calcular cuántas serpientes tiene?",
                R.drawable.puzzle_10,
                "2"
            ), PuzzleModel(
                11,
                "¿Verdad o mentira?",
                "Escuchas ruidos en el cuarto y al entrar te das cuenta de que se ha roto una lámpara y... ¡Los peluches de Bruno han cobrado vida! Están discutiendo entre ellos y al parecer uno está diciendo la verdad, pero los otros dos están mintiendo. Deduce quién dice la verdad teniendo en cuenta sus declaraciones:\n" + "\n" + "A: Yo no la he roto, yo no miento nunca.\n" + "B: A miente. ¡Soy yo quien dice la verdad!\n" + "C: B miente. ¡Quien dice la verdad soy yo!",
                R.drawable.puzzle_11,
                "B"
            ), PuzzleModel(
                12,
                "Triángulos traviesos",
                "Giovanni aprendió a dibujar y te hizo el siguiente dibujo que, entre otras cosas, está formado por varios triángulos. Además, usa su poder telepático para hacerte la siguiente pregunta:" + "\n" + "\n" + "¿Cuántos triángulos tiene este dibujo? No te olvides de contar los triángulos superpuestos por separado.",
                R.drawable.puzzle_12,
                "12"
            ), PuzzleModel(
                13,
                "Puzzle de una línea 2",
                "Tu novio ha vuelto a hacer 4 dibujos más, ahora que ya sabes como funciona esto aprovecharé para no explicarlo y darte un mensaje que ha dejado el autor misterioso y totalmente anónimo de esta aplicación:" + "\n" + "\n" + "\"Hola mi reina hermosa te amo mucho, espero que te esté gustando todo y lo estés pasando bien, eres la mejor.\"" + "\n" + "\n" + "Observa los dibujos e indica cuál de ellos no se puede dibujar con una sola línea.",
                R.drawable.puzzle_13,
                "B"
            ), PuzzleModel(
                14,
                "¿Cuántas hojas?",
                "Al terminar de estudiar, dejaste las hojas de tus resúmenes encima de la mesa. Las líneas representan las secciones en las que hay varias hojas solapadas." + "\n" + "\n" + "¿Cuántas hojas hay en la parte más gruesa de la pila?",
                R.drawable.puzzle_14,
                "5"
            ), PuzzleModel(
                15,
                "Triángulos",
                "28/01/2024: Quiero dormir y solo me queda este puzle, son exactamente las 6 am y estoy cansado, así que lo voy a dejar simple, que le den. Edit: Encima he tenido que cambiarlo porque era muy largo." + "\n" + "\n" + "Al meter la pluma en el tintero una vez, consigues tinta suficiente para dibujar cuatro triángulos pequeños." + "\n" + "\n" + "Sabiendo eso: ¿cuántas veces tienes que meter la pluma en el tintero para dibujar la figura grande, que tiene 36 triángulos?",
                R.drawable.puzzle_15,
                "7"
            ), PuzzleModel(
                16,
                "El sándwich",
                "Le hiciste el lonche a tu amorcito para cuando tenga que comer, pero te diste cuenta de que el sandwich que le hiciste no cabe, ya que tiene una forma un poco extraña" + "\n" + "\n" + "¿Cuántas veces debes cortarlo para que encaje en el recipiente que tienes al lado?",
                R.drawable.puzzle_16,
                "1"
            ), PuzzleModel(
                17,
                "Reunión de vecinos",
                "Bruno, Simba, Giovanni y Kevin están en las escaleras y sabes lo siguiente:\n" + "\n" + "1. Bruno y Kevin no están uno al lado del otro.\n" + "2. Giovanni y Simba están en escalones adyacentes.\n" + "3. Kevin está en un escalón más alto que Giovanni.\n" + "\n" + "Y ahora dinos: ¿quién es Bruno?",
                R.drawable.puzzle_17,
                "D"
            ), PuzzleModel(
                18,
                "Cubos y cubitos",
                "Kevin está preparando una sala de juegos para vuestras mascotas y se puso a pintar una de las piezas, consiste en un cubo de color rojo formado por 27 cubitos de idéntico tamaño que todavía no tienen todas las caras pintadas." + "\n" + "\n" + "¿Cuántos de estos 27 cubitos tienen pintadas solo una de sus seis caras?",
                R.drawable.puzzle_18,
                "6"
            ), PuzzleModel(
                19,
                "La ventana rota",
                "Varios niños estaban jugando fuera y uno rompió una ventana de casa. Saliste gritando y con ganas de matarlos, pero tu marido te tranquilizó como para que solo quieras matar al culpable. Esto es lo que dijeron al preguntarles:" + "\n" + "\n" + "A: \"¡Yo no he sido! ¡No he roto nada!\"\n" + "B: \"Vale. Fui yo. Yo rompí la ventana.\"\n" + "C: \"No te enfades con A, el no ha sido.\"\n" + "D: \"B no fue, de verdad que no.\"\n" + "\n" + "Sabes que el culpable miente y que al menos otro también. ¿Quién fue?",
                R.drawable.puzzle_19,
                "A"
            ), PuzzleModel(
                20,
                "De aquí para arriba",
                "Tu novio ha puesto unas cuantas monedas sobre la mesa y las ha mezclado junto a una cuerda, te dice que vengas y te hace la siguiente pregunta: si tirases de los extremos de la cuerda hasta tensarla, la cuerda dividirá las monedas en dos grupos." + "\n" + "\n" + "¿Así que cuántas monedas quedarían en la parte superior?",
                R.drawable.puzzle_20,
                "9"
            ), PuzzleModel(
                21,
                "Jardín florido",
                "Por tu fanatismo con las flores has decidido alquilar una parcela en un jardín. Quieres tener espacio suficiente para plantar 12 semillas; el encargado te dice que hay cuatro parcelas disponibles (A, B, C y D). El alquiler de cada parcela se calcula según el área, pero la valla que rodea cada parcela se cobra aparte." + "\n" + "\n" + "Teniendo esto en cuenta, ¿cuál sería la parcela más cara?",
                R.drawable.puzzle_21,
                "D"
            ), PuzzleModel(
                22,
                "La mudanza",
                "Vas a mudarte a España con tu esposo y ya tienes todas tus cosas guardadas en 20 cajas, apiladas tal y como ves abajo (esto es un dato irrelevante, pero probablemente 15 de ellas serían de ropa y las otras 5 de skincare). Ahora tienes que etiquetar las cajas para saber qué contiene cada una. Pero para hacerlo vas a tener que mover algunas." + "\n" + "\n" + "¿Cuántas de las cajas se quedarían sin etiqueta si no movieras ninguna?",
                R.drawable.puzzle_22,
                "3"
            ), PuzzleModel(
                23,
                "Cinco préstamos",
                "Escuchas unos ruidos detrás del sofá del salón, así que decides apartarlo para ver qué es. Te das cuenta de que hay un pequeño hueco en la pared donde habitan 5 criaturitas diminutas que parecen estar discutiendo entre sí. Cada una de las criaturitas ha pedido y a su vez ha prestado dinero a otra. Sabes lo siguiente:" + "\n" + "\n" + "1. B pidió dinero prestado a A." + "\n" + "2. E no prestó dinero a A." + "\n" + "3. C prestó dinero a D." + "\n" + "\n" + "¿Quién prestó dinero a A.?",
                R.drawable.puzzle_23,
                "D"
            ), PuzzleModel(
                24,
                "Cuadrado compuesto",
                "Compraste una caja de los cereales que le gustan a tu pareja y te fijaste que en la parte de atrás había un pequeño juego, consistía en recortar unas piezas para formar un cuadrado, hay cuatro piezas, pero solo se tienen que utilizar tres de ellas." + "\n" + "\n" + "¿Cuál de estas piezas no se utilizaría para formar el cuadrado?",
                R.drawable.puzzle_24,
                "D"
            ), PuzzleModel(
                25,
                "Enredos",
                "Me apetecía complicar un poco este enunciado, así que decidí quitarte algunas letras." + "\n" + "\n" + "A??í tie??s ??atro cu???as e?red??as. Señ??? a???lla ??e fo??arí? u? n?do s? l? e?tir?s?s.",
                R.drawable.puzzle_25,
                "C"
            ), PuzzleModel(
                26,
                "¿Qué es E?",
                "Tu novio te ha creado una aplicación en la que hay diversos juegos, uno de los juegos trata sobre resolver enigmas y uno de ellos consiste en lo siguiente. Te muestran una imagen con cierta información y te piden que obtengas el valor de E." + "\n" + "\n" + "A = 2, B = 3, C = 3 y D = 4.",
                R.drawable.puzzle_26,
                "5"
            ), PuzzleModel(
                27,
                "5 sospechosos",
                "Decides echarte una siesta junto a tu morocho y sueñas lo siguiente: Eres una agente de policía, tienes que interrogar a los sospechosos de un delito y analizar sus declaraciones:" + "\n" + "\n" + "A: \"Uno de nosotros miente.\"" + "\n" + "B: \"Dos de nosotros mienten.\"" + "\n" + "C: \"Les conozco, tres mienten.\"" + "\n" + "D: \"No les escuches, cuatro de nosotros mienten.\"" + "\n" + "E: \"¡Los cinco estamos mintiendo!\"" + "\n" + "\n" + "¿A quiénes tendrías que creer para liberar a los que no mienten?",
                R.drawable.puzzle_27,
                "D"
            ), PuzzleModel(
                28,
                "El guarda perezoso",
                "Kevin decide trabajar como guardia nocturno en un museo para ganar algo de plata, pero sabes que es muy vago, así que ¿Cuál es el número mínimo de veces que Kevin tiene que girar para ir de una salida a la otra pasando por todas las salas?",
                R.drawable.puzzle_28,
                "2"
            ), PuzzleModel(
                29,
                "La otra orilla",
                "Ayuda a Sr. Kevin a llevar al lobo, la oveja y la col a la otra orilla del río cumpliendo las siguientes normas:" + "\n" + "\n" + "Además del piloto, la balsa solo puede llevar un animal o un objeto a la vez. Ten en cuenta que si Sr. Kevin no está ahí para impedirlo, el lobo se comerá a la oveja. En cuanto Sr. Kevin se dé la vuelta, la oveja se comerá la col." + "\n" + "\n" + "Trata de adivinar el mínimo de pasos necesarios para llevarlos a todos al otro lado.",
                R.drawable.puzzle_29,
                "7"
            ), PuzzleModel(
                30,
                "El dado intruso",
                "Has comprado un juego de mesa para jugar con tu pareja y en él vienen 4 dados, empezáis a jugar y te percatas de algo extraño, parece que se equivocaron en uno de ellos, ya que las caras están en distinta posición al resto. Teniendo en cuenta que están puestos en distintos ángulos" + "\n" + "\n" + "¿Sabrías decir cuál es el dado intruso?",
                R.drawable.puzzle_30,
                "D"
            ), PuzzleModel(
                31,
                "¿Cuál cortas?",
                "Te iba a poner el enunciado en binario, pero voy a ser más bueno, te he desplazado el enunciado 5 letras a la derecha en el abecedario, así que si quieres saber que pone tendrás que desplazarlo en la dirección opuesta" + "\n"+ "\n" + "Htwyf zs fsnqqt d ktwrf zsf hfijsf. Rj lzxyfs yzx yjyfx.",
                R.drawable.puzzle_31,
                "D"
            ), PuzzleModel(
                32,
                "Loki y Lilith",
                "Loki está hablando con su hermana." + "\n" + "\n" + "\"Lilith, si me quitara dos años de edad y te los diera a ti, ¡tendrías dos veces mi edad! Qué fuerte, ¿no?\"" + "\n" + "\"¿Y si me dieras un año más? Entonces tendría tres veces tu edad.\"" + "\n"+ "\n" + "¿Cuántos años tiene cada uno? (Escribe los dos números de seguido por ejemplo si fuera 1 y 2, Respuesta:12",
                R.drawable.puzzle_32,
                "66"
            ), PuzzleModel(
                33,
                "Reparte caramelos",
                "Tu novio te compró muchos caramelos, para ser exactos tienes 10 tarros con 50 caramelos cada uno. Quieres repartir el contenido de los tarros a bolsitas e intentas meter la mitad de un tarro en cada una. Ahora tienes 20 bolsas de caramelos." + "\n" + "\n" + "¿Qué probabilidad hay de que haya una media de 25 caramelos en cada bolsa?",
                R.drawable.puzzle_33,
                "100"
            ), PuzzleModel(
                34,
                "Ladrón a la fuga",
                "Kevin se ha convertido en un sexy ladrón y está intentando huir de la policía. La flecha indica el punto por donde ha entrado en el barrio y como ya sabes, tu novio es un pelotudo, así que tiene un método de huida muy particular: nunca vuelve sobre sus pasos ni se da la vuelta, además de que en los cruces siempre gira a la derecha o la izquierda." + "\n" + "\n" + "Como ves en el mapa, ese barrio tiene muchas salidas, ¿sabrías decir cuál no podría alcanzar nunca?",
                R.drawable.puzzle_34,
                "B"
            ), PuzzleModel(
                35,
                "¡Sálvese quien pueda!",
                "Fuisteis de crucero en barco, pero te llevaste tantas cremas solares que causaste que el barco se desviase y colisionaron, incluyéndote hay 15 personas atrapadas a bordo y se hundirá exactamente dentro de 20 minutos. Disponéis de un bote en el que caben cinco personas." + "\n" +  "\n" +  "Si un viaje de ida y vuelta a la isla con el bote lleva nueve minutos, ¿cuántas personas vivirán para ver tierra firme?" + "\n" +  "\n" +  "PD: Vamos a hacer ver que no tienes poderes de sirena en esta pregunta, porque sería trampa ¿oki?",
                R.drawable.puzzle_35,
                "13"
            ), PuzzleModel(
                36,
                "Lobos y pollitos",
                "Utiliza el poder de tu mente para mover la barca y llevar a los tres lobos y los tres pollitos al otro lado del río cumpliendo las siguientes condiciones:" + "\n" + "\n" + "1- No puedes llevar más de dos animales en cada viaje." + "\n" + "2- Para que la balsa se mueva, tiene que haber al menos un animal en ella." + "\n" + "3- Si en uno de los lados hay más lobos que pollitos, se los comerán." + "\n" + "\n" + "Cuidado, ya que los lobos tienen casi las mismas ganas de comérselos que las que yo tengo de comerte a ti.",
                R.drawable.puzzle_36,
                "11"
            ), PuzzleModel(
                37,
                "¡Chocolate!",
                "Te vino la regla y por ello tienes un antojo ENORME de comer chocolate, gracias a fuentes externas y totalmente desconocidas sabemos que el chocolate te gusta un 10⁵⁹ sobre 10, así que tu novio fue a comprarte chocolate blanco porque al parecer eres racista también. Quieres dividirla en trozos, pero debes cumplir unas normas: solo puedes partir la tableta por las líneas que la dividen y no puedes superponer los trozos que ya has partido." + "\n" + "\n" + "Teniendo eso en cuenta, ¿cuántas veces tendrás que partirla para separar las 30 onzas?",
                R.drawable.puzzle_37,
                "29"
            ), PuzzleModel(
                38,
                "Manecillas",
                "Kevin compró un reloj analógico para vuestra casa y  como una curiosidad, te explicó cuántas veces se cruzaban las agujas. Pero como te estabas haciendo el skincare, te disociaste. " + "\n" + "\n" + "Ahora, te pregunta: ¿cuántas veces se cruzarán las manecillas entre las 12 del mediodía y las 12 de medianoche, si el reloj funciona correctamente?",
                R.drawable.puzzle_38,
                "10"
            ), PuzzleModel(
                39,
                "Siguiente, por favor",
                "Estás en una mazmorra y en la pared tienes un patrón con tres posibles piezas que se ve algo similar a la imagen, para abrir la puerta necesitas poner la pieza correcta y en el caso de fallar morirás." + "\n"+ "\n" + "¿Cuál de las tres opciones debería completar la serie?",
                R.drawable.puzzle_39,
                "C"
            ), PuzzleModel(
                40,
                "Puzzle equino",
                "El otro día se te antojó tener caballos a pesar de que te dan un poco de miedito así que tu príncipe te compró tres, estas son las vueltas que pueden correr los caballos en un minuto:" + "\n" + "\n" + "Noah (A): 2 vueltas." + "\n" + "Pandora (B): 3 vueltas." + "\n" + "Zafiro (C): 4 vueltas." + "\n" + "\n" + "Si los caballos se colocan en la línea de salida y empiezan a correr en la misma dirección. ¿Cuántos minutos pasarán antes de que los tres caballos se alineen de nuevo en la línea de salida?",
                R.drawable.puzzle_40,
                "1"
            ), PuzzleModel(
                41,
                "Depósito de agua",
                "Tienen en casa un depósito de 2,5 m de profundidad, durante el día el nivel del agua sube 60 cm. Sin embargo, parece que hay una fuga y como bien boluditos que son tu marido y tú ninguno se dio cuenta, a la mañana siguiente el nivel del agua bajó 20 cm, por lo tanto, se pierde agua durante la noche." + "\n" + "\n" + "Si el nivel del agua sigue subiendo 40 cm al día, ¿cuántos días tardará en desbordarse el depósito?",
                R.drawable.puzzle_41,
                "6"
            ), PuzzleModel(
                42,
                "Tantos triángulos",
                "Este enunciado es muy corto, así que te voy a mezclar el enunciado junto a la intro de la canción del rey león\n" + "\n" + "Naaaants ingonyamaaaa bagithi babaa cuaantoos Sithiiii uhm ingonyamaaa yeah, triángulooss ingonyamaaa Naaants ingonyamaaa BAGIITHIIII baabaaaa haaay Sithi uhm ingonyaamaaaa een eeel Siyo nqoba Ingonyama nengw' enamabala... diagramaaa Ingonyama nengw' enamabala... Ingonyama nengw' enamabala...",
                R.drawable.puzzle_42,
                "17"
            ), PuzzleModel(
                43,
                "Un tarro de virus",
                "Con tu conocimiento sobre los hechizos y amarres creaste un virus para enamorar a Kevin de por vida, asegurándote de estar juntos por siempre. El virus se multiplica cada minuto y necesitas llenar un tarro al completo, al cabo de un minuto, el virus se divide en dos. Y un minuto después, cada uno de esos dos virus se divide otra vez. Ahora son cuatro. A ese ritmo, un solo virus llena el tarro entero exactamente en una hora." + "\n" + "\n" + "Sabiendo esto, ¿cuántos minutos crees que tardaría en llenarse el tarro si hubieras empezado con dos virus?",
                R.drawable.puzzle_43,
                "59"
            ), PuzzleModel(
                44,
                "La cámara y la funda",
                "Pasando por un mercadillo en vuestra luna de miel, tu esposo decide comprar una cámara para que saques a relucir ese tremendo culote venerado por los dioses. El vendedor le dice que la cámara y la funda, cuestan 310 €, la cámara 300 € más que la funda y la funda lo que le pide menos lo que cuesta la cámara. Decide comprar la cámara en otra tienda y compra solo la funda. Le da al vendedor un billete de 100 € y ve que se le iluminan los ojos." + "\n" + "\n" + "¡Ahora tiene que pensar rápido! ¿Cuánto cambio tiene que devolverle?",
                R.drawable.puzzle_44,
                "95"
            ), PuzzleModel(
                45,
                "Dados y puntos",
                "Los dados que ves en la imagen parecen bastante normalitos, pero sabes que hay algo extraño y activas tu famoso 'ojo de loca nunca se equivoca' y ¡BOOM! Observas con atención y detectas que siguen un patrón." + "\n" + "\n" + "Sabes que:" + "\n" + "A=0" + "\n" + "B=9" + "\n" + "C=6" + "\n" + "\n" + "¿A qué corresponde D entonces?",
                R.drawable.puzzle_45,
                "3"
            ), PuzzleModel(
                46,
                "Gatos y ratones",
                "Cinco gatos, los cuales vamos a nombrar para darle más emoción a esto (Garfield, Skippy, Harry, Wendy y Fionna) pueden cazar cinco ratones (Ronald, Margarette, Jess, Candy y Bigotes) en cinco minutos." + "\n" + "\n" + "Teniendo eso en cuenta, ¿cuántos gatos se necesitan para cazar 100 ratones en 100 minutos?" + "\n" + "\n" + "PD: Lo de nombrar 100 ratones lo dejamos para otro día",
                R.drawable.puzzle_46,
                "5"
            ), PuzzleModel(
                47,
                "La noria",
                "Estáis por subir en una noria y sois los primeros en la cola, os dais cuenta de que detrás hay mucha gente esperando y creéis que no les va a dar tiempo a todo el mundo a subir, así que la curiosidad os hace querer calcularlo. En la noria hay 10 cabinas de dos plazas, la noria está programada para que gire de forma que cada minuto una de las cabinas llegue a la plataforma de salida." + "\n" + "\n" + "Si la noria empezó a funcionar a las 10:00 y se cerró 30 minutos después, ¿cuál es el número máximo de personas que podrán subir?",
                R.drawable.puzzle_47,
                "42"
            ), PuzzleModel(
                48,
                "Laberinto numérico",
                "Tu novio sabe que te encantan los números ¡Así que te ha hecho un laberinto con ellos! Ten en cuenta que empiezas por el 2 del centro; desde ahí debes alcanzar una de las salidas. Estas son las reglas:" + "\n" + "\n" + "Puedes moverte el número de casillas indicado en la casilla en la que estés." + "\n" + "Puedes moverte en horizontal y en vertical, pero solo en una de ellas." + "\n" + "Tienes que caer justo en una de las casillas de salida." + "\n" + "\n" + "Rodea la salida a la que llegarás.",
                R.drawable.puzzle_48,
                "B"
            ), PuzzleModel(
                49,
                "Cuatro dígitos",
                "Te llama Kevin, te sienta y te propone resolver lo siguiente a cambio de que en las próximas 24 h puedas pedirle todo lo que quieras, te muestra las siguientes 4 cifras de un solo dígito: A, B, C y D. Todas ellas forman parte de las ecuaciones de la imagen." + "\n" + "\n" + "Encuentra los valores e indica la solución como una cifra de cuatro dígitos: ABCD.",
                R.drawable.puzzle_49,
                "2368"
            )
        )
    }
}