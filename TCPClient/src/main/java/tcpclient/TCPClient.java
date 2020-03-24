package tcpclient;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import lombok.SneakyThrows;

/**
 *
 * @author eyraf
 */
public class TCPClient {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Server side:\n\n");
        Socket s = new Socket("127.0.0.1", 6789);

        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        System.out.println("Socket successfully created..\n"
                + "connected to the server..");

        System.out.print("Enter message: ");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        //send
        //dos.writeBytes(msg);//msg -> message from client
        //or send file
        byte[] bs = readByteByByte(msg);//msg -> file path
        dos.writeInt(bs.length);//first of all add arr length
        dos.write(bs);

        s.close();
        System.out.println("Client Exit... ");
    }

    @SneakyThrows
    public static byte[] readByteByByte(String fileName) {
        Path filePath = Paths.get(fileName);
        byte[] byteArr = Files.readAllBytes(filePath);
        return byteArr;

    }
}
