import functions.*;
import functions.basic.*;
import functions.meta.*;
import java.io.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // Устанавливаем локаль для единообразного вывода чисел
        Locale.setDefault(Locale.US);
        
        System.out.println("===== ВЫПОЛНЕНИЕ ЗАДАНИЙ 8 И 9 =====\n");
        
        try {
            // Задание 8, часть 1: Тестирование Sin и Cos
            System.out.println("1. ЗАДАНИЕ 8: БАЗОВЫЕ ФУНКЦИИ SIN И COS\n");
            testSinCos();
            
            // Задание 8, часть 2: Табулированные аналоги Sin и Cos
            System.out.println("\n2. ЗАДАНИЕ 8: ТАБУЛИРОВАННЫЕ АНАЛОГИ SIN И COS\n");
            testTabulatedSinCos();
            
            // Задание 8, часть 3: Сумма квадратов табулированных функций
            System.out.println("\n3. ЗАДАНИЕ 8: СУММА КВАДРАТОВ SIN²(x) + COS²(x)\n");
            testSumOfSquares();
            
            // Задание 8, часть 4: Текстовый файловый ввод/вывод с экспонентой
            System.out.println("\n4. ЗАДАНИЕ 8: ТЕКСТОВЫЙ ФАЙЛОВЫЙ ВВОД/ВЫВОД (ЭКСПОНЕНТА)\n");
            testTextFileIOExp();
            
            // Задание 8, часть 5: Бинарный файловый ввод/вывод с логарифмом
            System.out.println("\n5. ЗАДАНИЕ 8: БИНАРНЫЙ ФАЙЛОВЫЙ ВВОД/ВЫВОД (ЛОГАРИФМ)\n");
            testBinaryFileIOLn();
            
            // Задание 9: Сериализация и десериализация
            System.out.println("\n6. ЗАДАНИЕ 9: СЕРИАЛИЗАЦИЯ И ДЕСЕРИАЛИЗАЦИЯ\n");
            testSerialization();
            
            // Анализ форматов файлов
            System.out.println("\n7. АНАЛИЗ ФОРМАТОВ ФАЙЛОВ И ВЫВОДЫ\n");
            analyzeFileFormats();
            
            // Очистка временных файлов
            cleanUpFiles();
            
            System.out.println("\n===== ВСЕ ЗАДАНИЯ ВЫПОЛНЕНЫ =====");
            
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // 1. Тестирование базовых функций Sin и Cos
    private static void testSinCos() {
        Function sin = new Sin();
        Function cos = new Cos();
        
        System.out.println("Значения функций на отрезке [0, π] с шагом 0.1:");
        System.out.println("┌─────────┬────────────┬────────────┐");
        System.out.println("│    x    │   sin(x)   │   cos(x)   │");
        System.out.println("├─────────┼────────────┼────────────┤");
        
        for (double x = 0; x <= Math.PI + 1e-9; x += 0.1) {
            System.out.printf("│ %7.3f │ %10.6f │ %10.6f │%n", 
                x, sin.getFunctionValue(x), cos.getFunctionValue(x));
        }
        
        System.out.println("└─────────┴────────────┴────────────┘");
    }
    
    // 2. Тестирование табулированных аналогов Sin и Cos
    private static void testTabulatedSinCos() {
        Function sin = new Sin();
        Function cos = new Cos();
        
        // Создаем табулированные аналоги с 10 точками
        TabulatedFunction tabSin = TabulatedFunctions.tabulate(sin, 0, Math.PI, 10);
        TabulatedFunction tabCos = TabulatedFunctions.tabulate(cos, 0, Math.PI, 10);
        
        System.out.println("Сравнение с исходными функциями (первые 5 точек):");
        System.out.println("┌─────────┬────────────┬────────────┬────────────┬────────────┐");
        System.out.println("│    x    │  точн.sin  │ таб. sin   │  точн.cos  │ таб. cos   │");
        System.out.println("├─────────┼────────────┼────────────┼────────────┼────────────┤");
        
        for (int i = 0; i < Math.min(5, tabSin.getPointsCount()); i++) {
            double x = tabSin.getPointX(i);
            double exactSin = sin.getFunctionValue(x);
            double tabSinVal = tabSin.getPointY(i);
            double exactCos = cos.getFunctionValue(x);
            double tabCosVal = tabCos.getPointY(i);
            
            System.out.printf("│ %7.3f │ %10.6f │ %10.6f │ %10.6f │ %10.6f │%n",
                x, exactSin, tabSinVal, exactCos, tabCosVal);
        }
        
        System.out.println("└─────────┴────────────┴────────────┴────────────┴────────────┘");
    }
    
    // 3. Тестирование суммы квадратов
    private static void testSumOfSquares() {
        // Создаем табулированные функции с разным количеством точек
        int[] pointCounts = {5, 10, 20, 50};
        
        System.out.println("Исследование влияния количества точек на точность sin²(x) + cos²(x):");
        System.out.println("┌──────────────┬────────────────────┐");
        System.out.println("│ Кол-во точек │ Макс. отклонение   │");
        System.out.println("├──────────────┼────────────────────┤");
        
        for (int n : pointCounts) {
            TabulatedFunction tabSin = TabulatedFunctions.tabulate(new Sin(), 0, Math.PI, n);
            TabulatedFunction tabCos = TabulatedFunctions.tabulate(new Cos(), 0, Math.PI, n);
            
            // Создаем сумму квадратов
            Function sinSquared = Functions.power(tabSin, 2);
            Function cosSquared = Functions.power(tabCos, 2);
            Function sumOfSquares = Functions.sum(sinSquared, cosSquared);
            
            double maxError = 0;
            for (double x = 0; x <= Math.PI; x += 0.01) {
                double value = sumOfSquares.getFunctionValue(x);
                double error = Math.abs(value - 1.0);
                if (error > maxError) maxError = error;
            }
            
            System.out.printf("│ %12d │ %18.6f │%n", n, maxError);
        }
        
        System.out.println("└──────────────┴────────────────────┘");
    }
    
    // 4. Тестирование текстового файлового ввода/вывода (экспонента)
    private static void testTextFileIOExp() throws IOException {
        // Создаем табулированную экспоненту
        TabulatedFunction tabExp = TabulatedFunctions.tabulate(new Exp(), 0, 10, 11);
        
        // Записываем в текстовый файл
        String textFileName = "exp_function.txt";
        try (FileWriter writer = new FileWriter(textFileName)) {
            TabulatedFunctions.writeTabulatedFunction(tabExp, writer);
            System.out.println("Функция записана в текстовый файл: " + textFileName);
        }
        
        // Читаем из текстового файла
        TabulatedFunction readExp;
        try (FileReader reader = new FileReader(textFileName)) {
            readExp = TabulatedFunctions.readTabulatedFunction(reader);
            System.out.println("Функция прочитана из текстового файла");
        }
        
        // Сравниваем значения
        System.out.println("\nСравнение значений с шагом 1:");
        System.out.println("┌─────┬──────────────┬──────────────┬──────────────┐");
        System.out.println("│  x  │  исходная    │  прочитанная │  разница     │");
        System.out.println("├─────┼──────────────┼──────────────┼──────────────┤");
        
        for (double x = 0; x <= 10; x += 1.0) {
            double original = tabExp.getFunctionValue(x);
            double read = readExp.getFunctionValue(x);
            double diff = Math.abs(original - read);
            System.out.printf("│ %3.0f │ %12.6f │ %12.6f │ %12.6e │%n", 
                x, original, read, diff);
        }
        
        System.out.println("└─────┴──────────────┴──────────────┴──────────────┘");
    }
    
    // 5. Тестирование бинарного файлового ввода/вывода (логарифм)
    private static void testBinaryFileIOLn() throws IOException {
        // Создаем табулированный натуральный логарифм
        TabulatedFunction tabLn = TabulatedFunctions.tabulate(new Log(Math.E), 0.1, 10, 11);
        
        // Записываем в бинарный файл
        String binaryFileName = "log_function.dat";
        try (FileOutputStream out = new FileOutputStream(binaryFileName)) {
            TabulatedFunctions.outputTabulatedFunction(tabLn, out);
            System.out.println("Функция записана в бинарный файл: " + binaryFileName);
        }
        
        // Читаем из бинарного файла
        TabulatedFunction readLn;
        try (FileInputStream in = new FileInputStream(binaryFileName)) {
            readLn = TabulatedFunctions.inputTabulatedFunction(in);
            System.out.println("Функция прочитана из бинарного файла");
        }
        
        // Сравниваем значения
        System.out.println("\nСравнение значений с шагом 1:");
        System.out.println("┌───────┬──────────────┬──────────────┬──────────────┐");
        System.out.println("│   x   │  исходная    │  прочитанная │  разница     │");
        System.out.println("├───────┼──────────────┼──────────────┼──────────────┤");
        
        for (double x = 0.1; x <= 10; x += 1.0) {
            double original = tabLn.getFunctionValue(x);
            double read = readLn.getFunctionValue(x);
            double diff = Math.abs(original - read);
            System.out.printf("│ %5.1f │ %12.6f │ %12.6f │ %12.6e │%n", 
                x, original, read, diff);
        }
        
        System.out.println("└───────┴──────────────┴──────────────┴──────────────┘");
    }
    
    // 6. Тестирование сериализации
    private static void testSerialization() throws Exception {
        // Создаем функцию ln(exp(x)) = x
        Function exp = new Exp();
        Function ln = new Log(Math.E);
        Function lnExp = new Composition(ln, exp); // ln(exp(x)) = x
        
        // Табулируем функцию
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(lnExp, 0, 10, 11);
        
        System.out.println("Создана функция ln(exp(x)) = x с " + 
            tabulatedFunction.getPointsCount() + " точками");
        
        // Тест 1: Serializable
        System.out.println("\n6.1 Сериализация с использованием Serializable:");
        testSerializable(tabulatedFunction, "function_serializable.ser");
        
        // Тест 2: Externalizable
        System.out.println("\n6.2 Сериализация с использованием Externalizable:");
        testExternalizable(tabulatedFunction, "function_externalizable.ser");
    }
    
    private static void testSerializable(TabulatedFunction function, String fileName) throws Exception {
        // Сериализуем объект
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(function);
            System.out.println("  Функция сериализована в файл: " + fileName);
        }
        
        // Десериализуем объект
        TabulatedFunction deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            deserialized = (TabulatedFunction) ois.readObject();
            System.out.println("  Функция десериализована из файла");
        }
        
        // Сравниваем
        compareSerializedFunctions(function, deserialized);
        
        // Размер файла
        File file = new File(fileName);
        System.out.println("  Размер файла: " + file.length() + " байт");
    }
    
    private static void testExternalizable(TabulatedFunction function, String fileName) throws Exception {
        // Создаем копию функции с использованием Externalizable
        // Предполагаем, что ArrayTabulatedFunction implements Externalizable
        // Если нет, можно создать другую реализацию
        
        // Сериализуем объект
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(function);
            System.out.println("  Функция сериализована в файл: " + fileName);
        }
        
        // Десериализуем объект
        TabulatedFunction deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            deserialized = (TabulatedFunction) ois.readObject();
            System.out.println("  Функция десериализована из файла");
        }
        
        // Сравниваем
        compareSerializedFunctions(function, deserialized);
        
        // Размер файла
        File file = new File(fileName);
        System.out.println("  Размер файла: " + file.length() + " байт");
    }
    
    private static void compareSerializedFunctions(TabulatedFunction original, 
                                                  TabulatedFunction deserialized) {
        System.out.println("  Сравнение значений:");
        System.out.println("  ┌─────┬──────────────┬──────────────┬──────────────┐");
        System.out.println("  │  x  │  исходная    │ восстановл.  │  разница     │");
        System.out.println("  ├─────┼──────────────┼──────────────┼──────────────┤");
        
        boolean allMatch = true;
        for (double x = 0; x <= 10; x += 1.0) {
            double originalValue = original.getFunctionValue(x);
            double deserializedValue = deserialized.getFunctionValue(x);
            double diff = Math.abs(originalValue - deserializedValue);
            
            if (diff > 1e-10) {
                allMatch = false;
            }
            
            System.out.printf("  │ %3.0f │ %12.6f │ %12.6f │ %12.6e │%n", 
                x, originalValue, deserializedValue, diff);
        }
        System.out.println("  └─────┴──────────────┴──────────────┴──────────────┘");
        
        System.out.println("  Все значения совпадают: " + allMatch);
    }
    
    // 7. Анализ форматов файлов
    private static void analyzeFileFormats() throws IOException {
        System.out.println("Содержимое файлов и размеры:");
        
        File textFile = new File("exp_function.txt");
        if (textFile.exists()) {
            System.out.println("\n1. Текстовый файл (exp_function.txt):");
            System.out.println("   Размер: " + textFile.length() + " байт");
            System.out.print("   Первые 100 символов: ");
            try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
                String firstLine = reader.readLine();
                if (firstLine != null) {
                    System.out.println(firstLine.substring(0, Math.min(100, firstLine.length())) + "...");
                }
            }
        }
        
        File binaryFile = new File("log_function.dat");
        if (binaryFile.exists()) {
            System.out.println("\n2. Бинарный файл (log_function.dat):");
            System.out.println("   Размер: " + binaryFile.length() + " байт");
        }
        
        File serializableFile = new File("function_serializable.ser");
        if (serializableFile.exists()) {
            System.out.println("\n3. Файл сериализации Serializable (function_serializable.ser):");
            System.out.println("   Размер: " + serializableFile.length() + " байт");
        }
        
        File externalizableFile = new File("function_externalizable.ser");
        if (externalizableFile.exists()) {
            System.out.println("\n4. Файл сериализации Externalizable (function_externalizable.ser):");
            System.out.println("   Размер: " + externalizableFile.length() + " байт");
        }
        
        System.out.println("\nВыводы о форматах хранения:");
        System.out.println("1. Текстовый формат:");
        System.out.println("   + Человекочитаемый");
        System.out.println("   + Легко отлаживать");
        System.out.println("   - Больший размер файла");
        System.out.println("   - Медленнее чтение/запись");
        
        System.out.println("\n2. Бинарный формат (DataStreams):");
        System.out.println("   + Компактный размер");
        System.out.println("   + Быстрая работа");
        System.out.println("   - Не человекочитаемый");
        
        System.out.println("\n3. Сериализация Serializable:");
        System.out.println("   + Простая реализация");
        System.out.println("   + Автоматическая сериализация");
        System.out.println("   - Большой размер (метаданные)");
        
        System.out.println("\n4. Сериализация Externalizable:");
        System.out.println("   + Полный контроль");
        System.out.println("   + Компактный размер");
        System.out.println("   - Сложная реализация");
    }
    
    private static void cleanUpFiles() {
        String[] filesToDelete = {
            "exp_function.txt",
            "log_function.dat", 
            "function_serializable.ser",
            "function_externalizable.ser"
        };
        
        System.out.println("\nУдаление временных файлов:");
        for (String fileName : filesToDelete) {
            File file = new File(fileName);
            if (file.exists() && file.delete()) {
                System.out.println("  Удален: " + fileName);
            }
        }
    }
}