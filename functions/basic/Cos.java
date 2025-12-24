package functions.basic;

/**
 * Класс, представляющий косинусоидальную функцию cos(x)
 */
public class Cos extends TrigonometricFunction {
    
    /**
     * Вычисляет значение косинуса в заданной точке
     * @param x - аргумент функции (в радианах)
     * @return cos(x)
     */
    @Override
    public double getFunctionValue(double x) {
        return Math.cos(x);
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "cos(x)"
     */
    @Override
    public String toString() {
        return "cos(x)";
    }
}
