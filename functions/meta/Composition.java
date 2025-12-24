package functions.meta;

import functions.Function;

/**
 * Класс, представляющий композицию двух функций: g(f(x))
 */
public class Composition implements Function {
    private final Function f;
    private final Function g;
    
    /**
     * Конструктор композиции функций
     * @param f внутренняя функция
     * @param g внешняя функция
     */
    public Composition(Function f, Function g) {
        this.f = f;
        this.g = g;
    }
    
    /**
     * Возвращает левую границу области определения композиции
     * @return левая граница области определения внутренней функции
     */
    @Override
    public double getLeftDomainBorder() {
        return f.getLeftDomainBorder();
    }
    
    /**
     * Возвращает правую границу области определения композиции
     * @return правая граница области определения внутренней функции
     */
    @Override
    public double getRightDomainBorder() {
        return f.getRightDomainBorder();
    }
    
    /**
     * Вычисляет значение композиции функций в заданной точке
     * @param x аргумент функции
     * @return g(f(x))
     */
    @Override
    public double getFunctionValue(double x) {
        // Проверяем, что точка принадлежит области определения внутренней функции
        if (x < f.getLeftDomainBorder() || x > f.getRightDomainBorder()) {
            return Double.NaN;
        }
        
        double innerValue = f.getFunctionValue(x);
        if (Double.isNaN(innerValue)) {
            return Double.NaN;
        }
        
        // Проверяем, что значение внутренней функции принадлежит области определения внешней функции
        if (innerValue < g.getLeftDomainBorder() || innerValue > g.getRightDomainBorder()) {
            return Double.NaN;
        }
        
        return g.getFunctionValue(innerValue);
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "g(f(x))"
     */
    @Override
    public String toString() {
        return g + "(" + f + "(x))";
    }
}