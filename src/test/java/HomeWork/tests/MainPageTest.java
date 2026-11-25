package HomeWork.tests;

import HomeWork.pages.MainPage;
import dto.GoodsDto;
import dto.GoodsExtDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {

    /**
     * Список тестовых товаров
     */
    static List<GoodsExtDto> listGoods = new ArrayList<>();

    /**
     * Главная страница
     */
    private MainPage mainPage;


    /**
     * Метод для генерации тестовых товаров
     */
    private static GoodsExtDto createTestGood() {
        String goodName = "Новый товар " + new Random().nextInt(1000, 10000);
        int goodPrice = new Random().nextInt(10, 25);
        int goodId = ApiUtils.createGood(goodName, goodPrice);

        return new GoodsExtDto(new GoodsDto(goodName, goodPrice), goodId);
    }


    @BeforeEach
    public void setUp() {
        // Создадим и запомним несколько тестовых товаров
        for (int i = 0; i < new Random().nextInt(1, 10); i++) {
            listGoods.add(createTestGood());
        }

        // Откроем окно на главной странице
        open("http://localhost:8080");
        mainPage = new MainPage();
    }


    @Test
    @DisplayName("Проверка отображения элементов и взаимодействия с карточками товаров на главной странице")
    public void mainPageTest() {

        // Проверяем, что кнопки отображаются
        mainPage.assertThat()
                .adminButtonIsVisible()
                .cartButtonIsVisible();

        // Проверяем, что карточки товара отображаются
        for (GoodsExtDto item : listGoods) {
            mainPage.assertThat()
                    .goodCardIsVisible(item.good().name(), item.good().price()) // Проверка карточки товара по наименованию и цене
                    .goodCardIsVisible(item.id()); // Проверка карточки товара по id
        }

        // Добавим каждый тестовый товар в корзину
        for (GoodsExtDto item : listGoods) {
            mainPage.clickGoodsToCart(item.id());
        }

        // Проверим счетчик товаров в корзине
        mainPage.assertThat()
                .cartCounterHasCount(listGoods.size());

    }

    @AfterEach
    void deleteTestData() {
        // Удаление товаров через Utils класс
        for (GoodsExtDto good : listGoods) {
            ApiUtils.deleteGood(good.id());
        }

        // Очищаем список тестовых товаров
        listGoods.clear();
    }

}