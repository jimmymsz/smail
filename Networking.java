package smail;
import java.io.*;
import java.net.*;
import java.util.*;


public class Networking {
    public static void main(String[] args) throws IOException {
        try(Socket s = new Socket("192.168.43.27", 9019)){
            //try(Socket s = new Socket("172.10.9.94", 9090)){
            Scanner cin = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            String response = "", request = "";
            for (int i = 0; i < 3;i++){
                response = in.readLine();
                System.out.println(response);
            }
            while (true){
                System.out.print(">> ");
                request = cin.nextLine(); // user ketik input
                out.println(request); //kirim ke server
                response = in.readLine(); //baca respon server
                if (response == null || response.equals(""))
                    break;
                System.out.println(response);

            }
            System.out.println("Connection closed.");
        }
    }
}
