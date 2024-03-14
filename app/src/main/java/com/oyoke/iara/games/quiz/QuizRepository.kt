package com.oyoke.iara.games.quiz

import android.util.Log

object QuizRepository {
    private var allQuestions = mutableListOf<QuizModel>()

    fun random(): QuizModel {

        val position = (allQuestions.indices).random()
        val selectedQuestion = allQuestions[position]

        allQuestions.remove(selectedQuestion)

        return selectedQuestion
    }

    fun getQuestions(difficulty: Int): Int {
        when (difficulty) {
            1 -> {
                allQuestions = quizEasy.shuffled().take(10).toMutableList()
            }

            2 -> {
                allQuestions =
                    (quizEasy.shuffled().take(10) + quizMedium.shuffled().take(5)).toMutableList()
            }

            3 -> {
                allQuestions = (quizEasy.shuffled().take(10) + quizMedium.shuffled()
                    .take(6) + quizHard.shuffled().take(4)).toMutableList()
            }

            4 -> {
                allQuestions = (quizEasy + quizMedium + quizHard).toMutableList()
            }
        }
        return allQuestions.size
    }

    fun getSize() {
        //Log (quizEasy + quizMedium + quizHard)
        Log.d("Quiz easy", "Easy Size: ${quizEasy.size}")
        Log.d("Quiz medium", "Medium Size: ${quizMedium.size}")
        Log.d("Quiz hard", "Hard Size: ${quizHard.size}")
    }

    private val quizEasy = listOf(
        QuizModel(
            "¿Cuál es el nombre del ser que ilumina tus días, convirtiéndose en el amor de tu vida?",
            "Kevin Llabería Sánchez",
            "Kevin Sánchez Llavería",
            "Kevin Llavería Sánchez",
            "Messi."
        ),
        QuizModel(
            "¿En qué día celebra su cumpleaños tu querido novio, el que llegó al mundo para alegrar tus días?",
            "23/05/2002",
            "23/06/2002",
            "21/05/2002",
            "21/06/2002"
        ),
        QuizModel(
            "¿De qué rincón del planeta es originario tu ser amado, el lugar que le vio nacer y crecer?",
            "Reus, España",
            "Barcelona, España",
            "Tarragona, España",
            "Madrid, España"
        ),
        QuizModel(
            "¿Cuál es la fecha que marcó en el calendario el inicio de su historia juntos, ese día mágico en el que empezó todo?",
            "15/07/2023",
            "15/06/2023",
            "15/08/2023",
            "15/09/2023"
        ),
        QuizModel(
            "¿Cómo conquistaste el corazón de tu futuro esposo, demostrándole que eres el encanto que lo enamora día tras día?",
            "Le encantó mi forma de ser y mi físico, fue pasando el tiempo y llegó el día en el que no pudo resistirse a mis encantos",
            "Un amarre",
            "Mostrarle una teta",
            "Todas son correctas"
        ),
        QuizModel(
            "¿Cuál es la exquisitez culinaria que siempre pide tu príncipe encantador, la que hace que su paladar baile de alegría?",
            "Pizza",
            "Lasaña",
            "Hamburguesa",
            "Salmón"
        ),
        QuizModel(
            "¿Cómo se llama el fiel compañero peludo que comparte su vida con él, el que alegra sus días con su lealtad incondicional?",
            "Simba",
            "Bruno",
            "Giovanni",
            "Carlitos"
        ),
        QuizModel(
            "¿Cuál es el color que pinta el mundo de alegría para tu ser querido?",
            "Lila",
            "Azul",
            "Negro",
            "Rojo"
        ),
        QuizModel(
            "¿Qué peculiar sonido de notificación te ha asignado en WhatsApp, ese que anuncia la llegada de tus dulces palabras?",
            "Gato",
            "Perro",
            "Conejo",
            "Vaca"
        ),
        QuizModel(
            "¿Cuál es el anime que atrapa la atención de tu media naranja?",
            "One Piece",
            "Naruto",
            "Dragon Ball Z",
            "Jujutsu Kaisen"
        ),
        QuizModel(
            "¿Cuál es el pasatiempo favorito de tu ser amado? Esa actividad que siempre disfruta.",
            "Jugar Videojuegos",
            "Ver Series",
            "Escuchar Musica",
            "Dormir"
        ),
        QuizModel(
            "¿Cómo te tiene registrado en WhatsApp, ese espacio virtual donde comparten risas y mensajes llenos de amor?",
            "Iara\uD83D\uDC31\uD83D\uDC9C",
            "Iara\uD83D\uDC36\uD83D\uDC9C",
            "Iara\uD83D\uDC37\uD83D\uDC9C",
            "Iara\uD83D\uDC2F\uD83D\uDC9C"
        ),
        QuizModel(
            "¿Cómo os conocisteis? ¿Qué marcó el inicio de esta historia que sigue creciendo día a día?",
            "MEEFF",
            "Instagram",
            "Twitter",
            "Página XXX"
        ),
        QuizModel(
            "¿Cuál es la bebida que satisface su sed y deleita su paladar, la elección que refleja su gusto?",
            "Coca-Cola",
            "Fanta Naranja",
            "Cerveza",
            "Aquarius"
        ),
        QuizModel(
            "¿Cuál fue la primera app de mascota virtual que usaron, la plataforma digital que añadió un toque de alegría a sus días?",
            "SUSH",
            "SumOne",
            "Widgetable",
            "Sólo hemos usado 3 así que aprovecharé para decirte que me encantan tus tetas\uD83D\uDE33"
        ),
        QuizModel(
            "¿De qué color son los ojos de tu amado, esos que reflejan un universo de emociones y misterios?",
            "No lo sabe ni él por qué tiene una mezcla extraña entre azul, verde y gris, dependiendo del día y la luz",
            "Marrones",
            "Verdes",
            "Azules"
        ),
        QuizModel(
            "¿Cuánto mide ese atributo especial de tu ser querido, aquel que se carga entre las piernas?",
            "20-21 cm",
            "22-23 cm",
            "18-19 cm",
            "16-17 cm"
        ),
        QuizModel(
            "¿De qué equipo de fútbol es tu ser querido? (Aunque no lo siga de cerca porque le da igual)",
            "FC Barcelona",
            "Atlético de Madrid",
            "Real Madrid",
            "Athletic Club"
        ),
        QuizModel("¿Cuántas casas ha tenido a lo largo de su vida?", "2", "1", "3", "4"),
        QuizModel(
            "¿Qué detalle de tu persona atrapó su atención de manera inmediata, desatando la chispa del interés en sus ojos?",
            "Mi cara",
            "Mi pelo",
            "Mi físico",
            "Mi forma de vestir"
        ),
        QuizModel(
            "¿Cuál de los siguientes títulos tiene tu novio?",
            "DAM (Diseño de Aplicaciones Multiplataforma)",
            "DAW (Diseño de Aplicaciones Web)",
            "ASIR (Administración de Sistemas Informáticos en Red)",
            "DAA (Diseño de Aplicaciones Android)"
        ),
        QuizModel(
            "¿Cuántos hermanos tiene tu pareja?", "1", "Ninguno", "2", "3"
        ),
        QuizModel(
            "¿Qué estación del año despierta en él una sensación de plenitud y bienestar?",
            "Ha estado pensando esta pregunta por un rato y no sabe cuál de las 3 elegir",
            "Invierno",
            "Primavera",
            "Verano"
        ),
        QuizModel(
            "¿A qué rincones del mundo ha viajado, alimentando su espíritu con las maravillas de tierras lejanas? (1/2)",
            "Andorra",
            "Francia",
            "Portugal",
            "Este no sale ni de su casa"
        ),
        QuizModel(
            "¿Cuál es su animal favorito, aquel que despierta su fascinación y ternura?",
            "Tigre Blanco",
            "Perro",
            "Gato",
            "Yo"
        ),
        QuizModel(
            "¿Qué sucedió con la tortuga que formaba parte de su vida?",
            "Se tiró por el balcón",
            "Murió por su edad",
            "La devolvieron al mar",
            "Se la entregaron a un familiar"
        ),
        QuizModel(
            "¿Quién es el considerado mejor amigo de tu ser amado?",
            "Raiser",
            "Paul",
            "Osito",
            "Dani"
        ),
        QuizModel(
            "¿Cuál de las siguientes condiciones visuales tiene?",
            "Miopía",
            "Hipermetropía",
            "Astigmatismo",
            "Daltonsimo"
        ),
        QuizModel(
            "¿Cuántos tatuajes tiene adornando su piel?", "0", "1", "2", "3"
        ),
        QuizModel(
            "¿Cuál es la fobia que se esconde en las sombras de su mente?",
            "Ninguna, como mucho a mi versión enojada",
            "Acrofobia: Miedo a las alturas",
            "Necrofobia: Miedo a a la muerte",
            "Brontofobia: Miedo a las tormentas o truenos"
        ),
        QuizModel(
            "¿Cuál era su serie de dibujos animados favorita cuando era más pequeño?",
            "Ben 10",
            "Pokémon",
            "Hora de aventuras",
            "Las tortugas ninjas"
        ),
        QuizModel(
            "¿Cuál de los siguientes nombres el que usa en los videojuegos?",
            "Oyoke",
            "Okoye",
            "Kevinsito",
            "ElMásPelotudo"
        ),
        QuizModel(
            "¿Cuál de las siguientes películas animadas de Disney le gusta más? (1/3)",
            "Hércules",
            "La Sirenita",
            "Dumbo",
            "101 Dalmatas"
        ),
        QuizModel(
            "¿Cuál de las siguientes películas animadasde de Pixar le gusta más? (1/2)",
            "Los increíbles",
            "Coco",
            "Buscando a nemo",
            "Up"
        ),
        QuizModel(
            "¿Qué tan alto es el ser que te va a acompañar durante el resto de tu vida?",
            "+-1.85",
            "+-1.80",
            "+-1.75",
            "+-1.90"
        ),
        QuizModel(
            "Qué alimento le gusta tener para acompañar una buena película",
            "Normalmente, palomitas, pero es un gordo, así que todas",
            "Golosinas",
            "Nachos con queso",
            "Palomitas"
        ),
        QuizModel(
            "¿Cuál es la parte de su cuerpo que le agrada más?", "Ojos", "Torso", "Manos", "Piernas"
        ),
        QuizModel(
            "¿Qué estilo de vestir se adecua a él?", "Natural", "Elegante", "Deportivo", "Romántico"
        ),
        QuizModel(
            "¿Cuál es su orientación sexual?",
            "Hetero",
            "Gay",
            "Bisexual",
            "Solo le gustan las de anime"
        ),
        QuizModel(
            "¿Cuál de las siguientes licencias tiene? Esa que va a utilizar para llevarte a múltiples lugares",
            "Coche",
            "Moto",
            "Autobús",
            "Avión"
        ),
        QuizModel(
            "¿Qué concierto o evento musical anhela presenciar en algún momento, alimentando su pasión por la música y la cultura?",
            "Concierto? Al único concierto que va a asistir es al que yo le voy a hacer si me hace enojar",
            "Taylor Swift",
            "Justin Bieber",
            "Ariana Grande"
        ),
        QuizModel(
            "¿Cómo prefiere descansar en la noche?",
            "Pijama completo",
            "Desnudo",
            "Ropa interior",
            "Solo pantalón"
        ),
        QuizModel(
            "¿Qué animal encuentra más afinidad con su ser, reflejando sus valores, gustos y personalidad?",
            "Perro",
            "Oso",
            "León",
            "Elefante"
        ),
        QuizModel(
            "Si pudiera dominar un nuevo idioma, ¿cuál elegiría?",
            "Japonés",
            "Árabe",
            "Que me domine a mí mejor y se deje de tanto idioma",
            "Chino"
        ),
        QuizModel(
            "¿Cuál es su tema de conversación favorito?",
            "Ciencia y tecnología",
            "Arte y cultura",
            "Viajes y aventuras",
            "Historias personales"
        ),
        QuizModel(
            "¿Cuál de estas aplicaciones móviles es la que más utiliza?",
            "Youtube",
            "TikTok",
            "Instagram",
            "Twitter"
        ),
        QuizModel(
            "Si pudiera tener una profesión completamente diferente, ¿cuál elegiría?",
            "Científico",
            "Arquitecto",
            "Juez",
            "Astronauta"
        ),
        QuizModel(
            "¿Cuál es su festival o celebración favorita?",
            "Halloween",
            "Navidad",
            "Carnaval",
            "Año Nuevo"
        ),
        QuizModel(
            "¿Qué flor despierta su admiración y ternura, llevándolo a contemplar la belleza efímera de la naturaleza?",
            "Es un copión, la Camelia",
            "La Rosa",
            "El Girasol",
            "El Tulipán"
        ),
        QuizModel(
            "Si pudiera coleccionar algo, aunque en poca cantidad (ya que no le gusta tener mucha cosa), ¿qué sería?",
            "Figuras",
            "Vinilos",
            "Relojes",
            "Antigüedades"
        ),
        QuizModel(
            "¿Cuál fue la primera película/anime/serie que disfrutaron juntos, marcando el inicio de su aventura compartida?",
            "Sinceramente no lo sabe el cree que One Piece",
            "Raya y el último dragón",
            "To Every You I’ve Loved Before – To Me, The One Who Loved You",
            "Super Mario Bros"
        )
    )
    private val quizMedium = listOf(
        QuizModel(
            "Si pudiera elegir entre una variedad de deliciosas cocinas, ¿cuál sería su preferida?",
            "Japonesa",
            "China",
            "Tailandesa",
            "Francesa"
        ),
        QuizModel(
            "¿Cómo obtuvo esa cicatriz en la barriga, la marca que cuenta una historia única en su piel?",
            "Se clavó el manillar del patinete",
            "Le mordió un tiburón",
            "Me peleé a navajazos con un cajero del Mercadona",
            "Vino un niño y me pegó un mordisco",
        ),
        QuizModel(
            "¿Cómo se llaman los padres y hermano de tu ser amado, esos pilares fundamentales de su vida?",
            "Yolanda, Oscar y Aleix",
            "Yolanda, Oscar y Alex",
            "Iolanda, Omar y Antonio",
            "Iolanda, Omar y Albert"
        ),
        QuizModel(
            "¿Cuál es el videojuego que ocupa un lugar especial en el corazón de tu ser querido?",
            "God of War",
            "Valorant",
            "Smite",
            "Pokemon"
        ),
        QuizModel(
            "¿Cómo se originó la cicatriz en la parte trasera de su cabeza, ese recordatorio único de su trayectoria?",
            "Cuando era pequeño se resbaló y se golpeó con la mesa",
            "Jugando en el parque con un amigo le dio una piedra",
            "Le empujó su hermano mientras jugaban y se golpeó",
            "Se cayó con la bicicleta cuando estaba aprendiendo a usarla"
        ),
        QuizModel(
            "¿Cuál era su asignatura favorita en el instituto?",
            "Tecnología",
            "Matemáticas",
            "Ciencias",
            "Inglés"
        ),
        QuizModel(
            "¿Qué lenguaje de programación es el que ha usado para desarrollar esta aplicación?",
            "Kotlin",
            "Java",
            "C++",
            "Python"
        ),
        QuizModel("¿Cuál de estas frutas le gusta más?", "Plátano", "Melón", "Kiwi", "Sandía"),
        QuizModel(
            "¿Cuál fue la primera figura que se compró? Aquella que ocupó el primer lugar en su estantería.",
            "Una waifu",
            "Una de One Piece",
            "Una de Dragon Ball Z",
            "Una de Spiderman"
        ),
        QuizModel(
            "¿Cuál de los siguientes artrópodos le causa más incomodidad?",
            "Ciempiés",
            "Escorpión",
            "Araña",
            "Cucaracha"
        ),
        QuizModel(
            "¿Cuál de las siguientes películas animadas de Disney le gusta más? (2/3)",
            "Aladdin",
            "101 dálmatas",
            "El rey león",
            "¡Rompe Ralph!"
        ),
        QuizModel(
            "¿Cuál de las siguientes películas animadas de Pixar le gusta más? (2/2)",
            "Monstruos S.A.",
            "Toy Story",
            "Ratatouille",
            "Cars"
        ),
        QuizModel(
            "¿Qué supuesta alergia le diagnosticaron?",
            "Leve al melocotón",
            "Leve al marisco",
            "Leve al polen",
            "No tiene alergias"
        ),
        QuizModel(
            "¿Cuál de los siguientes géneros de películas le gustan menos? Ese que generalmente prefiere evitar.",
            "Terror",
            "Romance",
            "Comedia",
            "Drama"
        ),
        QuizModel(
            "Sabemos que es un amante de las pizzas, pero... ¿Cuál es su pizza favorita?",
            "Barbacoa",
            "Peperoni",
            "4 quesos",
            "Pollo"
        ),
        QuizModel(
            "¿Qué sabor de helado despierta le despierta felicidad, transportándolo a momentos de dulce deleite y placer?",
            "Chocolate",
            "Fresa",
            "Limón",
            "Vainilla"
        ),
        QuizModel(
            "¿Cuál de los siguientes superhéroes le gusta más?",
            "Spider-Man",
            "Iron Man",
            "Hulk",
            "Batman"
        ),
        QuizModel(
            "En el caso de poder tener una habilidad sobrenatural, ¿cuál elegiría?",
            "Teletransportarse",
            "Volar",
            "Invisibilidad",
            "Leer mentes"
        ),
        QuizModel(
            "Si pudiera viajar a través del tiempo y vivir en cualquier época de la historia, ¿dónde iría?",
            "Futuro distópico",
            "Renacimiento",
            "Edad Media",
            "Antiguo Egipto"
        ),
        QuizModel(
            "Si pudiera tener cualquier habilidad artística, ¿cuál elegiría?",
            "Dibujo",
            "Escultura",
            "Música",
            "Danza"
        ),
        QuizModel(
            "Si pudiera sumergirse en un universo de fantasía y maravillas, ¿en cuál sería?",
            "Harry Potter",
            "El Señor de los Anillos",
            "Juego de Tronos",
            "Mundo de Narnia"
        ),
        QuizModel(
            "¿Cuál es su estilo arquitectónico que más refleja sus gustos y sensibilidades estéticas?",
            "Moderno",
            "Gótico",
            "Renacentista",
            "Art Decó"
        ),
        QuizModel(
            "¿Cuál de los siguientes deportes extremos le gustaría probar primero?",
            "Paracaidismo",
            "Bungee jumping",
            "Surf",
            "Ala delta"
        ),
        QuizModel(
            "Si pudiera compartir su vida con una mascota exótica, ¿qué compañero peludo o escamoso llenaría su hogar de excentricidad y amor?",
            "Lémur",
            "Armadillo",
            "Serpiente",
            "Tucán"
        ),
        QuizModel(
            "¿Qué destreza atlética preferiría elegir en caso de poder masterizar una de ellas?",
            "Sprint",
            "Salto alto",
            "Lanzamiento de jabalina",
            "Gimnasia artística"
        ),
        QuizModel(
            "¿Cuánto tiempo llevó el proceso de corrección dental con brackets?",
            "+-1.5 años",
            "+-8 meses",
            "+-3 años",
            "+-4 meses"
        ),
        QuizModel(
            "¿Cuál de las siguientes cicatrices le sigue doliendo a día de hoy si se aprieta en ella?",
            "Cicatriz de la cabeza",
            "Cicatriz de la barriga",
            "Cicatriz de sus partes",
            "Ninguna de ellas"
        ),
        QuizModel(
            "¿En qué día empezaron a hablar, marcando el inicio de su conexión especial?",
            "28/06/2023",
            "27/06/2023",
            "26/06/2023",
            "25/06/2023"
        ),
        QuizModel(
            "¿Cuál de los siguientes objetos consiguió de manera gratuita en Amazon, ya que por una equivocación le hicieron un 100% de descuento?",
            "Una lámpara con forma de luna",
            "Una lámpara con forma de sol",
            "Una lámpara con forma de estrella",
            "Una lámpara con forma de planeta",
        ),
        QuizModel(
            "¿Sabrías decir qué número de pie calza tú pareja?",
            "+-42",
            "+-40",
            "+-38",
            "+-44"
        )
    )
    private val quizHard = listOf(
        QuizModel(
            "¿Qué géneros de videojuegos prefiere, esos mundos virtuales que le cautivan?",
            "Aventura y acción",
            "Disparos y estrategia",
            "Rol y disparos",
            "Acción y simulación"
        ), QuizModel(
            "Sabemos que la música no es lo suyo, pero ¿cuáles son los géneros que le atraen más?",
            "Pop y Techno",
            "Techno y Metal",
            "Pop y Reggae",
            "Reggae y Blues"
        ), QuizModel(
            "¿Cuál de estas películas es la que más disfruta, la que ha dejado una huella especial en su corazón?",
            "Batman: El Caballero de la noche",
            "Harry Potter y la cámara secreta",
            "Transformers: El Lado Oscuro de la Luna",
            "Piratas del Caribe: El Cofre de la Muerte"
        ), QuizModel(
            "En la app llamada 'Widgetable', ¿cuáles fueron las primeras mascotas que compartieron en su espacio virtual?",
            "Loki, Lilith y Thor",
            "Loki, Lilith y Zeus",
            "Loki, Lilith y Alaska",
            "Thor, Zeus y Alaska"
        ), QuizModel(
            "¿Qué mascotas ha tenido a lo largo de su vida, esas compañías entrañables que han dejado huella en su corazón?",
            "1 Perro, 1 Tortuga, 3 Peces, 3 Periquitos, 1 Gallina",
            "1 Perro, 1 Tortuga, 1 Lagarto, 2 Periquitos, 1 Serpiente",
            "1 Perro, 1 Tortuga, 2 Loros, 3 Hamsters, 1 Gato",
            "1 Perro, 1 Tortuga, 3 Periquitos, 1 Gato"
        ), QuizModel(
            "¿Cuál es su número de teléfono? Ese número que adquiriste después de tu seducción.",
            "625527742",
            "625523321",
            "625237712",
            "625577224"
        ), QuizModel(
            "¿Por qué motivo tuvo que ir a urgencias hace años?",
            "Algo dentro del oído",
            "Se le metió algo en el ojo y le escocía",
            "No podía respirar por la nariz",
            "Dolor muy agudo en la palma de la mano"
        ), QuizModel(
            "¿A qué rincones del mundo ha viajado, alimentando su espíritu con las maravillas de tierras lejanas? (2/2)",
            "Italia",
            "Estados Unidos",
            "Reino Unido",
            "Grecia"
        ), QuizModel(
            "¿Cuál de las siguientes películas animadas de Disney le gusta más? (3/3)",
            "El planeta del tesoro",
            "Atlantis el imperio perdido",
            "Los aristogatos",
            "La leyenda de Sleepy Hollow"
        ), QuizModel(
            "¿Con qué le gustaba jugar y coleccionar cuando era pequeño?",
            "Gormiti",
            "Bakugans",
            "Beyblades",
            "Hot Wheels"
        ), QuizModel(
            "¿Cuál de los siguientes juegos de mesa prefiere jugar?",
            "Cluedo",
            "Monopoly",
            "Pictionary",
            "Risk"
        ), QuizModel(
            "¿Cuál de las siguientes comidas le causa más rechazo?",
            "Setas",
            "Brócoli",
            "Espinacas",
            "Hígado"
        ), QuizModel(
            "¿Cuál de los siguientes streamers le gusta más?",
            "ElXokas",
            "Vegetta777",
            "Ibai",
            "TheGrefg"
        ), QuizModel(
            "¿Cuál de estos programas antiguos era el que más disfrutaba hace años?",
            "Humor Amarillo",
            "¡A que te ríes!",
            "American Pickers",
            "Dame una pista"
        ), QuizModel(
            "¿Cuál es su estrella de cine favorita?",
            "Keanu Reeves",
            "Dwayne Johnson",
            "Brad Pitt",
            "Leonardo DiCaprio"
        ), QuizModel(
            "¿Cuál es su juego de video favorito de todos los tiempos?",
            "The Witcher 3: Wild Hunt",
            "The Legend of Zelda: Ocarina of Time",
            "Super Mario Bros.",
            "Final Fantasy VII",
        ), QuizModel(
            "Hay una articulación del cuerpo que cada vez que dobla le hace un ruidito y que a veces le puede llegar hasta doler un poquito, ¿cuál es?",
            "La rodilla derecha",
            "La rodilla izquierda",
            "El codo derecho",
            "El codo izquierdo",
        ), QuizModel(
            "¿Cuál es su signo del zodiaco, aquella constelación que \"supuestamente\" marca su destino y define su personalidad?",
            "Géminis",
            "Tauro",
            "Cáncer",
            "Aries"
        ), QuizModel(
            "¿De qué le acusaron cuando era pequeño y en realidad no fue su culpa?",
            "Hacer que el hermano se golpee con una verja al empujarlo",
            "Romper la ventana del vecino jugando al fútbol",
            "Dejar la puerta de la casa abierta durante toda la noche",
            "Romper el jarrón de la abuela jugando a las peleas"
        ), QuizModel(
            "¿Cuál es la gema que \"supuestamente\" está asignada a su mes de nacimiento?",
            "Esmeralnda",
            "Rubí",
            "Zafiro",
            "Diamante"
        ), QuizModel(
            "¿Qué frutos secos son los que más le gustan?",
            "Pistachos",
            "Almendras",
            "Piñones",
            "Avellanas"
        )
    )
}