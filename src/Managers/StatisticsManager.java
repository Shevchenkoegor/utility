package Managers;

import java.math.BigInteger;
import java.util.List;

public class StatisticsManager {
    private static StatisticsManager instance;

    public StatisticsManager() {
    }

    public static StatisticsManager getInstance() {
        if (instance == null) {
            instance = new StatisticsManager();
        }
        return instance;
    }

    public String getFullFloatsStatistics(List<Double> numbers) {
        if (numbers.isEmpty()) return "";
        double minNumber = numbers.stream()
                .mapToDouble(Number::doubleValue)
                .min()
                .orElse(Double.NaN);
        double maxNumber = numbers.stream()
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(Double.NaN);
        double sumNumber = numbers.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
        double avgNumber = sumNumber / numbers.size();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Статистика по вещественным числам:\n")
                .append("Количество чисел: ")
                .append(numbers.size())
                .append("\n")
                .append("Минимальное число: ")
                .append(minNumber)
                .append("\n")
                .append("Максимальное число: ")
                .append(maxNumber)
                .append("\n")
                .append("Сумма числе: ")
                .append(sumNumber)
                .append("\n")
                .append("Среднее арифметическое: ")
                .append(avgNumber)
                .append("\n\n");
        return stringBuilder.toString();
    }

    public String getFullIntStatistics(List<BigInteger> numbers) {
        if (numbers.isEmpty()) return "";
        BigInteger minNumber = numbers.stream()
                .min(BigInteger::compareTo)
                .orElse(BigInteger.ZERO);
        BigInteger maxNumber = numbers.stream()
                .max(BigInteger::compareTo)
                .orElse(BigInteger.ZERO);
        BigInteger sumNumber = numbers.stream()
                .reduce(BigInteger.ZERO, BigInteger::add);
        double avgNumber = sumNumber.divide(BigInteger.valueOf(numbers.size())).doubleValue();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Статистика по целым числам:\n")
                .append("Количество чисел: ")
                .append(numbers.size())
                .append("\n")
                .append("Минимальное число: ")
                .append(minNumber)
                .append("\n")
                .append("Максимальное число: ")
                .append(maxNumber)
                .append("\n")
                .append("Сумма чисел: ")
                .append(sumNumber)
                .append("\n")
                .append("Среднее арифметическое: ")
                .append(avgNumber)
                .append("\n\n");
        return stringBuilder.toString();
    }

    public String getShortNumberStatistics(List<? extends Number> numbers) {
        if (numbers.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        if (numbers.get(0) instanceof BigInteger) {
            stringBuilder.append("Статистика по целым числам:")
                    .append("\n");
        } else if (numbers.get(0) instanceof Double) {
            stringBuilder.append("Статистика по вещественным числам:")
                    .append("\n");
        }
        stringBuilder.append("Количество чисел: ")
                .append(numbers.size())
                .append("\n\n");
        return stringBuilder.toString();
    }

    public String getFullStringStatistics(List<String> strings) {
        if (strings.isEmpty()) return "";
        int minLengthString = strings.stream()
                .mapToInt(String::length)
                .min()
                .orElse(0);
        int maxLengthString = strings.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        int countStrings = strings.size();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Статистика по строкам:\n")
                .append("Количество строк: ")
                .append(countStrings)
                .append("\n")
                .append("Длина самой короткой строки: ")
                .append(minLengthString)
                .append("\n")
                .append("Длина самой длинной строки: ")
                .append(maxLengthString)
                .append("\n\n");
        return stringBuilder.toString();
    }

    public String getShortStringStatistics(List<String> strings){
        if (strings.isEmpty()) return "";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Статистика по строкам:\n")
                .append("Количество строк: ")
                .append(strings.size())
                .append("\n\n");
        return stringBuilder.toString();
    }
}
