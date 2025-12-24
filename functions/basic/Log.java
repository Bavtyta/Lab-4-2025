package functions.basic;

import functions.Function;

/**
 * Класс, представляющий логарифмическую функцию log_base(x)
 */
public class Log implements Function {
    private final double base;
    /**
     * Конструктор логарифма с заданным основанием
     * @param base основание логарифма (должно быть положительным и не равным 1)
     * @throws IllegalArgumentException если основание некорректно
     */
    public Log(double base) {
        if (base <= 0 || Math.abs(base - 1.0) < 1e-10) {
            throw new IllegalArgumentException("Основание логарифма должно быть положительным и не равным 1");
        }
        this.base = base;
    }
    /**
     * Возвращает значение левой границы области определения логарифма
     * @return 0 (x > 0)
     */
    @Override
    public double getLeftDomainBorder() {
        return 0.0; // Логарифм определен для x > 0
    }
    /**
     * Возвращает значение правой границы области определения логарифма
     * @return +∞ (Double.POSITIVE_INFINITY)
     */
    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }
    /**
     * Вычисляет значение логарифма в заданной точке
     * @param x - аргумент функции (должен быть положительным)
     * @return log_base(x), или Double.NaN если x <= 0
     */
    @Override
    public double getFunctionValue(double x) {
        if (x <= 0) {
            return Double.NaN; // Логарифм не определен для неположительных аргументов
        }
        return Math.log(x) / Math.log(base);
    }
    /**
     * Возвращает основание логарифма
     * @return основание логарифма
     */
    public double getBase() {
        return base;
    }
    /**
     * Возвращает строковое представление функции
     * @return "log_base(x)"
     */
    @Override
    public String toString() {
        return "log_" + base + "(x)";
    }
}