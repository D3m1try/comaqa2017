import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

public class PortListener implements Runnable{
    private Callable callbackFunction;

    public PortListener(Callable callbackFunction) {
        this.callbackFunction = callbackFunction;
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(47000)) {
            Socket conn = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            // don't use buffered writer because we need to write both "text" and "binary"
            OutputStream out = conn.getOutputStream();
            int count = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    System.out.println("Connection closed");
                    break;
                }
                System.out.println("" + count + ": " + line);
                if (line.equals("")) {
                    System.out.println("Writing response...");

                    String statusLine = "HTTP/1.1 200 OK\r\n";
                    out.write(statusLine.getBytes("ASCII"));

                    String contentLength = "Content-Length: " + 0 + "\r\n";
                    out.write(contentLength.getBytes("ASCII"));

                    // signal end of headers
                    out.write("\r\n".getBytes("ASCII"));

                    out.flush();

                    callbackFunction.call();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
