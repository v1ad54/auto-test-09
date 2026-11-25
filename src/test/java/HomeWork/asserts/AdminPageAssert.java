package HomeWork.asserts;

import HomeWork.pages.AdminPage;
import org.assertj.core.api.AbstractAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

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

    /**
     * Проверка сообщения "Товар успешно добавлен!"
     */
    public AdminPageAssert toastIsVisible() {
        actual.getToast().should(visible);
        return this;
    }

    /**
     * Ожидает, пока всплывающее уведомление (тост) исчезнет с экрана
     *
     * @param timeout - максимальное время ожидания закрытия сообщения, в милисекундах
     */
    public AdminPageAssert toastDisappears(long timeout) {
        actual.getToast().should(disappear, Duration.ofMillis(timeout));
        return this;
    }

    /**
     * Проверяет строку редактирования наименования товара
     *
     * @param id - Id товара
     */
    public AdminPageAssert editGoodNameInputIsVisible(int id) {
        actual.getEditGoodNameInput(id).should(visible);
        return this;
    }

    /**
     * Проверяет строку редактирования цены товара
     *
     * @param id - Id товара
     */
    public AdminPageAssert editGoodPriceInputIsVisible(int id) {
        actual.getEditGoodNameInput(id).should(visible);
        return this;
    }

    /**
     * Проверяет кнопку "Сохранить" на товаре
     *
     * @param id - Id товара
     */
    public AdminPageAssert saveButtonIsVisible(int id) {
        actual.getEditGoodNameInput(id).should(visible);
        return this;
    }

    /**
     * Общая проверка, что все элементы редактирования товара видны
     *
     * @param id - Id товара
     */
    public AdminPageAssert allElementSaveGoodsIsVisible(int id) {
        return editGoodNameInputIsVisible(id)
                .editGoodPriceInputIsVisible(id)
                .saveButtonIsVisible(id);
    }

}
