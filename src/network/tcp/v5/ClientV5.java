package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.util.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV5 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())){

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("전송할 문자 : ");
                String toSend = scanner.nextLine();

                // 서버로 보내기
                output.writeUTF(toSend);
                log("client -> server");

                if (toSend.equals("exit")) {
                    break;
                }

                // 서버로 부터 받기
                String getMessage = input.readUTF();
                log("getMessage : " + getMessage);
                log("client <- server");
            }
        } catch (IOException e) {
            log(e);
        }
    }
}
