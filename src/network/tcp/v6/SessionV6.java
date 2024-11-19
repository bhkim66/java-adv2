package network.tcp.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.util.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV6 implements Runnable {

    private final Socket socket;
    private final DataOutputStream output;
    private final DataInputStream input;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;

    public SessionV6(SessionManagerV6 sessionManager, Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF(); // 블로킹 메소드 이므로 자원이 종료되면서 SocketException 발생
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
        } finally {
            sessionManager.remove(this);
            close(); // shutdownHook으로 close가 먼저 실행된 뒤 finally에서 close 실행하지만 closed로 플래그를 걸어놨기 때문에 중복호출이 되지 않는다!
        }
    }

    // 세션 종료시, 서버 종료시 동시에 호출될 수 있다
    public void close() {
        synchronized (this) {
            if (closed) {
                return;
            }
            closeAll(socket, input, output);
            closed = true;
        }
        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
