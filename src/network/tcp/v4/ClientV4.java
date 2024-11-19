package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.util.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            socket = new Socket("localhost", PORT);

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            log("소켓 연결");

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
        }  finally {
            // 자원 정리
            closeAll(socket, input, output);
            log("연결 종료 " + socket);
        }
    }
}
