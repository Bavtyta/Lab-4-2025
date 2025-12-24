package functions.basic;

import functions.Function;

/**
 * Базовый класс для тригонометрических функций
 * Определяет общую область определения (-∞, +∞)
 */
public abstract class TrigonometricFunction implements Function {
    
    /**
     * Возвращает значение левой границы области определения тригонометрических функций
     * @return -∞ (Double.NEGATIVE_INFINITY)
     */
    @Override
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }
    
    /**
     * Возвращает значение правой границы области определения тригонометрических функций
     * @return +∞ (Double.POSITIVE_INFINITY)
     */
    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }
    
    /**
     * Абстрактный метод для вычисления значения тригонометрической функции
     * @param x - аргумент функции (в радианах)
     * @return значение функции
     */
    @Override
    public abstract double getFunctionValue(double x);
}