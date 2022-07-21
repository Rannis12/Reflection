package reflection.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class ImplementsInvestigator {

    private Class aClass;

    ImplementsInvestigator() {}

    public void load(Object someObj) {
        aClass = someObj.getClass();
    }

    int getTotalNumberOfMethods(){
        return aClass.getDeclaredMethods().length;
    }

    public int getTotalNumberOfConstructors(){
        return aClass.getConstructors().length;
    }

    int getTotalNumberOfFields(){
        return aClass.getDeclaredFields().length;
    }
    /*Set<String> getAllImplementedInterfaces(){

    }*/
    int getCountOfConstantFields(){         //not good. need to edit it.
        Field[] allFields = aClass.getDeclaredFields();
        int i = 10;
        return 1;
    }

    int getCountOfStaticMethods(){
        int count = 0;
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if(Modifier.isStatic(methods[i].getModifiers())){
                count++;
            }
        }
        return count;
    }

    boolean isExtending(){
        Object obj = aClass.getSuperclass();
        if(obj != null)
            return true;
        return false;
    }

    String getParentClassSimpleName(){
        if(isExtending()){
            return aClass.getSuperclass().getSimpleName();
        }
        return null;
    }

    boolean isParentClassAbstract(){
        if(isExtending()){
            return Modifier.isAbstract(aClass.getSuperclass().getModifiers());
        }
        return false;
    }

    /*Set<String> getNamesOfAllFieldsIncludingInheritanceChain(){

    }*/

    int invokeMethodThatReturnsInt(String methodName, Object... args){
        Object[] arr = args;
        Method func = aClass.getClass().getDeclaredMethod(methodName, args);
        }


       /* Method func;
        func = aClass.getClass().getMethod(methodName, new Object[] {args});*/
    }








    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        ImplementsInvestigator investigator = new ImplementsInvestigator();
        investigator.load(rectangle);
        //investigator.getCountOfStaticMethods();
        //System.out.println("The answer is: " + investigator.isExtending());
        //System.out.println("The name is: " + investigator.getParentClassSimpleName());
        //System.out.println(investigator.isParentClassAbstract());
    }
}
