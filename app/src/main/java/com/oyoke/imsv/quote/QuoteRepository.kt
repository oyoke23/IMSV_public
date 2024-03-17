package com.oyoke.imsv.quote

object QuoteRepository {
    fun random(): QuoteModel {
        val position = (quotes.indices).random()
        val selectedQuote = quotes[position]
        return selectedQuote
    }
    private val quotes = listOf(
        QuoteModel("Random quote 1."),
        QuoteModel("Random quote 2."),
        QuoteModel("Random quote 3."),
        QuoteModel("Random quote 4."),
        QuoteModel("Random quote 5."),
        QuoteModel("Random quote 6."),
        QuoteModel("Random quote 7."),
        QuoteModel("Random quote 8."),
        QuoteModel("Random quote 9."),
        QuoteModel("Random quote 10."),
        QuoteModel("Random quote 11."),
        QuoteModel("Random quote 12."),
        QuoteModel("Random quote 13."),
        QuoteModel("Random quote 14."),
        QuoteModel("Random quote 15."),
    )
}