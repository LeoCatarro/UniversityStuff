package sd;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.text.StyledEditorKit.FontFamilyAction;

public class EchoServer {

    private int serverPort;	
    
    public EchoServer(int p) {
	serverPort= p;		
    }
    
    
    public static void main(String[] args){
		System.err.println("SERVER...");
		if (args.length<1) {
			System.err.println("Missing parameter: port number");	
			System.exit(1);
		}
		int p=0;
		try {
			p= Integer.parseInt( args[0] );
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		
		EchoServer serv= new EchoServer(p);
		serv.servico();   // activa o servico
		}

		
		// activa o servidor no porto indicado em "serverPort"
		public void servico(){
		
			// exercicio 2: inicializar um socket para aceitar ligacoes...
			try {
				ServerSocket svSocket = new ServerSocket(serverPort);
				
				//Connections cycle
				while(true) {
					Socket data = svSocket.accept();

					try{
						//Read data sent by client
						BufferedReader breader = new BufferedReader(new InputStreamReader(data.getInputStream()));
						String msg = breader.readLine();

						//Server Echo Message response
						DataOutputStream out = new DataOutputStream(data.getOutputStream());
						out.writeChars(msg);
						
						System.out.println(msg);
					}

					catch(Exception exNoAtendimento) {
						exNoAtendimento.printStackTrace();
					}

					finally {
						try {
							data.close();
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			catch(Exception e2) {
				e2.printStackTrace();
			}


    }


}
