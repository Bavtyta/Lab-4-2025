package functions;

import java.io.*;

public class TabulatedFunctions {
    
    // Приватный конструктор предотвращает создание экземпляров
    private TabulatedFunctions() {
        throw new AssertionError("Нельзя создавать экземпляры класса TabulatedFunctions");
    }
    
    // Метод табулирования функции на заданном отрезке
    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) {
        // Проверка количества точек
        if (pointsCount < 2) {
            throw new IllegalArgumentException("Количество точек должно быть не менее 2");
        }
        
        // Проверка границ отрезка
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница должна быть меньше правой границы");
        }
        
        // Проверка области определения
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder()) {
            throw new IllegalArgumentException(
                "Границы табулирования [" + leftX + ", " + rightX + 
                "] выходят за область определения функции [" + 
                function.getLeftDomainBorder() + ", " + function.getRightDomainBorder() + "]"
            );
        }
        
        // Создание массива точек
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        
        // Заполнение массива точек
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            double y = function.getFunctionValue(x);
            points[i] = new FunctionPoint(x, y);
        }
        
        // Возвращаем табулированную функцию
        return new ArrayTabulatedFunction(points);
    }
    
    // Метод вывода табулированной функции в байтовый поток
    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(out);
        int pointsCount = function.getPointsCount();
        
        dataOut.writeInt(pointsCount);
        
        for (int i = 0; i < pointsCount; i++) {
            dataOut.writeDouble(function.getPointX(i));
            dataOut.writeDouble(function.getPointY(i));
        }
        
        dataOut.flush();
    }
    
    // Метод ввода табулированной функции из байтового потока
    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        DataInputStream dataIn = new DataInputStream(in);
        
        int pointsCount = dataIn.readInt();
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        
        for (int i = 0; i < pointsCount; i++) {
            double x = dataIn.readDouble();
            double y = dataIn.readDouble();
            points[i] = new FunctionPoint(x, y);
        }
        
        return new ArrayTabulatedFunction(points);
    }
    
    // Метод записи табулированной функции в символьный поток
    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        int pointsCount = function.getPointsCount();
        
        writer.print(pointsCount);
        
        for (int i = 0; i < pointsCount; i++) {
            writer.print(" " + function.getPointX(i));
            writer.print(" " + function.getPointY(i));
        }
        
        writer.flush();
    }
    
    // Метод чтения табулированной функции из символьного потока
    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.eolIsSignificant(false);
        
        tokenizer.nextToken();
        if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
            throw new IOException("Ожидалось количество точек");
        }
        int pointsCount = (int) tokenizer.nval;
        
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        
        for (int i = 0; i < pointsCount; i++) {
            tokenizer.nextToken();
            if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
                throw new IOException("Ожидалась координата X");
            }
            double x = tokenizer.nval;
            
            tokenizer.nextToken();
            if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
                throw new IOException("Ожидалась координата Y");
            }
            double y = tokenizer.nval;
            
            points[i] = new FunctionPoint(x, y);
        }
        
        return new ArrayTabulatedFunction(points);
    }
}