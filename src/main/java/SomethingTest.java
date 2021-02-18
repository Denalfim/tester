import interfaces.AfterSuite;
import interfaces.BeforeSuite;
import interfaces.Test;

public class SomethingTest {

    @BeforeSuite
    public void  beforeSuite(){
        System.out.println("Метод Before Suite");
    }

    @Test(priority = 1)
    public void teste1(){
        System.out.println("Test 1");
    }

    @Test(priority = 3)
    public void teste3_1(){
        System.out.println("Test 3.1");
    }

    @Test(priority = 3)
    public void teste3_2(){
        System.out.println("Test 3.2");
    }

    @Test(priority = 2)
    public void teste2(){
        System.out.println("Test 2");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Метод After Suite");
    }


}
