package HomeWork.tests;

import HomeWork.pages.CartPage;
import HomeWork.pages.MainPage;
import com.codeborne.selenide.Selenide;
import dto.GoodsDto;
import dto.GoodsExtDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Тест: 2.2. Добавить в корзину несколько разных товаров и проверить, что общая цена в корзине считается корректно.
 */
public class CheckTotalPriceTest {

    /**
     * Список тестовых товаров
     */
    List<GoodsExtDto> listGoods = new ArrayList<>();

    /**
     * Главная страница
     */
    private MainPage mainPage;

    /**
     * Страница "Корзина"
     */
    private CartPage cartPage;


    private GoodsExtDto createTestGood() {
        String goodName = "Новый товар " + new Random().nextInt(1000, 10000);
        int goodPrice = new Random().nextInt(10, 25);
        int goodId = ApiUtils.createGood(goodName, goodPrice);

        return new GoodsExtDto(new GoodsDto(goodName, goodPrice), goodId);
    }

    @BeforeEach
    void openBrowser() {
        // Создадим и запомним несколько тестовых товаров
        for (int i = 0; i < new Random().nextInt(1, 10); i++) {
            listGoods.add(createTestGood());
        }

        // Откроем главную страницу
        Selenide.open("http://localhost:8080");
        mainPage = new MainPage();
    }

    @Test
    void checkTotalPriceTest() {
        // Добавим тестовые товары в корзину
        listGoods.forEach(item -> {
            // Проверим, что товар виден на витрине
            mainPage.assertThat()
                    .goodCardIsVisible(item.good().name(), item.good().price()) // По наименованию и цене
                    .goodCardIsVisible(item.id()); // По id

            // Добавим товар в корзину
            mainPage.clickButtonGoodsToCart(item.id())
                    .assertThat()
                    .toastDisappears(10000); // Подождем когда исчезнет сообщение о том что товар добавлен в корзину
        });

        // Перейдем в корзину
        cartPage = mainPage.clickCartButton();

        cartPage.assertThat()
                .isOpened()    // Проверим, что корзина открылась
                .totalPriceMatchesGoodsSum(listGoods);  // Проверяем общую сумму

    }

    @AfterEach
    void deleteTestData() {
        // Удаление товаров через Utils класс
        listGoods.forEach(good -> ApiUtils.deleteGood(good.id()));

        // Очищаем список тестовых товаров
        listGoods.clear();
    }

}
