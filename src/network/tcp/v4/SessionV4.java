package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.util.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV4 implements Runnable {

    private final Socket socket;

    public SessionV4(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataOutputStream output = null;
        DataInputStream input = null;
        try {
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

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
        }finally {
            closeAll(socket, input, output);
            log("연결 종료");
        }


    }
}
