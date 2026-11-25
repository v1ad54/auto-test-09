package HomeWork.asserts;

import HomeWork.pages.CartPage;
import dto.GoodsExtDto;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CartPageAssert extends AbstractAssert<CartPageAssert, CartPage> {

    public CartPageAssert(CartPage actual) {
        super(actual, CartPageAssert.class);
    }

    /**
     * Проверяет, что список товаров в корзине виден
     */
    public CartPageAssert isOpened() {
        actual.getCartModal().should(visible);
        return this;
    }

    /**
     * Проверяет, что появилось сообщение "Заказ принят в обработку!"
     */
    public CartPageAssert messageIsVisible() {
        actual.getMessage().should(visible);
        return this;
    }

    /**
     * Проверяет, что общая стоимость корзины равна ожидаемому числу
     */
    public CartPageAssert totalPriceIs(int expectedTotalPrice) {
        actual.getTotalPrice().shouldHave(text(String.valueOf(expectedTotalPrice)));
        return this;
    }

    /**
     * Суммирует стоимость товаров из списка и сравнивает с общей суммой корзины
     */
    public CartPageAssert totalPriceMatchesGoodsSum(List<GoodsExtDto> goodsList) {
        int expectedTotalPrice = 0;

        for (GoodsExtDto good : goodsList) {
            expectedTotalPrice += actual.getCartItemPrice(good.id());
        }

        return totalPriceIs(expectedTotalPrice);
    }

}
