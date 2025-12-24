package functions;

import functions.meta.*;

/**
 * Вспомогательный класс со статическими методами для работы с функциями.
 * Не может быть инстанциирован.
 */
public class Functions {
    
    /**
     * Приватный конструктор для предотвращения создания экземпляров класса
     */
    private Functions() {
        throw new AssertionError("Нельзя создавать экземпляры класса Functions");
    }
    
    /**
     * Возвращает функцию, полученную из исходной сдвигом вдоль осей
     * @param f исходная функция
     * @param shiftX сдвиг по оси X (может быть отрицательным)
     * @param shiftY сдвиг по оси Y (может быть отрицательным)
     * @return функция f(x + shiftX) + shiftY
     */
    public static Function shift(Function f, double shiftX, double shiftY) {
        return new Shift(f, shiftX, shiftY);
    }
    
    /**
     * Возвращает функцию, полученную из исходной масштабированием вдоль осей
     * @param f исходная функция
     * @param scaleX коэффициент масштабирования по оси X (может быть отрицательным)
     * @param scaleY коэффициент масштабирования по оси Y (может быть отрицательным)
     * @return функция scaleY * f(scaleX * x)
     */
    public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f, scaleX, scaleY);
    }
    
    /**
     * Возвращает функцию, являющуюся заданной степенью исходной
     * @param f исходная функция
     * @param power степень
     * @return функция [f(x)]^power
     */
    public static Function power(Function f, double power) {
        return new Power(f, power);
    }
    
    /**
     * Возвращает функцию, являющуюся суммой двух исходных
     * @param f1 первая функция
     * @param f2 вторая функция
     * @return функция f1(x) + f2(x)
     */
    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }
    
    /**
     * Возвращает функцию, являющуюся произведением двух исходных
     * @param f1 первая функция
     * @param f2 вторая функция
     * @return функция f1(x) * f2(x)
     */
    public static Function mult(Function f1, Function f2) {
        return new Mult(f1, f2);
    }
    
    /**
     * Возвращает функцию, являющуюся композицией двух исходных
     * @param f1 внутренняя функция
     * @param f2 внешняя функция
     * @return функция f2(f1(x))
     */
    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }
    
    /**
     * Дополнительный метод: создает квадрат функции
     * @param f исходная функция
     * @return функция [f(x)]^2
     */
    public static Function square(Function f) {
        return new Power(f, 2);
    }
    
    /**
     * Дополнительный метод: создает обратную функцию (1/f(x))
     * @param f исходная функция
     * @return функция 1/f(x)
     */
    public static Function inverse(Function f) {
        return new Power(f, -1);
    }
    
    /**
     * Дополнительный метод: создает разность двух функций
     * @param f1 первая функция
     * @param f2 вторая функция
     * @return функция f1(x) - f2(x)
     */
    public static Function difference(Function f1, Function f2) {
        return sum(f1, scale(f2, 1, -1));
    }
    
    /**
     * Дополнительный метод: создает частное двух функций
     * @param f1 числитель
     * @param f2 знаменатель
     * @return функция f1(x) / f2(x)
     */
    public static Function quotient(Function f1, Function f2) {
        return mult(f1, inverse(f2));
    }
}