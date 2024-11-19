package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.util.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV5 implements Runnable {

    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (socket;
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {

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
        } catch (IOException e) {
            log(e);
        }

        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
