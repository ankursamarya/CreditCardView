package com.ankursamarya.creditcardview.repo

import com.ankursamarya.creditcardview.R
import java.util.regex.Pattern

class MaestroCard  : Card(){
    override val minCardLength: Int = 12
    override val maxCardLength: Int = 19
    override val numberPattern: Pattern = Pattern.compile("^(5018|5020|5038|5[6-9]|6020|6304|6703|6759|676[1-3])\\d*")
    override val cardIcon: Int = R.drawable.ic_maestro
}