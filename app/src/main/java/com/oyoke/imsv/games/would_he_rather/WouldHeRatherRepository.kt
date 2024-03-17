package com.oyoke.imsv.games.would_he_rather

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
            "Category1" -> wouldYouRatherCategory1.toMutableList()
            "Category2" -> wouldYouRatherCategory2.toMutableList()
            "Category3" -> wouldYouRatherCategory3.toMutableList()
            "Category4" -> wouldYouRatherCategory4.toMutableList()
            "Category5" -> wouldYouRatherCategory5.toMutableList()
            else -> (wouldYouRatherCategory1.toMutableList() + wouldYouRatherCategory2.toMutableList() + wouldYouRatherCategory3.toMutableList() + wouldYouRatherCategory3.toMutableList() + wouldYouRatherCategory5.toMutableList())

        }
        wouldHeRatherList = sourceList.shuffled().distinct().take(15).toMutableList()

        return wouldHeRatherList.size
    }

    private val wouldYouRatherCategory1 = listOf(
        WouldHeRatherModel(
            "Correct Answer1", "Wrong Answer1"
        ), WouldHeRatherModel(
            "Correct Answer2", "Wrong Answer2"
        ), WouldHeRatherModel(
            "Correct Answer3", "Wrong Answer3"
        ), WouldHeRatherModel(
            "Correct Answer4", "Wrong Answer4"
        ), WouldHeRatherModel(
            "Correct Answer5", "Wrong Answer5"
        ), WouldHeRatherModel(
            "Correct Answer6", "Wrong Answer6"
        ), WouldHeRatherModel(
            "Correct Answer7", "Wrong Answer7"
        ), WouldHeRatherModel(
            "Correct Answer8", "Wrong Answer8"
        ), WouldHeRatherModel(
            "Correct Answer9", "Wrong Answer9"
        ), WouldHeRatherModel(
            "Correct Answer10", "Wrong Answer10"
        )
    )

    private val wouldYouRatherCategory2 = listOf(
        WouldHeRatherModel(
            "Correct Answer12", "Wrong Answer12"
        ), WouldHeRatherModel(
            "Correct Answer22", "Wrong Answer22"
        ), WouldHeRatherModel(
            "Correct Answer32", "Wrong Answer32"
        ), WouldHeRatherModel(
            "Correct Answer42", "Wrong Answer42"
        ), WouldHeRatherModel(
            "Correct Answer52", "Wrong Answer52"
        ), WouldHeRatherModel(
            "Correct Answer62", "Wrong Answer62"
        ), WouldHeRatherModel(
            "Correct Answer72", "Wrong Answer72"
        ), WouldHeRatherModel(
            "Correct Answer82", "Wrong Answer82"
        ), WouldHeRatherModel(
            "Correct Answer92", "Wrong Answer92"
        ), WouldHeRatherModel(
            "Correct Answer102", "Wrong Answer102"
        )
    )

    private val wouldYouRatherCategory3 = listOf(
        WouldHeRatherModel(
            "Correct Answer13", "Wrong Answer13"
        ), WouldHeRatherModel(
            "Correct Answer23", "Wrong Answer23"
        ), WouldHeRatherModel(
            "Correct Answer33", "Wrong Answer33"
        ), WouldHeRatherModel(
            "Correct Answer43", "Wrong Answer43"
        ), WouldHeRatherModel(
            "Correct Answer53", "Wrong Answer53"
        ), WouldHeRatherModel(
            "Correct Answer63", "Wrong Answer63"
        ), WouldHeRatherModel(
            "Correct Answer73", "Wrong Answer73"
        ), WouldHeRatherModel(
            "Correct Answer83", "Wrong Answer83"
        ), WouldHeRatherModel(
            "Correct Answer93", "Wrong Answer93"
        ), WouldHeRatherModel(
            "Correct Answer103", "Wrong Answer103"
        )
    )

    private val wouldYouRatherCategory4 = listOf(
        WouldHeRatherModel(
            "Correct Answer14", "Wrong Answer14"
        ), WouldHeRatherModel(
            "Correct Answer24", "Wrong Answer24"
        ), WouldHeRatherModel(
            "Correct Answer34", "Wrong Answer34"
        ), WouldHeRatherModel(
            "Correct Answer44", "Wrong Answer44"
        ), WouldHeRatherModel(
            "Correct Answer54", "Wrong Answer54"
        ), WouldHeRatherModel(
            "Correct Answer64", "Wrong Answer64"
        ), WouldHeRatherModel(
            "Correct Answer74", "Wrong Answer74"
        ), WouldHeRatherModel(
            "Correct Answer84", "Wrong Answer84"
        ), WouldHeRatherModel(
            "Correct Answer94", "Wrong Answer94"
        ), WouldHeRatherModel(
            "Correct Answer104", "Wrong Answer104"
        )
    )

    private val wouldYouRatherCategory5 = listOf(
        WouldHeRatherModel(
            "Correct Answer15", "Wrong Answer15"
        ), WouldHeRatherModel(
            "Correct Answer25", "Wrong Answer25"
        ), WouldHeRatherModel(
            "Correct Answer35", "Wrong Answer35"
        ), WouldHeRatherModel(
            "Correct Answer45", "Wrong Answer45"
        ), WouldHeRatherModel(
            "Correct Answer55", "Wrong Answer55"
        ), WouldHeRatherModel(
            "Correct Answer65", "Wrong Answer65"
        ), WouldHeRatherModel(
            "Correct Answer75", "Wrong Answer75"
        ), WouldHeRatherModel(
            "Correct Answer85", "Wrong Answer85"
        ), WouldHeRatherModel(
            "Correct Answer95", "Wrong Answer95"
        ), WouldHeRatherModel(
            "Correct Answer105", "Wrong Answer105"
        )
    )
}