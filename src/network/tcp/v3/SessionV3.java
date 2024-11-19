package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV3 implements Runnable {

    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
