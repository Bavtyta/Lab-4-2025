package functions.meta;

import functions.Function;

/**
 * Класс, представляющий масштабирование функции: a * f(b * x)
 */
public class Scale implements Function {
    private final Function f;
    private final double scaleX;
    private final double scaleY;
    
    /**
     * Конструктор масштабированной функции
     * @param f исходная функция
     * @param scaleX коэффициент масштабирования по оси X (может быть отрицательным)
     * @param scaleY коэффициент масштабирования по оси Y (может быть отрицательным)
     */
    public Scale(Function f, double scaleX, double scaleY) {
        this.f = f;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    /**
     * Возвращает левую границу области определения после масштабирования
     * @return левая граница масштабированной области определения
     */
    @Override
    public double getLeftDomainBorder() {
        if (scaleX > 0) {
            return f.getLeftDomainBorder() / scaleX;
        } else if (scaleX < 0) {
            return f.getRightDomainBorder() / scaleX;
        } else {
            // Если scaleX = 0, функция определена только в нуле (если он входит в область определения f)
            // Но математически это сложный случай, вернем специальное значение
            return Double.NEGATIVE_INFINITY;
        }
    }
    
    /**
     * Возвращает правую границу области определения после масштабирования
     * @return правая граница масштабированной области определения
     */
    @Override
    public double getRightDomainBorder() {
        if (scaleX > 0) {
            return f.getRightDomainBorder() / scaleX;
        } else if (scaleX < 0) {
            return f.getLeftDomainBorder() / scaleX;
        } else {
            // Если scaleX = 0
            return Double.POSITIVE_INFINITY;
        }
    }
    
    /**
     * Вычисляет значение масштабированной функции в заданной точке
     * @param x аргумент функции
     * @return scaleY * f(scaleX * x)
     */
    @Override
    public double getFunctionValue(double x) {
        // Проверяем, что точка принадлежит области определения
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        
        // Если scaleX = 0, то f(0) должно быть определено
        if (scaleX == 0) {
            double value = f.getFunctionValue(0);
            if (Double.isNaN(value)) {
                return Double.NaN;
            }
            return scaleY * value;
        }
        
        double innerX = scaleX * x;
        // Проверяем, что innerX принадлежит области определения f
        if (innerX < f.getLeftDomainBorder() || innerX > f.getRightDomainBorder()) {
            return Double.NaN;
        }
        
        double value = f.getFunctionValue(innerX);
        if (Double.isNaN(value)) {
            return Double.NaN;
        }
        
        return scaleY * value;
    }
    
    /**
     * Возвращает коэффициент масштабирования по X
     * @return коэффициент масштабирования по X
     */
    public double getScaleX() {
        return scaleX;
    }
    
    /**
     * Возвращает коэффициент масштабирования по Y
     * @return коэффициент масштабирования по Y
     */
    public double getScaleY() {
        return scaleY;
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "scaleY * f(scaleX * x)"
     */
    @Override
    public String toString() {
        return scaleY + " * " + f + "(" + scaleX + " * x)";
    }
}