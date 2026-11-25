package HomeWork.pages;

import HomeWork.asserts.MainPageAssert;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница
 */
@Getter
@Setter
public class MainPage {

    // --- Элементы страницы ---

    /**
     * Заголовок страницы
     */
    SelenideElement pageTitle = $x("//title[contains(text(), 'Please sign in')]");

    /**
     * Кнопка "Администрирование"
     */
    SelenideElement adminButton = $x("//*[@href='/admin']");

    /**
     * Кнопка "Корзина"
     */
    SelenideElement cartButton = $x("//*[@id='open-cart-btn']");

    /**
     * Счетчик товаров в корзине
     */
    SelenideElement cartCounter = $x("//*[@id='cart-count']");

    /**
     * Карточка товара по наименованию и цене
     */
    public SelenideElement getGoodsCard(String goodName, double goodPrice) {
        return $x("//div[@data-name='%s' and @data-price='%s']".formatted(goodName, (int)goodPrice));
    }

    /**
     * Карточка товара по id
     */
    public SelenideElement getGoodsCard(int id) {
        return $x("//div[@id='card-%d']".formatted(id));
    }

    /**
     * Кнопка "В корзину" по наименованию и цене товара
     */
    public SelenideElement getGoodsToCart(String goodName, String goodPrice) {
        return $x("//button[@data-action='add-to-cart' and @data-name='%s' and @data-price='%s']".formatted(goodName, goodPrice));
    }

    /**
     * Кнопка "В корзину" по id товара
     */
    public SelenideElement getGoodsToCart(int id) {
        return $x("//button[@data-action='add-to-cart' and @data-id='%d']".formatted(id));
    }


    // --- Методы взаимодействия с элементами страницы ---

    /**
     * Клик по кнопке "Администрирование"
     */
    public AdminPage clickAdminButton() {
        adminButton.click();
        return new AdminPage(); // Возвращаем страницу "Администрирование"
    }

    /**
     * Клик по кнопке "Корзина"
     */
    public CartPage clickCartButton() {
        cartButton.click();
        return new CartPage(); // Возвращаем страницу "Корзина"
    }

    /**
     * Клик по карточке товара с определенным наименованием и ценой
     */
    public MainPage clickGoodsToCart(String goodName, String goodPrice) {
        getGoodsToCart(goodName, goodPrice).click();
        return this;
    }

    /**
     * Клик по карточке товара с определенным id
     */
    public MainPage clickGoodsToCart(int id) {
        getGoodsToCart(id).click();
        return this;
    }


    /**
     * Метод для перехода к проверкам
     */
    public MainPageAssert assertThat() {
        return new MainPageAssert(this);
    }
}
