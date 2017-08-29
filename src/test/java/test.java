import com.jcraft.jsch.*;

import java.io.InputStream;

public class test {
    public static void main(String[] args) throws Exception {
        SshCommandExecutor executor = new SshCommandExecutor();

        System.out.println(executor.execute("uname -a"));
        System.out.println(executor.execute("uname -a"));
        System.out.println(executor.execute("uname -a"));

        executor.destroy();
    }
}
