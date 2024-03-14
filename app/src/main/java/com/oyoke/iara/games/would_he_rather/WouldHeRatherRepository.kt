package com.oyoke.iara.games.would_he_rather

object WouldHeRatherRepository {
    private var wouldHeRatherList = mutableListOf<WouldHeRatherModel>()


    fun random(): WouldHeRatherModel {
        val position = (wouldHeRatherList.indices).random()
        val selectedQuestion = wouldHeRatherList[position]
        wouldHeRatherList.remove(selectedQuestion)

        return selectedQuestion
    }

    fun generateQuestions(category: String): Int {
        val sourceList = when (category) {
            "Casual" -> wouldYouRatherCasual.toMutableList()
            "Funny" -> wouldYouRatherFunny.toMutableList()
            "Gore" -> wouldYouRatherGore.toMutableList()
            "Couple" -> wouldYouRatherCouple.toMutableList()
            "Sexual" -> wouldYouRatherSexual.toMutableList()
            else -> (wouldYouRatherCasual.toMutableList() + wouldYouRatherFunny.toMutableList() + wouldYouRatherGore.toMutableList() + wouldYouRatherCouple.toMutableList() + wouldYouRatherSexual.toMutableList())

        }
        wouldHeRatherList = sourceList.shuffled().distinct().take(15).toMutableList()

        return wouldHeRatherList.size
    }

    fun getSize(): Int {
        val casualSize = wouldYouRatherCasual.size
        val funnySize = wouldYouRatherFunny.size
        val goreSize = wouldYouRatherGore.size
        val coupleSize = wouldYouRatherCouple.size
        val sexualSize = wouldYouRatherSexual.size
        println("casual: $casualSize, funny: $funnySize, gore: $goreSize, couple: $coupleSize, sexual: $sexualSize")
        println("total: ${casualSize + funnySize + goreSize + sexualSize}")
        return casualSize + funnySize + goreSize + coupleSize + sexualSize
    }

    private val wouldYouRatherCasual = listOf(
        WouldHeRatherModel(
            "Pasar siempre frío", "Pasar siempre calor",
        ),
        WouldHeRatherModel(
            "Viajar al futuro", "Viajar al pasado"
        ),
        WouldHeRatherModel(
            "Perder la cartera", "Perder el teléfono"
        ),
        WouldHeRatherModel(
            "Tener un trabajo que no te gusta, pero ganar mucho dinero",
            "Tener un trabajo que te gusta pero no ganar dinero"
        ),
        WouldHeRatherModel(
            "Jugar solo videojuegos multijugador", "Jugar solo videojuegos de un jugador"
        ),
        WouldHeRatherModel(
            "Solo poder comer pescado", "Solo poder comer verdura"
        ),
        WouldHeRatherModel(
            "Jugar al baloncesto", "Jugar al fútbol"
        ),
        WouldHeRatherModel(
            "No poder usar las redes sociales (excluyendo WhatsApp)",
            "Tener que publicar algo diariamente (Facebook, Instagram y Twitter)"
        ),
        WouldHeRatherModel(
            "Quedarte en casa y beber", "Salir y beber"
        ),
        WouldHeRatherModel(
            "Vivir en un pueblo toda la vida", "Tener que mudarte una vez cada 6 meses"
        ),
        WouldHeRatherModel(
            "Tener un perro de mascota", "Tener un gato de mascota"
        ),
        WouldHeRatherModel(
            "Tener un sueldo promedio, pero estar siempre desocupado",
            "Tener mucho dinero pero estar siempre ocupado",
        ),
        WouldHeRatherModel(
            "Ser hij@ único", "Tener 3 herman@s"
        ),
        WouldHeRatherModel(
            "Verter cereal en leche", "Verter leche en cereal"
        ),
        WouldHeRatherModel(
            "Ir a la playa", "Ir a la montaña"
        ),
        WouldHeRatherModel(
            "Fiestas de Halloween", "Fiestas de Carnaval"
        ),
        WouldHeRatherModel(
            "Marvel", "DC"
        ),
        WouldHeRatherModel(
            "Hacerse rico lastimando a la gente", "Ser pobre pero ayudar a la gente",
        ),
        WouldHeRatherModel(
            "Saber cantar muy bien", "Saber bailar muy bien",
        ),
        WouldHeRatherModel(
            "Ser feo y rico", "Ser atractivo y pobre"
        ),
        WouldHeRatherModel(
            "Ver películas de romance", "Ver películas de terror"
        ),
        WouldHeRatherModel(
            "Chicles de menta", "Chicles de fresa"
        ),
        WouldHeRatherModel(
            "Solo poder tomar agua", "Poder tomar diferentes bebidas excepto agua"
        ),
        WouldHeRatherModel(
            "Tener un oso panda de mascota", "Tener un tigre de mascota"
        ),
        WouldHeRatherModel(
            "Solo poder ver series", "Solo poder ver películas"
        ),
        WouldHeRatherModel(
            "Que tu dormitorio esté siempre sucio", "Que tu cocina esté siempre sucia",
        ),
        WouldHeRatherModel(
            "Curar el cáncer", "Erradicar el hambre en el mundo"
        ),
        WouldHeRatherModel(
            "Ser detective", "Ser juez"
        ),
        WouldHeRatherModel(
            "Viajar en barco", "Viajar en avión"
        ),
        WouldHeRatherModel(
            "Solo poder viajar en avión", "Solo poder viajar en barco"
        ),
        WouldHeRatherModel(
            "Comer salado", "Comer dulce"
        ),
        WouldHeRatherModel(
            "Vivir en la antigua Grecia", "Vivir en la antigua Roma"
        ),
        WouldHeRatherModel(
            "Tomar un baño de burbujas", "Tomar una ducha de vapor"
        ),
        WouldHeRatherModel(
            "Usar ropa de verano durante invierno", "Usar ropa de invierno durante verano"
        ),
        WouldHeRatherModel(
            "Tener mala memoria a corto plazo", "Tener mala memoria a largo plazo"
        ),
        WouldHeRatherModel(
            "Tener tatuajes", "Tener piercings"
        ),
        WouldHeRatherModel(
            "Ser una persona nocturna", "Ser una persona mañanera"
        ),
        WouldHeRatherModel(
            "Beber Coca cola", "Beber Pepsi"
        ),
        WouldHeRatherModel(
            "Tocar una serpiente", "Tocar una araña"
        ),
        WouldHeRatherModel(
            "Admitir que no te gusta un regalo", "Pretender que te encanta"
        ),
        WouldHeRatherModel(
            "Hacer paracaidismo", "Hacer puenting"
        ),
        WouldHeRatherModel(
            "No necesitar comer y nunca tener hambre", "No necesitar beber y nunca tener sed"
        ),
        WouldHeRatherModel(
            "Saber tocar el piano", "Saber tocar la guitarra"
        ),
        WouldHeRatherModel(
            "Tener que beber alcohol", "Tener que fumar"
        ),
        WouldHeRatherModel(
            "Tener una sala de cine privada", "Tener una sala de juegos privada"
        ),
        WouldHeRatherModel(
            "Darte un baño", "Darte una ducha"
        ),
        WouldHeRatherModel(
            "Celebrar navidad", "Celebrar tu cumpleaños"
        ),
        WouldHeRatherModel(
            "Que tu día empiece mal", "Que tu día termine mal"
        ),
        WouldHeRatherModel(
            "Ducharse siempre con agua caliente", "Ducharse siempre con agua fría"
        ),
        WouldHeRatherModel(
            "Ir a la piscina", "Ir a la playa"
        ),
        WouldHeRatherModel(
            "Tomar un vaso de leche", "Tomar una taza de café"
        ),
        WouldHeRatherModel("Jugar con teclado","Jugar con mando"),
        WouldHeRatherModel("Manejar un coche","Manejar una moto"),
        WouldHeRatherModel("One Piece","Naruto"),
        WouldHeRatherModel("Dibujar","Pintar"),

    )

    private val wouldYouRatherFunny = listOf(
        WouldHeRatherModel(
            "Tener 3 brazos", "Tener 3 piernas"
        ), WouldHeRatherModel(
            "Vivir una vida tranquila en una cabaña en medio del bosque",
            "Vivir una vida conflictiva en una mansión en medio de la ciudad"
        ), WouldHeRatherModel(
            "Nunca morir de vejez ni enfermedades y mantenerse en la edad que elijas",
            "Tener una tarjeta de crédito con dinero ilimitado"
        ), WouldHeRatherModel(
            "Vivir en un mundo con una tecnología muy avanzada pero sin animales",
            "Vivir un mundo sin electricidad pero repleto de animales amistosos"
        ), WouldHeRatherModel(
            "Caminar a cuatro patas durante el resto de tu vida", "No poder hablar"
        ), WouldHeRatherModel(
            "Quedarte en una isla con las 4 personas que más odies",
            "Quedarte solo y perdido en un desierto"
        ), WouldHeRatherModel(
            "Hacer un ruido muy extraño al reírte", "Que tu sonrisa sea horrible y extraña"
        ), WouldHeRatherModel(
            "Tener diarrea todos los días", "Estar siempre resfriado"
        ), WouldHeRatherModel(
            "Tener que vivir en una mansión que recibiste de manera gratuita pero en mitad del mar",
            "Tener que mudarte cada 3 meses con viaje y mudanza gratuitos pero a un lugar aleatorio"
        ), WouldHeRatherModel(
            "Mearte cada vez que te ríes", "Mearte cada vez que alguien menciona tu nombre"
        ), WouldHeRatherModel(
            "Tener que lamer el suelo de la calle cada vez que sales",
            "Solo poder limpiar tu inodoro con la lengua"
        ), WouldHeRatherModel(
            "Ser invisible durante 24 horas",
            "Leer los pensamientos de las personas durante 24 horas"
        ), WouldHeRatherModel(
            "Cagarte en los pantalones en público una vez al año por el resto de tu vida",
            "Cagarte encima en privado todos los días por el resto de tu vida"
        ), WouldHeRatherModel(
            "Que todos los gatos intenten atacarte cuando te vean",
            "Que todos los pájaros intenten atacarte cuando te vean"
        ), WouldHeRatherModel(
            "Ser un mago de Harry Potter", "Ser un superhéroe de Marvel"
        ), WouldHeRatherModel(
            "Ser capaz de controlar el tiempo", "Ir a dónde tú quieras simplemente pensando en ello"
        ), WouldHeRatherModel(
            "Hacer pipí sentado", "Hacer pipí de pie",
        ), WouldHeRatherModel(
            "Bailar la Macarena durante una hora seguida en la calle y retransmitiendo en directo",
            "Bailar la Macarena durante una hora seguida frente a los padres de tu pareja",
        ), WouldHeRatherModel(
            "Viajar 1 año atrás en el tiempo cada vez que estornudas",
            "Ser teletransportado a un lugar aleatorio en el mundo cada vez que te tiras un pedo",
        ), WouldHeRatherModel(
            "Tener un billete internacional ilimitado de primera clase",
            "Viajar por tu propio país de manera gratuita"
        ), WouldHeRatherModel(
            "Solo poder susurrar", "Solo poder gritar"
        ), WouldHeRatherModel(
            "Sentir la necesidad de orinar cada vez que te pones de pie",
            "Sentir la necesidad de defecar cada vez que te sientas"
        ), WouldHeRatherModel(
            "Ser capaz de controlar el agua", "Ser capaz de controlar el fuego"
        ), WouldHeRatherModel(
            "Poder reducir cosas a la mitad de su tamaño",
            "Poder aumentar cosas al doble de su tamaño"
        ), WouldHeRatherModel(
            "Ser un vampiro", "Ser un fantasma"
        ), WouldHeRatherModel(
            "Poder ser invisible", "Poder volar"
        ), WouldHeRatherModel(
            "Ser un personaje de Anime", "Ser un personaje de una película"
        ), WouldHeRatherModel(
            "Ser atacado por zombis", "Ser atacado por alienígenas"
        ), WouldHeRatherModel(
            "Vivir en el universo del señor de los anillos", "Vivir en el universo de Star Wars"
        ), WouldHeRatherModel(
            "Tener una tarjeta que te proporciona un 15% de descuento en todo",
            "Tener una tarjeta que te proporciona comida y ropa gratuita"
        ), WouldHeRatherModel(
            "Tener un gato con personalidad de perro", "Tener un perro con personalidad de gato"
        ), WouldHeRatherModel(
            "Hacer que tu videojuego favorito cobre vida en el mundo real",
            "Quedar atrapado en tu videojuego favorito"
        ), WouldHeRatherModel(
            "Vivir en el universo de Batman", "Vivir en el universo de Superman"
        ), WouldHeRatherModel(
            "Ser capaz de crear criaturas con tu mente", "Que todo sea gratis para ti"
        ), WouldHeRatherModel(
            "Poder volar por un día", "Poder ser invisible por un día"
        ), WouldHeRatherModel(
            "Ser capaz de transformarte en cualquier animal",
            "Ser capaz de transformarte en cualquier persona"
        ), WouldHeRatherModel(
            "Ver fantasmas y monstruos haya donde vayas",
            "Escuchar y sentir fantasmas y monstruos haya donde vayas"
        ), WouldHeRatherModel(
            "Ser capaz de recordar todo lo que ves", "Ser capaz de recordar todo lo que escuchas"
        ), WouldHeRatherModel(
            "Poder hacer que los animales te entiendan, pero tu no entenderlos a ellos",
            "Poder entender todo lo que hablan los animales"
        ), WouldHeRatherModel(
            "Poder atravesar objetos", "Poder ser invisible"
        ), WouldHeRatherModel(
            "Tener un pene como nariz", "Tener unos testículos como ojos"
        ), WouldHeRatherModel(
            "Tener que bailar en lugar de caminar", "Tener que cantar en lugar de hablar"
        ), WouldHeRatherModel(
            "Ensuciarte y no poder limpiarte ni cambiarte de ropa durante una semana",
            "No poder cambiar de ropa durante 4 meses"
        ), WouldHeRatherModel(
            "Vivir en el espacio", "Vivir bajo el agua"
        ), WouldHeRatherModel(
            "Solo poder hablar a través de un traductor",
            "Solo poder hablar cuando te hagan una pregunta"
        ), WouldHeRatherModel(
            "Caminar como un pato durante un mes completo",
            "Caminar como un robot durante un mes completo"
        ), WouldHeRatherModel(
            "Correr como un velociraptor cada vez que vas tarde para algo",
            "Caminar como un pingüino cada vez que vas a comprar"
        ), WouldHeRatherModel(
            "Hacer una imitación de un animal cada vez que respondes al teléfono",
            "Tener que cantar cada vez que respondes al teléfono"
        )
    )

    private val wouldYouRatherGore = listOf(
        WouldHeRatherModel(
            "Ahogarte en una bañera", "Estar atrapado en un edificio en llamas sin poder escapar"
        ), WouldHeRatherModel(
            "Salvar a 1 miembro de tu familia", "Salvar a 1000 extraños"
        ), WouldHeRatherModel(
            "Presionar un botón y hacer que mueran 10.000 personas al azar", "Suicidarte"
        ), WouldHeRatherModel(
            "Morder el vientre de una paloma", "Morder el vientre de una rata"
        ), WouldHeRatherModel(
            "Matar a un animal todos los días", "Ser vegetariano durante el resto de tu vida"
        ), WouldHeRatherModel(
            "Matar a 3 familiares a elegir con tus manos",
            "Que haya un 50% de probabilidades de que te maten"
        ), WouldHeRatherModel(
            "Estar atrapado en una isla con un asesino",
            "Estar atrapado en una isla con muchos animales grandes y hambrientos"
        ), WouldHeRatherModel(
            "Cortarte los pies", "Cortarte las manos"
        ), WouldHeRatherModel(
            "Comer un puñado de lombrices", "Masticar una cucaracha"
        ), WouldHeRatherModel(
            "Ser comido por larvas de adentro a fuera",
            "Ser comido por hormigas de afuera hacia dentro"
        ), WouldHeRatherModel(
            "Matar al gato de una anciana", "Matar al gato de una niña de 5 años"
        ), WouldHeRatherModel(
            "Matar y salir impune", "Condenar a alguien por un crimen que no ha cometido"
        ), WouldHeRatherModel(
            "Cortarte con un papel cada día hasta que sangres",
            "Golpearte un dedo hasta tener un moratón una vez a la semana"
        ), WouldHeRatherModel(
            "Que la mafia busque a tu familia", "Poner el pie en ácido sulfúrico"
        ), WouldHeRatherModel(
            "Poner la lengua en el mechero 5 s", "Caminar sobre vidrios rotos"
        ), WouldHeRatherModel(
            "Que solo te queden 1.000 palabras para morir",
            "Que solo te queden 1.000 pasos para morir"
        ), WouldHeRatherModel(
            "Beber sudor", "Beber pipí"
        ), WouldHeRatherModel(
            "Cagar hojas de afeitar", "Mear fragmentos de vidrio"
        ), WouldHeRatherModel(
            "Poner un palillo debajo de la uña del dedo gordo del pie y darle una patada a una pared",
            "Quitarte la uña del dedo meñique con un tenedor"
        ), WouldHeRatherModel(
            "Tener un dedo amputado sin anestesia", "Tener una oreja amputada con anestesia"
        ), WouldHeRatherModel(
            "Presionar un botón y hacer que mueran 1 millón de personas al azar", "Suicidarte"
        ), WouldHeRatherModel(
            "Perder un ojo de manera muy dolorosa",
            "Limpiar los inodoros de las áreas de descanso con la lengua una vez a la semana",
        ), WouldHeRatherModel(
            "Tener que matar a 500 niños con tus manos y que no te pase nada",
            "Que un asesino mate a un ser querido"
        ), WouldHeRatherModel(
            "Que un anzuelo se clave en tu ojo", "Que te corten la lengua"
        ), WouldHeRatherModel(
            "Convertirte en inmortal, pero que tu familia muera",
            "Convertirte en inmortal pero perder las piernas"
        ), WouldHeRatherModel(
            "Perder una pierna", "Perder un brazo"
        ), WouldHeRatherModel(
            "Quitarte todos los dientes con unos alicates", "Quitarte un ojo con unas tijeras"
        ), WouldHeRatherModel(
            "Morir de asfixia", "Morir ahogado"
        ), WouldHeRatherModel(
            "Que te cosan las piernas", "Que te cosan las manos"
        ), WouldHeRatherModel(
            "Comer un tampón usado", "Comer 15 hisopos usados"
        ), WouldHeRatherModel(
            "Beber un trago de menstruación", "Electrocutar tus partes intimas durante 25 segundos"
        ), WouldHeRatherModel(
            "Cortarte un dedo",
            "Comer un único plato elegido de manera aleatoria durante el resto de tu vida"
        ), WouldHeRatherModel(
            "Perder tus genitales", "Perder una pierna"
        ), WouldHeRatherModel(
            "Morir de hambre", "Morir enterrado pero con comida y bebida"
        ), WouldHeRatherModel(
            "Jugar a la ruleta con una de seis balas por 100.000",
            "Jugar a la ruleta con dos de seis balas por 300.000"
        ), WouldHeRatherModel(
            "Morderte un dedo del pie hasta el hueso", "Sacrificar a tu mascota"
        ), WouldHeRatherModel(
            "Cortarte un brazo", "Estar completamente paralizado"
        ), WouldHeRatherModel(
            "Matar a 50 personas y que nadie lo sepa",
            "No matar a nadie, pero que todo el mundo piense que sí"
        ), WouldHeRatherModel(
            "Comer diarrea una vez", "Hacerte encima siempre"
        ), WouldHeRatherModel(
            "Comerte a una persona", "Cortarte una pierna"
        ), WouldHeRatherModel(
            "Sentarte con tu trasero desnudo sobre una brasa",
            "Tocar agua hirviendo con la palma de la mano"
        ), WouldHeRatherModel(
            "Tragarte un alfiler", "Que te pongan un petardo en el culo"
        ), WouldHeRatherModel(
            "Caminar sobre brasas", "Caminar sobre una cama de clavos"
        ), WouldHeRatherModel(
            "Ser desmembrado lentamente por maquinaria industrial",
            "Ser devorado vivo por un enjambre de insectos carnívoros"
        ), WouldHeRatherModel(
            "Ser parte de un experimento de regeneración celular donde tus extremidades se cortan y vuelven a crecer",
            "Ser torturado con métodos medievales durante un día completo"
        ), WouldHeRatherModel(
            "Ser parte de un juego mortal donde debes escapar de un laberinto lleno de trampas mortales",
            "Ser torturado con métodos medievales durante un día completo"
        ), WouldHeRatherModel(
            "Ser perseguido por una horda de zombis sedientos de sangre",
            "Ser atacado por criaturas mutantes en un entorno postapocalíptico"
        ), WouldHeRatherModel(
            "Ser parte de un ritual de resurrección donde experimentas la muerte y vuelves a la vida repetidamente durante un día",
            "Ser atado a una silla y ser sometido a tortura psicológica durante varias semanas"
        ), WouldHeRatherModel(
            "Ser atrapado en un ascensor que cae desde un rascacielos",
            "Ser sometido a una experiencia de ahogamiento en sangre"
        ), WouldHeRatherModel(
            "Ser parte de un juego donde debes sacrificar algún familiar",
            "Ser jugador en un juego macabro donde cada equivocación resulta en la mutilación de una parte de tu propio cuerpo"
        )
    )

    private val wouldYouRatherCouple = listOf(
        WouldHeRatherModel(
            "Que tu pareja tenga el pelo largo", "Que tu pareja tenga el pelo corto"
        ), WouldHeRatherModel(
            "Tener una boda pequeña pero íntima", "Tener una gran boda"
        ), WouldHeRatherModel(
            "No tener hijos", "Tener 2 hijos"
        ), WouldHeRatherModel(
            "Que tu pareja sea extremadamente peluda",
            "Que tu pareja sea absolutamente lampiña (Sin pelo)"
        ), WouldHeRatherModel(
            "Que tu pareja huela a pescado diariamente",
            "Que tu pareja huela a caca dos días a la semana"
        ), WouldHeRatherModel(
            "No poder ducharte durante un mes", "No poder comunicarte con tu pareja durante un mes"
        ), WouldHeRatherModel(
            "Poder saber cuando tu pareja miente",
            "Poder escuchar todos los pensamientos de tu pareja"
        ), WouldHeRatherModel(
            "Que tu pareja sea más baja que tú", "Que tu pareja sea más alta que tú"
        ), WouldHeRatherModel(
            "Estar casado con alguien al que no quieres",
            "Estar casado con alguien que no te quiere"
        ), WouldHeRatherModel(
            "Escribir a tu pareja una carta de amor", "Cantar a tu pareja una canción"
        ), WouldHeRatherModel(
            "Tener la primera cita al aire libre", "Tener la primera cita en el interior"
        ), WouldHeRatherModel(
            "Tirarte un pedo delante de tu pareja", "Hurgarte la nariz delante de tu pareja"
        ), WouldHeRatherModel(
            "Que tu pareja te engañe",
            "Engañar a tu pareja",
        ), WouldHeRatherModel(
            "Ser el que hace la comida", "Ser el que friega los platos"
        ), WouldHeRatherModel(
            "Ser el que lava la ropa y luego la pone a tender",
            "Ser el que recoge la ropa, la dobla y después guarda"
        ), WouldHeRatherModel(
            "Engañar a tu pareja una vez y estar segur@ de que nunca lo sepa",
            "Tener que decirle a tu pareja que la has engañado con otra y que nunca pueda saber la verdad",
        ), WouldHeRatherModel(
            "Estar con una persona a elegir de por vida",
            "Poder estar con las personas que quieras, pero que las relaciones no te duren más de 1 año"
        ), WouldHeRatherModel(
            "Hacerte un tatuaje del nombre de tu pareja en tus partes",
            "Hacerte un tatuaje de la cara de tu pareja en la espalda"
        ), WouldHeRatherModel(
            "Saber como va a morir tu pareja", "Saber como vas a morir"
        ), WouldHeRatherModel(
            "Estar alejado de tu familia, pero estar con la persona que amas",
            "Estar cerca de tu familia, pero no estar con la persona que amas"
        ), WouldHeRatherModel(
            "Que tu pareja vaya delante de ti", "Ir delante de tu pareja"
        ), WouldHeRatherModel(
            "Planificar un viaje detalladamente junto a tu pareja",
            "Hacer un viaje espontáneo junto a tu pareja"
        ), WouldHeRatherModel(
            "Hacer un álbum de fotos juntos", "Hacer videos románticos juntos"
        ), WouldHeRatherModel(
            "Aprender a cocinar el uno con el otro", "Aprender a bailar el uno con el otro"
        ), WouldHeRatherModel(
            "Ir los dos a un teatro o musical", "Ir los dos a un concierto"
        ), WouldHeRatherModel(
            "Compartir un día de compras para renovar el armario",
            "Ir a un festival de música juntos para disfrutar de la música en vivo"
        ), WouldHeRatherModel(
            "Compartir las cuentas bancarias el uno con el otro",
            "Cada uno mantener cuentas bancarias separadas"
        ), WouldHeRatherModel(
            "Pasar la tarde juntos en un museo", "Pasar la tarde juntos en una galería de arte"
        ), WouldHeRatherModel(
            "Tener una cita para visitar un acuario y disfrutar de la vida marina",
            "Tener una cita en un zoológico para explorar la diversidad de animales",
        ), WouldHeRatherModel(
            "Organizar una noche de disfraces temática junto a tu pareja",
            "Hacer una noche de películas de terror junto a tu pareja"
        ), WouldHeRatherModel(
            "Ir los dos a una salida para patinar sobre hielo",
            "Ir los dos a una salida para esquiar en la nieve"
        ), WouldHeRatherModel(
            "Estar con alguien que te hace reír, pero tiene poco dinero",
            "Estar con alguien que no te hace reír, pero tiene mucho dinero"
        ), WouldHeRatherModel(
            "Ser el que se encarga en casa de fregar y barrer el suelo",
            "Ser el que se encarga en casa de limpiar las puertas y ventanas"
        ), WouldHeRatherModel(
            "Tener una noche de películas de acción y suspenso",
            "Tener una noche de películas de comedia romántica"
        ), WouldHeRatherModel(
            "Leer un libro el uno al otro antes de dormir",
            "Participar en un club de lectura juntos"
        ), WouldHeRatherModel(
            "Pasar una tarde en un parque de diversiones", "Pasar una tarde en un parque acuático"
        ), WouldHeRatherModel(
            "Hacer una sesión de fotos divertida", "Hacer una sesión de fotos profesional"
        ), WouldHeRatherModel(
            "Aprender técnicas de masaje para consentirse mutuamente",
            "Hacer una cita para un masaje en pareja"
        ), WouldHeRatherModel(
            "Tener una cita para una cata de chocolates", "Tener una cita para una cata de vinos"
        ), WouldHeRatherModel(
            "Ir juntos a un escape room", "Ir juntos a un laberinto de maíz"
        ), WouldHeRatherModel(
            "Tener una cita para un paseo en globo aerostático",
            "Tener una cita para un paseo en barca"
        ), WouldHeRatherModel(
            "Tener una cita en una feria de ciencias", "Realizar experimentos científicos en casa"
        ), WouldHeRatherModel(
            "Hacer una tarde de cata de cosas dulces", "Hacer una tarde de cata de cosas saladas"
        ), WouldHeRatherModel(
            "Tarde en pareja de jugar videojuegos", "Tarde en pareja de juegos de mesa"
        ), WouldHeRatherModel(
            "Tener una cita romántica en un observatorio", "Tener una cita romántica en un camping"
        ), WouldHeRatherModel(
            "Organizar una cita para un pícnic al amanecer",
            "Organizar una cita de pícnic al atardecer"
        ), WouldHeRatherModel(
            "Tener una cita romántica en un observatorio, observando las estrellas y planetas",
            "Una cita romántica observando estrellas desde la montaña"
        ), WouldHeRatherModel(
            "Tener mascotas juntos", "No tener mascotas"
        ), WouldHeRatherModel(
            "Realizar una búsqueda del tesoro en casa",
            "Realizar una excursión improvisada a la naturaleza"
        )
    )

    private val wouldYouRatherSexual = listOf(
        WouldHeRatherModel(
            "Tener preliminares durante más de 15 minutos",
            "Tener preliminares durante menos de 15 minutos"
        ), WouldHeRatherModel(
            "Hacer juegos de rol con tu pareja", "Hacer juegos preliminares muy largos"
        ), WouldHeRatherModel(
            "Hacer siempre cosas sexuales pero románticas en la cama",
            "Hacer siempre cosas sexuales pero pervertidas en la cama"
        ), WouldHeRatherModel(
            "Tener sexo por la tarde", "Tener sexo por la mañana"
        ), WouldHeRatherModel(
            "Tener sexo sin besar a tu pareja", "Tener sexo sin usar las manos en absoluto"
        ), WouldHeRatherModel(
            "Sólo poder masturbarte junto a tu pareja", "Sólo poder masturbarte solo@"
        ), WouldHeRatherModel(
            "Tener sexo con más frecuencia pero mediocre",
            "Tener sexo una vez al año, pero que sea genial"
        ), WouldHeRatherModel(
            "Estar siempre arriba", "Estar siempre abajo"
        ), WouldHeRatherModel(
            "Manosearse mutuamente en un cine",
            "Manosearse mutuamente en un parque aparentemente solos"
        ), WouldHeRatherModel(
            "Hacer el amor bajo la lluvia", "Hacer el amor en el agua"
        ), WouldHeRatherModel(
            "Mirar a tu pareja masturbándose", "Dejar que tu pareja te mire masturbándote"
        ), WouldHeRatherModel(
            "No poder tener orgasmos nunca más", "Que tu pareja no pueda tener orgasmos contigo"
        ), WouldHeRatherModel(
            "Tener que hacer el amor en una granja rodeados de animales",
            "Tener que hacer el amor mientras 3 desconocidos te miran"
        ), WouldHeRatherModel(
            "Ser malo besando", "Ser malo en el sexo oral"
        ), WouldHeRatherModel(
            "Que tu pareja bese mal y sea buena en la cama",
            "Que tu pareja bese bien y sea mala en la cama"
        ), WouldHeRatherModel(
            "Esposar a tu pareja", "Vendar los ojos de tu pareja"
        ), WouldHeRatherModel(
            "Decir algo inapropiado durante el sexo", "Quedarse dormido durante el sexo"
        ), WouldHeRatherModel(
            "Dar sexo oral", "Recibir sexo oral"
        ), WouldHeRatherModel(
            "Tener una experiencia BDSM, pero que nadie lo sepa",
            "Tener una experiencia de swingers con tu pareja"
        ), WouldHeRatherModel(
            "Solo poder llegar al orgasmo después que tu pareja",
            "Solo poder llegar al orgasmo antes que tu pareja"
        ), WouldHeRatherModel(
            "Que tu pareja te pueda besar solo en los labios",
            "Que tu pareja te bese en todas las partes del cuerpo, excepto los labios"
        ), WouldHeRatherModel(
            "Poner una cara poco atractiva cuando tienes un orgasmo",
            "Hacer ruidos extraños cuando tienes un orgasmo"
        ), WouldHeRatherModel(
            "Tener una pareja que se corra en 60 segundos",
            "Tener una pareja que se corra a las 3 horas"
        ), WouldHeRatherModel(
            "Que tu pareja sea atractiva y coger una vez al mes",
            "Que tu pareja sea menos atractiva y coger siempre que quieras"
        ), WouldHeRatherModel(
            "No poder tener sexo hasta casarte con tu pareja",
            "No poder estar con la persona que realmente te gusta"
        ), WouldHeRatherModel(
            "Hacer el amor en un barco", "Hacer el amor en un avión"
        ), WouldHeRatherModel(
            "Ir probando diferentes cosas sexuales", "Hacer siempre lo habitual"
        ), WouldHeRatherModel(
            "Perder la erección en mitad del sexo", "Empezar a llorar en mitad del sexo"
        ), WouldHeRatherModel(
            "Hacer un masaje erótico", "Recibir un masaje erótico"
        ), WouldHeRatherModel(
            "Que te despierte haciéndote un oral", "Que te despierte haciéndote una paja"
        ), WouldHeRatherModel(
            "Largo, pero tranquilo (60 min)", "Corto pero muy intenso (8 min)"
        ), WouldHeRatherModel(
            "Hacerlo con las luces encendidas", "Hacerlo con las luces apagadas"
        ), WouldHeRatherModel(
            "Hacer el amor en una sauna", "Hacer el amor en la nieve"
        ), WouldHeRatherModel(
            "Recibir un baile erótico", "Hacer un baile erótico"
        ), WouldHeRatherModel(
            "Que tu pareja se ría de ti cada vez que te ve desnud@",
            "Que tu pareja llore cada vez que te ve desnud@",
        ), WouldHeRatherModel(
            "Hacer el amor 10 veces en un día", "No poder hacer nada durante dos meses"
        ), WouldHeRatherModel(
            "Dominar en la cama", "Que te dominen"
        ), WouldHeRatherModel(
            "Sexo por delante", "Sexo por detrás"
        ), WouldHeRatherModel(
            "Estar con alguien que no finge sus orgasmos, pero es más simple en la cama",
            "Estar con alguien que finge sus orgasmos, pero te deja hacer todo lo que quieras en la cama"
        ), WouldHeRatherModel(
            "Comerle el culo a tu pareja", "Que te coma el culo tu pareja a ti"
        ), WouldHeRatherModel(
            "Ver películas con múltiples escenas de sexo juntos", "Leer historias eróticas juntos"
        ), WouldHeRatherModel(
            "Utilizar chocolate en los preliminares", "Utilizar nata dulce en los preliminares"
        ), WouldHeRatherModel(
            "Utilizar aceite de masaje en los preliminares",
            "Utilizar pintura corporal comestible en los preliminares"
        ), WouldHeRatherModel(
            "Noche de rol con connotación sexual", "Noche de disfraces con connotación sexual"
        ), WouldHeRatherModel(
            "Utilizar juguetes y accesorios sexuales", "Explorar nuevas posturas sexuales"
        ), WouldHeRatherModel(
            "Tener una sesión de ducha juntos", "Desnudaros el uno al otro lentamente"
        ), WouldHeRatherModel(
            "Que tu pareja solo pueda usar su boca durante juegos previos",
            "Que tu pareja solo pueda usar sus manos durante juegos previos"
        ), WouldHeRatherModel(
            "Tener videollamadas explícitas con tu pareja",
            "Que tu pareja te mande todo lo que le pidas sin oponerse"
        ), WouldHeRatherModel(
            "Lamerle la axila a tu pareja", "Lamerle el pie a tu pareja"
        ), WouldHeRatherModel(
            "Tener sexo por el chikito", "Atar del techo a tu pareja"
        )
    )
}