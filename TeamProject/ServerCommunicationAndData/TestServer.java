package ServerCommunicationAndData;

import java.io.IOException;

public class TestServer
{
	private GameServer server;

public TestServer(int port, int timeout)
{
server = new GameServer();
server.setPort(port);
server.setTimeout(timeout);

try {
server.listen();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

public static void main(String[] args)
{
//Extract the args port/timeout
//int port = Integer.parseInt(args[0]);
//int timeout = Integer.parseInt(args[1]);
new TestServer(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
}
}
