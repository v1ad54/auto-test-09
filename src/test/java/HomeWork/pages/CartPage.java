package HomeWork.pages;

import HomeWork.asserts.CartPageAssert;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;

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

    // --- Методы взаимодействия с элементами страницы ---

    /**
     * Метод для перехода к проверкам
     */
    public CartPageAssert assertThat() {
        return new CartPageAssert(this);
    }
}
