package com.ankursamarya.creditcardview.repo.provider

import com.ankursamarya.creditcardview.repo.*

object defaultCardProvider : CardProvider(){
    override val supportedCard: Array<Card> = arrayOf(
            AmexCard(),
            DinersClubCard(),
            MasterCard(),
            VisaCard(),
            MaestroCard())
}