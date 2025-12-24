package functions.meta;

import functions.Function;

/**
 * Класс, представляющий сдвиг функции: f(x + shiftX) + shiftY
 */
public class Shift implements Function {
    private final Function f;
    private final double shiftX;
    private final double shiftY;
    
    /**
     * Конструктор сдвинутой функции
     * @param f исходная функция
     * @param shiftX сдвиг по оси X (может быть отрицательным)
     * @param shiftY сдвиг по оси Y (может быть отрицательным)
     */
    public Shift(Function f, double shiftX, double shiftY) {
        this.f = f;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }
    
    /**
     * Возвращает левую границу области определения после сдвига
     * @return левая граница сдвинутой области определения
     */
    @Override
    public double getLeftDomainBorder() {
        return f.getLeftDomainBorder() - shiftX;
    }
    
    /**
     * Возвращает правую границу области определения после сдвига
     * @return правая граница сдвинутой области определения
     */
    @Override
    public double getRightDomainBorder() {
        return f.getRightDomainBorder() - shiftX;
    }
    
    /**
     * Вычисляет значение сдвинутой функции в заданной точке
     * @param x аргумент функции
     * @return f(x + shiftX) + shiftY
     */
    @Override
    public double getFunctionValue(double x) {
        // Проверяем, что точка принадлежит области определения
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        
        double innerX = x + shiftX;
        // Проверяем, что innerX принадлежит области определения f
        if (innerX < f.getLeftDomainBorder() || innerX > f.getRightDomainBorder()) {
            return Double.NaN;
        }
        
        double value = f.getFunctionValue(innerX);
        if (Double.isNaN(value)) {
            return Double.NaN;
        }
        
        return value + shiftY;
    }
    
    /**
     * Возвращает сдвиг по X
     * @return сдвиг по X
     */
    public double getShiftX() {
        return shiftX;
    }
    
    /**
     * Возвращает сдвиг по Y
     * @return сдвиг по Y
     */
    public double getShiftY() {
        return shiftY;
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "f(x + shiftX) + shiftY"
     */
    @Override
    public String toString() {
        return f + "(x + " + shiftX + ") + " + shiftY;
    }
}