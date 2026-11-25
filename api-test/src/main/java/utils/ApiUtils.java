package utils;

import dto.GoodsDto;
import endpoint.GoodsApi;

import java.util.Random;

public class ApiUtils {

    public static int createGood(String name, float price) {
        return GoodsApi.addGoods(new GoodsDto(name, price)).jsonPath().getInt("data.id");
    }

    public static void deleteGood(int id) {
        GoodsApi.deleteGoods(id);
    }

}
