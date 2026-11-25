package HomeWork.pages;

import HomeWork.asserts.CartPageAssert;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница "Корзина"
 */
@Getter
@Setter
public class CartPage {

    // --- Элементы страницы ---

    /**
     * Список товаров в корзине
     */
    SelenideElement cartModal = $x("//*[@id='cartModal']");

    /**
     * Кнопка "Оформить заказ"
     */
    SelenideElement payButton = $x("//*[@id='makeOrder']");

    /**
     * Сообщение "Заказ принят в обработку"
     */
    SelenideElement message = $x("//*[@class='toast'][text()='Заказ принят в обработку!']");

    /**
     * Общая стоимость корзины
     */
    SelenideElement totalPrice = $x("//*[@id='total-price']");


    // --- Методы взаимодействия с элементами страницы ---

    /**
     * Клик по кнопке "Оформить заказ"
     */
    public CartPage clickPayButton() {
        payButton.click();
        return this;
    }

    /**
     * Стоимость товара в корзине
     *
     * @param id - id товара
     * @return - стоимость товара
     */
    public int getCartItemPrice(int id) {
        return Integer.parseInt(
                $x("//*[@id='cart-item-%d']//*[contains(text(),'₽')]".formatted(id))
                        .should(visible)
                        .text()
                        .replaceAll("\\D+", "")
        );
    }


    /**
     * Метод для перехода к проверкам
     */
    public CartPageAssert assertThat() {
        return new CartPageAssert(this);
    }
}
