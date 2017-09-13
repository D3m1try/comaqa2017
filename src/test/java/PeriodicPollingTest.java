import org.testng.annotations.Test;


public class PeriodicPollingTest {

    @Test
    public void doTest() throws Exception {
        long startTime = System.currentTimeMillis();
        boolean flag = true;

        SshCommandExecutor sshOperationsService = new SshCommandExecutor();
        while ((System.currentTimeMillis() - startTime) < 300000 && flag) {
            Thread.sleep(20000);
            flag = sshOperationsService.execute("cat tmp | grep 'jjj'").isEmpty();
        }

        Actor.doAction();
    }
}
