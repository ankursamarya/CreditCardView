package com.ankursamarya.creditcardview.repo

import com.ankursamarya.creditcardview.R
import java.util.regex.Pattern

class DinersClubCard : Card(){
    override val minCardLength: Int = 14
    override val maxCardLength: Int = 14
    override val numberPattern: Pattern = Pattern.compile("^(36|38|30[0-5])\\d*")
    override val cardIcon: Int = R.drawable.ic_diners
    override val numberSpaceIdices = intArrayOf(4, 10)
}