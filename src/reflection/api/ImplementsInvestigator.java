package reflection.api;

import java.lang.reflect.*;
import java.util.*;

public class ImplementsInvestigator implements Investigator{
    private Class aClass;
    private Object obj;

    ImplementsInvestigator() {}

    @Override
    public void load(Object someObj) {
        obj = someObj;
        aClass = someObj.getClass();
    }

    @Override
    public int getTotalNumberOfMethods(){
        return aClass.getDeclaredMethods().length;
    }

    @Override
    public int getTotalNumberOfConstructors(){
        return aClass.getConstructors().length;
    }

    @Override
    public int getTotalNumberOfFields(){
        return aClass.getDeclaredFields().length;
    }

    @Override
    public Set<String> getAllImplementedInterfaces() {
        return null;
    }

    @Override
    public int getCountOfConstantFields(){
        Field[] allFields = aClass.getDeclaredFields();
        int finalCounter = 0;
        for (Field field: allFields) {
            if(Modifier.isFinal(field.getModifiers()))
            {
                finalCounter++;
            }
        }
        return finalCounter;
    }

    @Override
    public int getCountOfStaticMethods(){
        int StaticCount = 0;
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if(Modifier.isStatic(methods[i].getModifiers())){
                StaticCount++;
            }
        }
        return StaticCount;
    }

    @Override
    public boolean isExtending(){
        Class superClass = aClass.getSuperclass();
        if(superClass != null)
            return true;
        return false;
    }

    @Override
    public String getParentClassSimpleName(){
        if(isExtending()){
            return aClass.getSuperclass().getSimpleName();
        }
        return null;
    }

    @Override
    public boolean isParentClassAbstract(){
        if(isExtending()){
            return Modifier.isAbstract(aClass.getSuperclass().getModifiers());
        }
        return false;
    }

    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) throws NoSuchMethodException  {
        try {
            Method func = aClass.getMethod(methodName);
            Object value = func.invoke(this.obj, args);
            return (int)value;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object createInstance(int numberOfArgs, Object... args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        try {
            Class[] argArr = new Class[numberOfArgs];
            for (int i = 0; i < numberOfArgs; i++)
                argArr[i] = (Class) args[i].getClass().getField("TYPE").get(null);

            Constructor ctor = aClass.getConstructor(argArr);
            Object resObj = ctor.newInstance(args);
            return resObj;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        return null;
    }

    @Override
    public String getInheritanceChain(String delimiter) {
        List<String> InheritanceChain = new LinkedList<>();
        Class Parent = aClass.getClass();
        do{
            InheritanceChain.add(new String(Parent.getSimpleName()));
            Parent = Parent.getSuperclass();
        } while(Parent != null);

        String stringChain = new String();
        for (int i = InheritanceChain.toArray().length - 1; i >=0 ; i--) {
            stringChain += InheritanceChain.get(i);

            if(i-1 >=0){
                stringChain += delimiter;
            }
        }
        return stringChain;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        return null;
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
