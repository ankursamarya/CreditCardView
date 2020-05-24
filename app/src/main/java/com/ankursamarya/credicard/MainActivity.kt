package com.ankursamarya.credicard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ankursamarya.creditcardview.repo.Card
import com.ankursamarya.creditcardview.repo.provider.defaultCardProvider
import com.ankursamarya.creditcardview.view.CreditCardView
import com.ankursamarya.creditcardview.view.OnCardChangeListener


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val creditCardView = findViewById<CreditCardView>(R.id.card_view);

        /*creditCardView.setCardChangeListener(object : OnCardChangeListener{
            override fun onCardChange(card: Card) {
                Log.d(TAG, "onCardChange")
            }
        })
        creditCardView.setCardProvider(defaultCardProvider)

        creditCardView.getCardNumber()
        creditCardView.getSelectedCard()*/



    }
}


