package functions.basic;

/**
 * Класс, представляющий тангенс функцию tan(x)
 * Обратите внимание: тангенс имеет разрывы в точках π/2 + πk
 */
public class Tan extends TrigonometricFunction {
    
    /**
     * Вычисляет значение тангенса в заданной точке
     * @param x - аргумент функции (в радианах)
     * @return tan(x), или Double.NaN если cos(x) = 0 (разрыв)
     */
    @Override
    public double getFunctionValue(double x) {
        // Проверяем, не является ли точка точкой разрыва
        double cosValue = Math.cos(x);
        if (Math.abs(cosValue) < 1e-10) {
            return Double.NaN;
        }
        return Math.tan(x);
    }
    
    /**
     * Возвращает строковое представление функции
     * @return "tan(x)"
     */
    @Override
    public String toString() {
        return "tan(x)";
    }
}