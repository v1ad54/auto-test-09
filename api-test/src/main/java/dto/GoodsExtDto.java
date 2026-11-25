package dto;

/**
 * Структура с тестовым товаром и его id
 */
public record GoodsExtDto(
        /** Структура товара */
        GoodsDto good,
        /** Id товара */
        int id
) {
}
