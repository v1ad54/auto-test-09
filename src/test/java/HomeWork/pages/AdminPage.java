package HomeWork.pages;

import HomeWork.asserts.AdminPageAssert;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;

import static com.codeborne.selenide.Selenide.$x;


/**
 * Страница "Администрирование"
 */
@Getter
@Setter
public class AdminPage {

    // --- Элементы страницы ---

    /**
     * Заголовок страницы
     */
    SelenideElement pageTitle = $x("//title[contains(text(), 'Please sign in')]");

    /**
     * Форма авторизации
     */
    SelenideElement loginForm = $x("//form[@action='/login']");

    /**
     * Строка ввода логина
     */
    SelenideElement loginInput = $x("//*[@id='username']");

    /**
     * Строка ввода пароля
     */
    SelenideElement passwordInput = $x("//*[@id='password']");

    /**
     * Кнопка авторизации
     */
    SelenideElement submitButton = $x("//*[@type='submit']");

    /**
     * Строка ввода наименования товара
     */
    SelenideElement goodsNameInput = $x("//*[@id='n-name']");
    /**
     * Строка ввода цены товара
     */
    SelenideElement goodsPriceInput = $x("//*[@id='n-price']");
    /**
     * Кнопка добавления товара
     */
    SelenideElement buttonAdd = $x("//*[@id='add-btn']");



    // --- Методы взаимодействия с элементами страницы ---

    /**
     * Ввод логина
     */
    public AdminPage setLoginInput(String login) {
        loginInput.setValue(login);
        return this;
    }

    /**
     * Ввод пароля
     */
    public AdminPage setPasswordInput(String password) {
        passwordInput.setValue(password);
        return this;
    }

    /**
     * Клик по кнопке авторизации
     */
    public AdminPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    /**
     * Метод авторизации
     */
    public AdminPage loginAs(String login, String password) {
        return setLoginInput(login)
                .setPasswordInput(password)
                .clickSubmitButton();
    }

    /**
     * Ввод наименования товара
     */
    public AdminPage setGoodsNameInput(String goodsName) {
        goodsNameInput.setValue(goodsName);
        return this;
    }

    /**
     * Ввод цены товара (строкой)
     */
    public AdminPage setGoodsPriceInput(String goodsPrice) {
        goodsPriceInput.setValue(goodsPrice);
        return this;
    }

    /**
     * Ввод цены товара (числом)
     */
    public AdminPage setGoodsPriceInput(double goodsPrice) {
        goodsPriceInput.setValue(String.valueOf(goodsPrice));
        return this;
    }

    /**
     * Клик по кнопке добавления товара
     */
    public AdminPage clickButtonAdd() {
        buttonAdd.click();
        return this;
    }

    /**
     * Комплексный метод добавления нового товара
     */
    public AdminPage addGood(String goodsName, double goodsPrice) {
        return setGoodsNameInput(goodsName)
                .setGoodsPriceInput(goodsPrice)
                .clickButtonAdd();
    }


    /**
     * Метод для перехода к проверкам
     */
    public AdminPageAssert assertThat() {
        return new AdminPageAssert(this);
    }
}
