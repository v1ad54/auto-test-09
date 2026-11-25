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
 * Тест: 2.4. Войти в админку и отредактировать товар. Выйти на список товаров и проверить, что изменения применились.
 */
public class EditGoodTest {

    /**
     * Id тестового товара
     */
    static Integer id;

    /**
     * Наименование тестового товара
     */
    static String goodName = "Новый товар " + new Random().nextInt(1000, 10000);

    /**
     * Цена полученного товара
     */
    static int goodPrice = new Random().nextInt(10, 25);

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
        // Создаем тестовый товар
        id = ApiUtils.createGood(goodName, goodPrice);

        // Откроем окно на главной странице
        open("http://localhost:8080");
        mainPage = new MainPage();

        // Откроем страницу "Администрирование"
        adminPage = mainPage.clickAdminButton();
    }

    @Test
    void editGoodTest() {
        // Сгенерируем новые название и цену товара
        goodName = "Измененный товар " + new Random().nextInt(1000, 10000);
        goodPrice = new Random().nextInt(10, 25);

        adminPage.assertThat().allElementsLoginFormAreVisible() // Проверим, что форма авторизации отображается корректно
                .actual().loginAs("admin", "secret123") // Авторизуемся в админке
                .assertThat().allElementSaveGoodsIsVisible(id)   // Проверим, что все элементы редактирования товара видны
                .actual().editGoods(id, goodName, goodPrice);   // Изменим наименование и цену товара

        // Перейдем обратно на главную страницу
        open("http://localhost:8080");

        // Проверим что на витрине есть товар с новым имененем и ценой
        mainPage.assertThat()
                .goodCardIsVisible(goodName, goodPrice);
    }

    @AfterEach
    void deleteTestData() {
        // Удаление товара через Utils класс
        if (id != null) {
            ApiUtils.deleteGood(id);
        }
    }

}
