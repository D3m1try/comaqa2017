import com.jcraft.jsch.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyUserInfo implements UserInfo {
    String passphrase = "";
    String password = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String getPassphrase() {
        System.out.println("get passphrase");
        return passphrase;
    }

    @Override
    public String getPassword() {
        System.out.println("get password");
        try {
            password = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        System.out.println(message);
        System.out.println("y/n");
        try {
            if (reader.readLine().equalsIgnoreCase("y")){
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
