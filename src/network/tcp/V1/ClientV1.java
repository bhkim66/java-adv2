package network.tcp.V1;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class ClientV1 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = new Socket("localhost", PORT);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("소켓 연결");

        String sendMessage = "hello";
        output.writeUTF(sendMessage);
        log("client -> server");

        String getMessage = input.readUTF();
        log("getMessage : " + getMessage);
        log("client <- server");

        //자원 정리
        log("연결 종료" + socket);
        input.close();
        output.close();
        socket.close();
    }
}
