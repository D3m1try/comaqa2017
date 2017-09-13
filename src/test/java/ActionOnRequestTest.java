import org.testng.annotations.Test;

public class ActionOnRequestTest {

    @Test
    public void doTest() throws Exception {
        SshCommandExecutor sshOperationsService = new SshCommandExecutor();

        sshOperationsService.execute("( timeout 30 tail -f --bytes=0 tmp 2> /dev/null || true ) | \n" +
                "stdbuf -i0 -o0 -e0 grep \"jjj\" | \n" +
                "(dd bs=1 count=1 2>/dev/null; kill $$)");

        Actor.doAction();
    }
}
