package functions.basic;

/**
 * Класс, представляющий синусоидальную функцию sin(x)
 */
public class Sin extends TrigonometricFunction {
    
    /**
     * Вычисляет значение синуса в заданной точке
     * @param x - аргумент функции (в радианах)
     * @return sin(x)
     */
    @Override
    public double getFunctionValue(double x) {
        return Math.sin(x);
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "sin(x)"
     */
    @Override
    public String toString() {
        return "sin(x)";
    }
}