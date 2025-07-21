package Util;

public interface Console {
        void print(Object o);
        void println(Object o);
        void printError(Object o);
        boolean isCanRead();
        String read();

}
