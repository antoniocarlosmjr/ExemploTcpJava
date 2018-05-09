/**
 * @author Antonio Martins (DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPServer{
    public static void main(String[] args){
        
        try {
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
  
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            
            Scanner sc = new Scanner(System.in);
            String msg_resposta = "";
            
            while (true) {
                byte[] buf = new byte[50]; // buffer de recepção
    
                System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
                is.read(buf); // Operação bloqueante (aguardando chegada de dados)
                System.out.println("[OK] ]");
                String msg = new String(buf); // Mapeando vetor de bytes recebido para String
                msg = msg.trim();
                if (msg.intern() != "exit") {
                    System.out.println("  Mensagem recebida: "+ msg);
                    System.out.println("Digite sua mensagem:");
                    msg_resposta = sc.nextLine();
                    byte[] buf_envio = msg_resposta.getBytes(); 
                    System.out.print("[ Enviando mensagem de retorno    ...................  ");
                    os.write(buf_envio);
                    System.out.println("[OK] ]");
                    if (msg_resposta.intern() == "exit") {
                        System.out.println("[ FIM ]");
                        System.exit(1);
                    }
                } else {
                    System.out.println("[ FIM ]");
                    System.exit(0);
                }
            }
            
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}