package com.ankursamarya.creditcardview.view

import com.ankursamarya.creditcardview.repo.Card

interface OnCardChangeListener {
    fun onCardChange(card: Card)
}