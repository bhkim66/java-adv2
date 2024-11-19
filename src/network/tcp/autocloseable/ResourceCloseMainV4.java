package network.tcp.autocloseable;

public class ResourceCloseMainV4 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            e.printStackTrace();
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            e.printStackTrace();
        }
    }

    private static void logic() throws CallException, CloseException {
        try (ResourceV2 resourceV1 = new ResourceV2("resourceV1");
             ResourceV2 resourceV2 = new ResourceV2("resourceV2")) {
            resourceV1.call();
            resourceV2.callEx();

        } catch (CallException e) {
            System.out.println("e = " + e);
            throw e;
        }

    }
}
