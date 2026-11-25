package HomeWork.asserts;

import org.assertj.core.api.AbstractAssert;
import HomeWork.pages.AdminPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert extends AbstractAssert<AdminPageAssert, AdminPage> {

    public AdminPageAssert(AdminPage actual) {
        super(actual, AdminPageAssert.class);
    }

    /**
     * Проверка заголовка страницы
     */
    public AdminPageAssert pageTitleExists() {
        actual.getPageTitle().should(exist);
        return this;
    }

    /**
     * Проверка формы авторизации
     */
    public AdminPageAssert loginFormIsVisible() {
        actual.getLoginForm().should(visible);
        return this;
    }

    /**
     * Проверка строки ввода логина
     */
    public AdminPageAssert loginInputIsVisible() {
        actual.getLoginInput().should(visible);
        return this;
    }

    /**
     * Проверка строки ввода пароля
     */
    public AdminPageAssert passwordInputIsVisible() {
        actual.getPasswordInput().should(visible);
        return this;
    }

    /**
     * Проверка кнопки авторизации
     */
    public AdminPageAssert submitButtonIsVisible() {
        actual.getSubmitButton().should(visible);
        return this;
    }

    /**
     * Общая проверка, что вся форма авторизации отображается корректно
     */
    public AdminPageAssert allElementsLoginFormAreVisible() {
        return pageTitleExists()
                .loginFormIsVisible()
                .loginInputIsVisible()
                .passwordInputIsVisible()
                .submitButtonIsVisible();
    }

    /**
     * Проверка строки ввода наименования товара
     */
    public AdminPageAssert goodsNameInputIsVisible() {
        actual.getGoodsNameInput().should(visible);
        return this;
    }

    /**
     * Проверка строки ввода цены товара
     */
    public AdminPageAssert goodsPriceInputIsVisible() {
        actual.getGoodsPriceInput().should(visible);
        return this;
    }

    /**
     * Проверка кнопки добавления товара
     */
    public AdminPageAssert buttonAddIsVisible() {
        actual.getButtonAdd().should(visible);
        return this;
    }

    /**
     * Общая проверка, что вся форма добавления товара отображается корректно
     */
    public AdminPageAssert allElementsAddGoodsFormAreVisible() {
        return goodsNameInputIsVisible()
                .goodsPriceInputIsVisible()
                .buttonAddIsVisible();
    }

}
