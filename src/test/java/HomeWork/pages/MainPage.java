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
     * Сообщение, что товар добавлен в корзину
     */
    SelenideElement toast = $x("//*[@id='toast-container']//*[@class='toast']");

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
    public SelenideElement getButtonGoodsToCart(String goodName, String goodPrice) {
        return $x("//button[@data-action='add-to-cart' and @data-name='%s' and @data-price='%s']".formatted(goodName, goodPrice));
    }

    /**
     * Кнопка "В корзину" по id товара
     */
    public SelenideElement getButtonGoodsToCart(int id) {
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
    public MainPage clickButtonGoodsToCart(String goodName, String goodPrice) {
        getButtonGoodsToCart(goodName, goodPrice).click();
        return this;
    }

    /**
     * Клик по карточке товара с определенным id
     */
    public MainPage clickButtonGoodsToCart(int id) {
        getButtonGoodsToCart(id).click();
        return this;
    }

    /**
     * Возвращает Id по его наименованию и цене
     * @param goodName - наименование товара
     * @param goodPrice - цена товара
     * @return - Id товара или null, если товар не найден
     */
    public Integer getGoodsCardId(String goodName, int goodPrice) {
        SelenideElement card = getGoodsCard(goodName, goodPrice);
        if (!card.exists()) {
            return null;
        }

        String dataId = card.getAttribute("data-id");
        return dataId != null ? Integer.parseInt(dataId) : null;
    }


    /**
     * Метод для перехода к проверкам
     */
    public MainPageAssert assertThat() {
        return new MainPageAssert(this);
    }
}
