package Util;

import java.util.Scanner;

public class Console_Impl implements Console{
    private Scanner scanner=new Scanner(System.in);

    @Override
    public void print(Object o) {
        System.out.print(o);
    }

    @Override
    public void println(Object o) {
        System.out.println(o);
    }

    @Override
    public void printError(Object o) {
        System.err.println("Ошибка:"+o);
    }

    @Override
    public boolean isCanRead() {
        return scanner.hasNextLine();
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
