package tcpserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.SneakyThrows;

/**
 *
 * @author eyraf
 */
public class TCPServer {

    @SneakyThrows
    public static void main(String[] args) {
        //readAsString();
        readAsByte();
        System.out.println("Server exit...");
    }

    @SneakyThrows
    public static void readAsByte() {
        System.out.println("Server side:\n\n");
        ServerSocket ss = new ServerSocket(6789);//Localhost or 127.0.0.1

        while (true) {
            System.out.println("Server listening..");
            System.out.println("Socket successfully created..\n"
                    + "Socket successfully binded..");
            
            System.out.println("server acccept the client...");
            Socket conn = ss.accept();
            
            DataInputStream dis = new DataInputStream(conn.getInputStream());
            byte[] bs = readMessage(dis);
            writeBytes("D:\\Wallpaper\\eyraf.jpg", bs);
        }
    }

    @SneakyThrows
    public static void readAsString() {
        ServerSocket ss = new ServerSocket(6789);//Localhost or 127.0.0.1

        while (true) {
            System.out.println("Server listening..");
            System.out.println("Socket successfully created..\n"
                    + "Socket successfully binded..");
            Socket conn = ss.accept();
            System.out.println("server acccept the client...");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String messageFromClient = br.readLine() + "\n";

            System.out.println("From client: "+ messageFromClient);
        }
    }

    @SneakyThrows
    public static byte[] readMessage(DataInputStream dis) {
        int length = dis.readInt();
        byte[] msg = new byte[length];
        dis.readFully(msg);// -> be careful
        return msg;
    }

    @SneakyThrows
    public static void writeBytes(String fileName, byte[] bs) {
        OutputStream os = new FileOutputStream(fileName);
        os.write(bs);
        os.close();
        System.out.println("Done!");
    }
}
