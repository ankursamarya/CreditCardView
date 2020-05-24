package com.ankursamarya.creditcardview.repo

import com.ankursamarya.creditcardview.R
import java.util.regex.Pattern

class UnknownCard : Card() {
    override val minCardLength: Int = 16
    override val maxCardLength: Int = 16
    override val numberPattern: Pattern = Pattern.compile("^$")
    override val cardIcon: Int = R.drawable.ic_unknown

    override fun validate(cardNumber: String?): Boolean {
        return false
    }
}