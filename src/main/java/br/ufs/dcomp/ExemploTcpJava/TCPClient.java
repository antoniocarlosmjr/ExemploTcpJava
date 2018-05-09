/**
 * @author Antonio Martins (DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient{
    
    public static void main(String[] args) throws IOException{
        try {
            System.out.print("[ Conectando com o Servidor TCP    ..................  ");
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            
            Scanner sc = new Scanner(System.in);
            String msg = "";
            
            while (true) {
                System.out.println("Digite sua mensagem:");
                msg = sc.nextLine();
                
                byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                System.out.print("[ Enviando mensagem    ..............................  ");
                os.write(buf);
                System.out.println("[OK] ]");
                    
                if (msg.intern() != "exit") {
                    byte[] buf_recepcao = new byte[50]; // buffer de recepção
                    System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
                    is.read(buf_recepcao); // Operação bloqueante (aguardando chegada de dados)
                    System.out.println("[OK] ]");
                    String msg_recebida = new String(buf_recepcao);
                    msg_recebida = msg_recebida.trim();
                    if (msg_recebida.intern() != "exit") {
                        System.out.println("  Mensagem recebida: "+ msg_recebida);    
                    } else {
                        System.out.println("[ FIM ]");
                        System.exit(1);
                    }
                } else {
                    System.out.println("[ FIM ]");
                    System.exit(1);
                }
            }
            
            
        }catch(Exception e){System.out.println(e);}    
            System.out.println("[ FIM ]");
    }
}