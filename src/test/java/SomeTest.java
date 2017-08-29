import org.testng.annotations.Test;

import java.util.concurrent.Callable;

public class SomeTest {
    public static void main(String[] args) throws Exception {
        Callable helloWorldPrinter = () -> {
            System.out.println("hello world!");
            return 0;
        };

        Thread thread = new Thread(new PortListener(helloWorldPrinter));

        thread.start();
        System.out.println("main func end");
    }

    @Test
    public void test(){

    }
}
