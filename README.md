# CreditCardView
Android Credit Card View Lib 

Simple Credit Card view
## Example
![](/image/demo.gif)
![](/image/image_1.png)
![](/image/image_2.png)
* `Card Icon changes once card type detected.
* `Card Number fomatter according Card type.
* `Error message if card is not valid/supported.


## Usage
To add in layout
```xml
<com.ankursamarya.creditcardview.view.CreditCardView
    android:id="@+id/card_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
public methods
```kotlin
creditCardView.setCardChangeListener(object : OnCardChangeListener{
            override fun onCardChange(card: Card) {
                Log.d(TAG, "onCardChange")
            }
        })
        creditCardView.setCardProvider(defaultCardProvider)

        creditCardView.getCardNumber()
        creditCardView.getSelectedCard()
```
## Customization
Currently supporting Five cards. To create new card just have to extend `Card` class like this.
```kotlin
class AmexCard : Card(){
    override val minCardLength: Int = 15
    override val maxCardLength: Int = 15
    override val numberPattern: Pattern = Pattern.compile("^3[47]\\d*")
    override val cardIcon: Int = R.drawable.ic_amex
    override val numberSpaceIdices = intArrayOf(4, 10)


}
```
And to this card to supported list, create new provider by extending `CardProvider`
```kotlin
object defaultCardProvider : CardProvider(){
    override val supportedCard: Array<Card> = arrayOf(
            AmexCard(),
            DinersClubCard(),
            MasterCard(),
            VisaCard(),
            MaestroCard())
}

creditCardView.setCardProvider(defaultCardProvider)
```
