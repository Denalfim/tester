import interfaces.AfterSuite;
import interfaces.BeforeSuite;
import interfaces.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Teste {
public static void  start(Class cls) throws InvocationTargetException, IllegalAccessException{
    Method beforeMethod = null;
    Method afterMethod = null;
    ArrayList<Method> testM = new ArrayList<Method>();

    Object obj = null;

    try {
        obj = cls.newInstance();
    } catch (InstantiationException e){
        e.printStackTrace();
    }

    for(Method m : cls.getDeclaredMethods()){
        if(m.isAnnotationPresent(Test.class)){
            testM.add(m);
        } else if(m.isAnnotationPresent(BeforeSuite.class)){
            if(beforeMethod == null) {
                beforeMethod = m;
            } else {
                throw new RuntimeException("Методов с аннотацией @BeforeSuite должно быть не более одного");
            }
        } if (m.isAnnotationPresent(AfterSuite.class)){
            if(afterMethod == null){
                afterMethod = m;
            } else {
                throw new RuntimeException("Методов с аннотацией @AfterSuite должно быть не более одного");
            }
        }
    }

    if(beforeMethod != null){
        beforeMethod.invoke(obj);
    }

    testM.sort(Comparator.comparingInt( o -> o.getAnnotation(Test.class).priority()));
    for(Method m: testM){
        m.invoke(obj);
    }

    if(afterMethod != null){
        afterMethod.invoke(obj);
    }
}

}
