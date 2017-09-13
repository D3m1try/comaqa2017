import com.jcraft.jsch.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyUserInfo implements UserInfo {
    String passphrase = "";
    String password = "111111";
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String getPassphrase() {
        System.out.println("get passphrase");
        return passphrase;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean promptPassword(String message) {
        System.out.println(message);

        return true;
    }

    @Override
    public boolean promptPassphrase(String message) {
        System.out.println(message);
        return true;
    }

    @Override
    public boolean promptYesNo(String message) {

        return true;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
