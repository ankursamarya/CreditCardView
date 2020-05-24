package com.ankursamarya.creditcardview.repo

import android.text.TextUtils
import java.util.regex.Pattern

abstract class Card {

    abstract val minCardLength: Int
    abstract val maxCardLength: Int
    abstract val numberPattern: Pattern
    abstract val cardIcon: Int
    open val numberSpaceIdices = intArrayOf(4, 8, 12)
    private var numberHint: String? = null

    /**
     * Performs the Luhn check on the given card number.
     *
     * @param cardNumber a String consisting of numeric digits (only).
     * @return `true` if the sequence passes the checksum
     * @throws IllegalArgumentException if `cardNumber` contained a non-digit (where [ ][Character.isDefined] is `false`).
     * @see [Luhn Algorithm
    ](http://en.wikipedia.org/wiki/Luhn_algorithm) */
    private fun isLuhnValid(cardNumber: String): Boolean {
        val reversed = StringBuffer(cardNumber).reverse().toString()
        val len = reversed.length
        var oddSum = 0
        var evenSum = 0
        for (i in 0 until len) {
            val c = reversed[i]
            require(Character.isDigit(c)) {
                String.format("Not a digit: '%s'", c)
            }
            val digit = Character.digit(c, 10)
            if (i % 2 == 0) {
                oddSum += digit
            } else {
                evenSum += digit / 5 + 2 * digit % 10
            }
        }
        return (oddSum + evenSum) % 10 == 0
    }


    /**
     * @param cardNumber The card number to validate.
     * @return `true` if this card number is locally valid.
     */
    open fun validate(cardNumber: String?): Boolean {
        if (cardNumber.isNullOrEmpty() || !TextUtils.isDigitsOnly(cardNumber)) {
            return false
        }
        val numberLength = cardNumber.length
        if (numberLength < minCardLength || numberLength > maxCardLength) {
            return false
        }
        if (!numberPattern.matcher(cardNumber).matches()) {
            return false
        }
        return isLuhnValid(cardNumber)
    }


    open fun getNumberHint(): String {
        if (numberHint.isNullOrBlank()) {
            numberHint = ""
            for (i in 1..maxCardLength) {
                numberHint = numberHint.plus("X")
                if (numberSpaceIdices.contains(i)) {
                    numberHint = numberHint.plus(" ")
                }
            }
        }
        return numberHint!!
    }

}