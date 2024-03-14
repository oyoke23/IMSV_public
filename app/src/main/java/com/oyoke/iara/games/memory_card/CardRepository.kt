package com.oyoke.iara.games.memory_card

import com.oyoke.iara.R

object CardRepository {
    val cards = listOf(
        CardModel(30001, 1, image = R.drawable.card_bruno_anime),
        CardModel(30002, 2, image = R.drawable.card_giovanni_anime),
        CardModel(30003, 3, image = R.drawable.card_simba_anime),
        CardModel(30004, 4, image = R.drawable.card_bruno_armor),
        CardModel(30005, 5, image = R.drawable.card_giovanni_armor),
        //CardModel(30006, 6, image = R.drawable.card_simba_armor),
        CardModel(30007, 7, image = R.drawable.card_bruno_demonic),
        CardModel(30008, 8, image = R.drawable.card_giovanni_demonic),
        CardModel(30009, 9, image = R.drawable.card_simba_demonic),
        CardModel(300010, 10, image = R.drawable.card_bruno_angelic),
        CardModel(30011, 11, image = R.drawable.card_giovanni_angelic),
        //CardModel(30012, 12, image = R.drawable.card_simba_angelic),
        CardModel(30013, 13, image = R.drawable.card_bruno_barbie),
        CardModel(30014, 14, image = R.drawable.card_giovanni_barbie),
        CardModel(30015, 15, image = R.drawable.card_simba_barbie),
        CardModel(30016, 16, image = R.drawable.card_bruno_baby),
        //CardModel(30017, 17, image = R.drawable.card_giovanni_baby),
        CardModel(30018, 18, image = R.drawable.card_simba_baby),
        //CardModel(30019, 19, image = R.drawable.card_bruno_superhero),
        CardModel(30020, 20, image = R.drawable.card_giovanni_superhero),
        //CardModel(30021, 21, image = R.drawable.card_simba_superhero),
        //CardModel(30022, 22, image = R.drawable.card_bruno_humanized),
        CardModel(30023, 23, image = R.drawable.card_giovanni_humanized),
        //CardModel(30024, 24, image = R.drawable.card_simba_humanized),
        CardModel(30025, 25, image = R.drawable.card_bruno_robot),
        CardModel(30026, 26, image = R.drawable.card_giovanni_robot),
        CardModel(30027, 27, image = R.drawable.card_simba_robot),
        CardModel(30028, 28, image = R.drawable.card_bruno_underwater),
        CardModel(30029, 29, image = R.drawable.card_giovanni_underwater),
        CardModel(30030, 30, image = R.drawable.card_simba_underwater),

        CardModel(30040, 40, image = R.drawable.card_bruno_giovanni_babys),

    )

    fun getRandomCards(numCards : Int): List<CardModel> {
        val randomCards = cards.shuffled().take(numCards)


        return duplicateCards(randomCards).shuffled()
    }
    fun duplicateCards(randomCards: List<CardModel>): List<CardModel> {
        val duplicatedCards = randomCards.flatMap { card ->
            listOf(CardModel(card.id,card.pairId, card.image), CardModel(card.id + 1000,card.pairId, card.image))
        }
        return duplicatedCards
    }
    
}