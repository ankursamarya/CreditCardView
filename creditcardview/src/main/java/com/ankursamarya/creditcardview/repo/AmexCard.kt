package com.ankursamarya.creditcardview.repo

import com.ankursamarya.creditcardview.R
import java.util.regex.Pattern

class AmexCard : Card(){
    override val minCardLength: Int = 15
    override val maxCardLength: Int = 15
    override val numberPattern: Pattern = Pattern.compile("^3[47]\\d*")
    override val cardIcon: Int = R.drawable.ic_amex
    override val numberSpaceIdices = intArrayOf(4, 10)


}