package functions.basic;

import functions.Function;
/**
 * Класс, представляющий экспоненциальную функцию e^x
 */
public class Exp implements Function {
    
    /**
     * Возвращает значение левой границы области определения экспоненты
     * @return -∞ (Double.NEGATIVE_INFINITY)
     */
    @Override
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }
    /**
     * Возвращает значение правой границы области определения экспоненты
     * @return +∞ (Double.POSITIVE_INFINITY)
     */
    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }
    /**
     * Вычисляет значение экспоненты в заданной точке
     * @param x - аргумент функции
     * @return e^x
     */
    @Override
    public double getFunctionValue(double x) {
        return Math.exp(x);
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "exp(x)"
     */
    @Override
    public String toString() {
        return "exp(x)";
    }
}