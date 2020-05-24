package com.ankursamarya.creditcardview.repo

import com.ankursamarya.creditcardview.R
import java.util.regex.Pattern

class MasterCard  : Card(){
    override val minCardLength: Int= 16
    override val maxCardLength: Int = 16
    override val numberPattern: Pattern = Pattern.compile("\"^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[0-1]|2720)\\\\d*\"")
    override val cardIcon: Int = R.drawable.ic_mastercard
}