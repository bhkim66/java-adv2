package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV3 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = new Socket("localhost", PORT);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
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

        //자원 정리
        log("연결 종료 " + socket);
        input.close();
        output.close();
        socket.close();
    }
}
