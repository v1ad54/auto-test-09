package HomeWork.tests;

import HomeWork.pages.AdminPage;
import HomeWork.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class AdminPageTest {

    /**
     * Главная страница
     */
    private MainPage mainPage;

    /**
     * Страница "Администрирование"
     */
    private AdminPage adminPage;


    @BeforeEach
    public void setUp() {
        // Откроем окно на главной странице
        open("http://localhost:8080");
        mainPage = new MainPage();

        // Откроем страницу "Администрирование"
        adminPage = mainPage.clickAdminButton();
    }

    @Test
    public void adminPageTest(){
        // Проверим что форма авторизации отображается корректно
        adminPage.assertThat()
                .allElementsLoginFormAreVisible();

        // Авторизуемся в админке
        adminPage.loginAs("admin", "secret123");

        // Проверим что страница админки отображается корректно
        adminPage.assertThat()
                .allElementsAddGoodsFormAreVisible();

    }


    @AfterEach
    void deleteTestData() {

    }

}
