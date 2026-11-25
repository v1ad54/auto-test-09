package HomeWork.tests;

import HomeWork.pages.AdminPage;
import HomeWork.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiUtils;

import java.util.Random;

import static com.codeborne.selenide.Selenide.open;


/**
 * Тест: 2.3. Войти в админку и добавить товар. Проверить уведомление после добавления товара.
 */
public class AddGoodTest {

    /**
     * Id тестового товара
     */
    Integer id;
    /**
     * Наименование тестового товара
     */
    String goodName = "Новый товар " + new Random().nextInt(1000, 10000);
    /**
     * Цена полученного товара
     */
    int goodPrice = new Random().nextInt(10, 25);

    /**
     * Главная страница
     */
    private MainPage mainPage;

    /**
     * Страница "Администрирование"
     */
    private AdminPage adminPage;


    @BeforeEach
    void openBrowser() {
        // Откроем окно на главной странице
        open("http://localhost:8080");
        mainPage = new MainPage();

        // Откроем страницу "Администрирование"
        adminPage = mainPage.clickAdminButton();
    }

    @Test
    void addGoodTest() {
        // Проверим что форма авторизации отображается корректно
        adminPage.assertThat()
                .allElementsLoginFormAreVisible();

        adminPage.loginAs("admin", "secret123")     // Авторизуемся в админке
                .assertThat().allElementsAddGoodsFormAreVisible()   // Проверим что страница админки отображается корректно
                .actual().addGoods(goodName, goodPrice)  // Добавим товар
                .assertThat().toastIsVisible();     // Проверим уведомление после добавления товара
    }

    @AfterEach
    void deleteTestData() {
        // Вернемся на главную страницу
        open("http://localhost:8080");

        // Получим Id созданного в тесте товара
        id = mainPage.getGoodsCardId(goodName, goodPrice);

        // Удаление товара через Utils класс
        if (id != null) {
            ApiUtils.deleteGood(id);
        }
    }

}
