package Util;



import Exceptions.IllegalKeyException;
import Exceptions.MissingFilesException;
import Managers.FileManager;
import Managers.StatisticsManager;

import java.math.BigInteger;
import java.util.*;

public class Filter {
    private FileManager fileManager;
    private Console_Impl console;
    private List<String> parameters;
    private List<String> keys = new ArrayList<>();
    private String prefix = "";
    private String filepath = "";
    private List<String> files = new ArrayList<>();
    private boolean append = false;
    private List<String> strings = new ArrayList<>();
    private List<BigInteger> integers = new ArrayList<>();
    private List<Double> floats = new ArrayList<>();
    private TypeChecker typeChecker = TypeChecker.getInstance();
    private StatisticsManager statisticsManager = StatisticsManager.getInstance();

    public Filter(FileManager fileManager, Console_Impl console, String[] parameters) {
        this.fileManager = fileManager;
        this.console = console;
        this.parameters = new ArrayList<>(Arrays.asList(parameters));
    }

    private void sortArgs() throws IllegalKeyException, MissingFilesException {
        List<String> availableKeys = Arrays.asList("-p", "-s", "-f", "-o", "-a");
        Iterator<String> iterator = parameters.iterator();
        while (iterator.hasNext()) {
            String param = iterator.next();
            if (param.charAt(0) == '-') {
                if (availableKeys.contains(param)) {
                    if (param.equals("-p")) {
                        prefix = iterator.next();
                    } else if (param.equals("-o")) {
                        filepath = iterator.next();
                    } else keys.add(param);
                } else throw new IllegalKeyException("Введен неправильынй аргумент");
            } else files.add(param);
        }
        if (files.size() == 0) {
            throw new MissingFilesException("Не введены названия файлов");
        }
    }

    private void getArguments() {
        console.print("Получено: ");
        for (var e : parameters) {
            console.print(e);
            console.print(" ");
        }
        console.println("");
        console.print("Ключи: ");
        for (var e : keys) {
            console.print(e);
            console.print(" ");
        }
        console.println("");
        console.print("Путь к файлам: ");
        console.println(filepath);
        console.print("Префикс: ");
        console.println(prefix);
        console.print("Файлы: ");
        for (var e : files) {
            console.print(e);
            console.print(" ");
        }
        console.println("");
    }

    private void sortData(List<String> data) {
        for (var e : data) {
            switch (typeChecker.determineType(e)) {
                case "Integer":
                    integers.add(new BigInteger(e));
                    break;
                case "Float":
                    floats.add(Double.parseDouble(e));
                    break;
                case "String":
                    strings.add(e);
            }
        }
    }

    public void solve() {
        try {
            sortArgs();
            sortData(fileManager.readFiles(files));
            if (keys.contains("-f")) {
                console.print(statisticsManager.getFullIntStatistics(integers));
                console.print(statisticsManager.getFullFloatsStatistics(floats));
                console.print(statisticsManager.getFullStringStatistics(strings));
            } else if (keys.contains("-s")) {
                console.print(statisticsManager.getShortNumberStatistics(integers));
                console.print(statisticsManager.getShortNumberStatistics(floats));
                console.print(statisticsManager.getShortStringStatistics(strings));
            }
            if (keys.contains("-a")) append = true;
            fileManager.write(integers, filepath, prefix, "integers.txt", append);
            fileManager.write(floats, filepath, prefix, "floats.txt", append);
            fileManager.write(strings, filepath, prefix, "strings.txt", append);
            append = false;
        } catch (IllegalKeyException | MissingFilesException e) {
            console.printError(e.getMessage());
        } catch (NoSuchElementException e) {
            console.printError("Введено недостаточное количетсво аргументов.");
        }
    }
}
