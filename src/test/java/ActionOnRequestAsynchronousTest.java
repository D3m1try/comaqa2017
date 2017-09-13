import org.testng.annotations.Test;

import java.util.concurrent.Callable;

public class ActionOnRequestAsynchronousTest {
    Callable action = () -> {
        Actor.doAction();
        return "ok";
    };
    @Test
    public void doTest() throws Exception {
        PortListener actionListener = new PortListener(action, 47000);
        new Thread(actionListener).start();

        SshCommandExecutor sshOperationsService = new SshCommandExecutor();

        sshOperationsService.execute("( timeout 30 tail -f --bytes=0 tmp 2> /dev/null || true ) | \n" +
                "stdbuf -i0 -o0 -e0 grep \"jjj\" | \n" +
                "(dd bs=1 count=1 2>/dev/null; curl --request POST --header \"Content-Length: 0\" 127.0.0.1:47000);");
    }
}
