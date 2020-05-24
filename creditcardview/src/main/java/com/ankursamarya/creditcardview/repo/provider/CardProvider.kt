package com.ankursamarya.creditcardview.repo.provider

import com.ankursamarya.creditcardview.repo.Card
import com.ankursamarya.creditcardview.repo.UnknownCard

abstract class CardProvider {
    abstract val supportedCard: Array<Card>
    open val unknownCard: UnknownCard =
        UnknownCard()

    open fun isCardNumberValid(number: String?):Boolean {
        val card = getCardFromNumber(number);
        return when(card){
            is UnknownCard -> false
            else -> card.validate(number)
        }
    }

    open fun getCardFromNumber(number: String?): Card {
        if (number.isNullOrBlank()) {
            return unknownCard
        }

        for (card in supportedCard) {
            if (card.numberPattern.matcher(number).matches()) {
                return card
            }
        }
        return unknownCard
    }
}