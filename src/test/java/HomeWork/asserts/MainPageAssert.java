package HomeWork.asserts;

import org.assertj.core.api.AbstractAssert;
import HomeWork.pages.MainPage;

import static com.codeborne.selenide.Condition.*;

public class MainPageAssert extends AbstractAssert<MainPageAssert, MainPage> {

    public MainPageAssert(MainPage actual) {
        super(actual, MainPageAssert.class);
    }

    /**
     * Проверка заголовка страницы
     */
    public MainPageAssert pageTitleExists() {
        actual.getPageTitle().should(exist);
        return this;
    }

    /**
     * Проверка кнопки "Администрирование"
     */
    public MainPageAssert adminButtonIsVisible() {
        actual.getAdminButton().should(visible);
        return this;
    }

    /**
     * Проверка кнопки "Корзина"
     */
    public MainPageAssert cartButtonIsVisible() {
        actual.getCartButton().should(visible);
        return this;
    }

    /**
     * Проверка карточки товара по наименованию и цене
     */
    public MainPageAssert goodCardIsVisible(String goodName, double goodPrice) {
        actual.getGoodsCard(goodName, goodPrice).should(visible);
        return this;
    }

    /**
     * Проверка карточки товара по id
     */
    public MainPageAssert goodCardIsVisible(int id) {
        actual.getGoodsCard(id).should(visible);
        return this;
    }

    /**
     * Проверка количества товаров на счетчике корзины
     */
    public MainPageAssert cartCounterHasCount(int expectedCount) {
        actual.getCartCounter().shouldHave(exactText(String.valueOf(expectedCount)));
        return this;
    }

}
