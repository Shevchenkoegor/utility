package Util;

public class TypeChecker {
    private static TypeChecker instance;

    public TypeChecker() {
    }

    public static TypeChecker getInstance(){
        if (instance==null){
            instance=new TypeChecker();
        }
        return instance;
    }

    public String determineType(String input){
        if (isInteger(input)){
            return "Integer";
        }else if(isFloat(input)){
            return "Float";
        }
        return "String";
    }

    private boolean isInteger(String input){
        try {
            Long.parseLong(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isFloat(String input){
        try {
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
