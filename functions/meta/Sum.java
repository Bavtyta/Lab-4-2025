package functions.meta;

import functions.Function;

/**
 * Класс, представляющий сумму двух функций: f(x) + g(x)
 */
public class Sum implements Function {
    private final Function f;
    private final Function g;
    
    /**
     * Конструктор суммы двух функций
     * @param f первая функция
     * @param g вторая функция
     */
    public Sum(Function f, Function g) {
        this.f = f;
        this.g = g;
    }
    
    /**
     * Возвращает левую границу области определения суммы функций
     * @return максимальная из левых границ областей определения
     */
    @Override
    public double getLeftDomainBorder() {
        return Math.max(f.getLeftDomainBorder(), g.getLeftDomainBorder());
    }
    
    /**
     * Возвращает правую границу области определения суммы функций
     * @return минимальная из правых границ областей определения
     */
    @Override
    public double getRightDomainBorder() {
        return Math.min(f.getRightDomainBorder(), g.getRightDomainBorder());
    }
    
    /**
     * Вычисляет значение суммы функций в заданной точке
     * @param x аргумент функции
     * @return f(x) + g(x), или Double.NaN если точка вне области определения
     */
    @Override
    public double getFunctionValue(double x) {
        // Проверяем, что точка принадлежит пересечению областей определения
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        return f.getFunctionValue(x) + g.getFunctionValue(x);
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "(f + g)"
     */
    @Override
    public String toString() {
        return "(" + f + " + " + g + ")";
    }
}