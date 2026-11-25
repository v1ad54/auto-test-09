package HomeWork.asserts;

import org.assertj.core.api.AbstractAssert;
import HomeWork.pages.CartPage;
import static com.codeborne.selenide.Condition.visible;

public class CartPageAssert extends AbstractAssert<CartPageAssert, CartPage> {

    public CartPageAssert(CartPage actual) {
        super(actual, CartPageAssert.class);
    }

    /**
     * Проверяет что список товаров в корзине виден
     */
    public CartPageAssert isOpened() {
        actual.getCartModal().should(visible);
        return this;
    }
}
