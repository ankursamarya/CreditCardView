package com.ankursamarya.creditcardview.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.*
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ankursamarya.creditcardview.R
import com.ankursamarya.creditcardview.databinding.LayoutCardViewBinding
import com.ankursamarya.creditcardview.repo.Card
import com.ankursamarya.creditcardview.repo.UnknownCard
import com.ankursamarya.creditcardview.repo.provider.CardProvider
import com.ankursamarya.creditcardview.repo.provider.defaultCardProvider


public class CreditCardView : FrameLayout, TextWatcher {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?,
            defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int,
            defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private val binding: LayoutCardViewBinding
    private var selectedCard: Card = UnknownCard()
    private var cardProvider : CardProvider = defaultCardProvider
    private var cardChangeListener: OnCardChangeListener? = null

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_card_view,
                this, true)

        setBackgroundResource(R.drawable.card_background)

        binding.cardNumHint.paint.letterSpacing = .1f
        binding.clickableView.setOnClickListener {
            showSoftKeyboard()
        }

        binding.cardNum.addTextChangedListener(this)
        binding.cardNum.paint.letterSpacing = .1f
        binding.cardNum.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.cardNum.viewTreeObserver.removeOnGlobalLayoutListener(this)
                showSoftKeyboard()
            }
        })
        onCardChange(selectedCard)
    }

    override fun afterTextChanged(editable: Editable?) {
        onCardNumberChanged(editable)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //ignore
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //ignore
    }

    private fun onCardNumberChanged(editable: Editable?){
        editable?.let {
            val newCard = cardProvider.getCardFromNumber(it.toString())
            if (newCard != selectedCard) {
                selectedCard = newCard
                onCardChange(selectedCard)
            }
            formatCardNumber(editable)
            showError(it.length)
        }
    }

    private fun setNumberHint() {
        val spannableString = SpannableString(selectedCard.getNumberHint());
        spannableString.setSpan(CardDigitsSpan(false), 0, spannableString.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.cardNumHint.setText(spannableString, TextView.BufferType.SPANNABLE)
    }

    private fun setCardIcon() {
        binding.cardIcon.setImageDrawable(ContextCompat.getDrawable(context, selectedCard.cardIcon))
    }

    private fun showError(count: Int) {
        Log.d("TAG", "")
        val show = selectedCard.maxCardLength == count && !selectedCard.validate(getCardNumber())
        Log.d("TAG", show.toString())
        binding.errorText.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun formatCardNumber(editable: Editable) {

        val paddingSpans: Array<CardDigitsSpan> =
            editable.getSpans(0, editable.length, CardDigitsSpan::class.java)
        for (span in paddingSpans) {
            editable.removeSpan(span)
        }

        for (i in 0 until editable.count()) {
            editable.setSpan(CardDigitsSpan(selectedCard.numberSpaceIdices.contains(i)), i,
                    i + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }

    private fun onCardChange(card: Card) {
        cardChangeListener?.onCardChange(card)

        setNumberHint()
        setCardIcon()
        setMaxLength()
    }

    private fun setMaxLength() {
        binding.cardNum.setFilters(arrayOf<InputFilter>(LengthFilter(selectedCard.maxCardLength)))
    }

    private fun showSoftKeyboard() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.cardNum.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_DOWN,
                    binding.cardNum.width.toFloat(),
                    0f,
                    0))
            binding.cardNum.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_UP,
                    binding.cardNum.width.toFloat(),
                    0f,
                    0))
        }, 100)
    }

    fun getCardNumber(): String {
        return binding.cardNum.text.toString()
    }

    fun getSelectedCard(): Card {
        return selectedCard
    }

    fun setCardProvider(cardProvider: CardProvider){
        this.cardProvider = cardProvider
        onCardNumberChanged(binding.cardNum.text)
    }

    fun setCardChangeListener(listener: OnCardChangeListener){
        cardChangeListener = listener
    }

}