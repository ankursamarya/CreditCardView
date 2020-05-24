package com.ankursamarya.creditcardview.repo

import com.ankursamarya.creditcardview.R
import java.util.regex.Pattern

class VisaCard  : Card(){
    override val minCardLength: Int = 16
    override val maxCardLength: Int = 16
    override val numberPattern: Pattern = Pattern.compile("^4\\d*")
    override val cardIcon: Int = R.drawable.ic_visa
}