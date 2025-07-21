package Managers;



import Util.Console_Impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManager {
    private Console_Impl console;

    public FileManager(Console_Impl console) {
        this.console = console;
    }

    public List<String> readFiles(List<String> files) {
        List<BufferedReader> readers = new ArrayList<>();
        List<String> data = new ArrayList<>();
        int errors = 0;
        try {
            for (String fileName : files) {
                try {
                    if (new File(fileName).exists()) {
                        BufferedReader reader = new BufferedReader(new FileReader(fileName));
                        readers.add(reader);
                    } else {
                        console.printError("Файл " + fileName + " не найден!");
                        errors += 1;
                    }
                } catch (IOException e) {
                    errors += 1;
                    console.printError("Недостаточно прав для чтения файла " + fileName + ".");
                }
            }
            if (errors == files.size()) {
                console.printError("Нет доступных файлов для чтения, дальнейшее выполнение невозможно!");
                System.exit(1);
            }
            boolean hasMoreLines;
            do {
                hasMoreLines = false;
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        data.add(line);
                        hasMoreLines = true;
                    }
                }
            } while (hasMoreLines);{
                if (errors < files.size() & data.size() == 0) {
                    console.printError("Доступные для чтения файлы пусты, дальнейшее выполнение невозможно!");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            console.printError("Возникла ошибка чтения!");
        } finally {
            for (BufferedReader reader : readers) {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    private String collectionToString(List<?> collection) {
        Iterator<?> iterator = collection.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void write(List<?> data, String filepath, String prefix, String filename, boolean append){
        if (data.size()>0){
            FileWriter writer=null;
            String file=!filepath.isEmpty() ? filepath + "/":"";
            file+=prefix+filename;
            String info=collectionToString(data);
            try {
                writer=new FileWriter(file,append);
                try {
                    writer.write(info);
                    writer.flush();
                }catch (IOException e){
                    console.printError("Ошибка сохранения в файл "+filename+".");
                }
            }catch (IOException e){
                console.printError("Не удалось записать в "+file+".");
            }finally {
                if (writer!=null){
                    try {
                        writer.close();
                    }catch (IOException e){
                        console.printError("Ошибка закрытия файла.");
                    }
                }
            }
        }
    }
}
