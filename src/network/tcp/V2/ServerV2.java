package network.tcp.V2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV2 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");

        ServerSocket server = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트 : " + PORT);

        Socket socket = server.accept();
        log("소켓 연결 : " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        while (true) {
            // 클라이언트로부터 문자 받기
            String received = input.readUTF();
            log("getMessage : " + received);
            log("client -> server");

            if (received.equals("exit")) {
                break;
            }

            // 클라이언트한테 메세지 보내기
            String sendMessage = "world!";
            output.writeUTF(received + " " + sendMessage);
            log("client <- server : " + received + " " + sendMessage);
        }



        //자원 정리, 사용 역순으로 정리
        log("연결 종료 " + socket);
        input.close();
        output.close();
        socket.close();
        server.close();
    }
}
