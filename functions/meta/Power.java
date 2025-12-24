package functions.meta;

import functions.Function;

/**
 * Класс, представляющий функцию в степени: [f(x)]^power
 */
public class Power implements Function {
    private final Function f;
    private final double power;
    
    /**
     * Конструктор функции в степени
     * @param f базовая функция
     * @param power степень
     */
    public Power(Function f, double power) {
        this.f = f;
        this.power = power;
    }
    
    /**
     * Возвращает левую границу области определения
     * @return левая граница области определения базовой функции
     */
    @Override
    public double getLeftDomainBorder() {
        return f.getLeftDomainBorder();
    }
    
    /**
     * Возвращает правую границу области определения
     * @return правая граница области определения базовой функции
     */
    @Override
    public double getRightDomainBorder() {
        return f.getRightDomainBorder();
    }
    
    /**
     * Вычисляет значение функции в заданной точке
     * @param x аргумент функции
     * @return [f(x)]^power, или Double.NaN если точка вне области определения
     */
    @Override
    public double getFunctionValue(double x) {
        // Проверяем, что точка принадлежит области определения базовой функции
        if (x < f.getLeftDomainBorder() || x > f.getRightDomainBorder()) {
            return Double.NaN;
        }
        double value = f.getFunctionValue(x);
        
        // Проверяем особые случаи для возведения в степень
        if (Double.isNaN(value)) {
            return Double.NaN;
        }
        
        return Math.pow(value, power);
    }
    
    /**
     * Возвращает степень
     * @return степень
     */
    public double getPower() {
        return power;
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "(f)^power"
     */
    @Override
    public String toString() {
        return "(" + f + ")^" + power;
    }
}