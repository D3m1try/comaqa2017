import com.jcraft.jsch.*;

import java.io.InputStream;

public class SshCommandExecutor {
    Session session;
    Channel channel;
    InputStream in;

    public SshCommandExecutor() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession("dima", "192.168.56.1", 22);
            UserInfo ui = new MyUserInfo();
            session.setUserInfo(ui);
            session.connect();

        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void setRemotePortForwarding(int remotePort, String host, int localPort){
        try {
            session.setPortForwardingR(remotePort, host, localPort);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void setLocalPortForwarding(int localPort, String host, int remotePort){
        try {
            session.setPortForwardingL(localPort, host, remotePort);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public String execute(String command) throws Exception {
        StringBuilder result = new StringBuilder();

        channel = session.openChannel("exec");
        in = channel.getInputStream();

        ((ChannelExec) channel).setCommand(command);
        ((ChannelExec) channel).setErrStream(System.err);

        channel.connect();

        byte[] tmp=new byte[1024];
        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                result.append(new String(tmp, 0, i));
            }
            if(channel.isClosed()){
                if(in.available()>0) continue;
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }

        return result.toString();
    }

    public void destroy() {
        channel.disconnect();
        session.disconnect();
    }
}
