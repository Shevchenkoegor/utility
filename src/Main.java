import Managers.FileManager;
import Util.Console_Impl;
import Util.Filter;

public class Main {
    public static void main(String[] args) {
        Console_Impl console=new Console_Impl();
        FileManager fileManager=new FileManager(console);
        Filter filter=new Filter(fileManager,console,args);
        filter.solve();

    }
}