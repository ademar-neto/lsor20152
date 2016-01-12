import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane; 
public class UDPMaquina2 {    
		public static void main(String args[]) throws Exception{
			DatagramSocket serverSocket = new DatagramSocket(9876);             
			byte[] receiveData = new byte[7];             
			while(true){
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				
                                String MsgRecebida = new String( receivePacket.getData()); 
				
                                System.out.println("Recebido: " + MsgRecebida);
				
                                InetAddress IPAddress = receivePacket.getAddress();  
				
                                int port = receivePacket.getPort(); 
				
                                String retornoValidacao = validarMsg(MsgRecebida);
                                
                                String retornoComando = execCommand(retornoValidacao); 
				
                                if(MsgRecebida.equals("network")){
                                    JOptionPane.showMessageDialog(null, retornoComando);
                                }
                                
				
			}
		}
                
                
                public synchronized static String validarMsg(String parametro) throws IOException {
			String result = null;
                        
                        if(parametro.equals("network")){
                            result = "ipconfig";
                        }else if(parametro.equals("shtdown")){
                            result = "shutdown -s -t 15 -c \"vou desligar em 15 segundos\"";
                        }else if(parametro.equals("restart")){
                            result = "shutdown -r -t 15 -c \"vou reiniciar em 15 segundos\"";
                        }
                         
                        return result;
		}
                
		
		public synchronized static String execCommand(final String commandLine) throws IOException {
			
			boolean success = false;
			String result;
			Process p;
			BufferedReader input;
			StringBuffer cmdOut = new StringBuffer();
			String lineOut = null;
			int numberOfOutline = 0;
			try {
				p = Runtime.getRuntime().exec(commandLine);
				input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((lineOut = input.readLine()) != null) {
					if (numberOfOutline > 0) {
						cmdOut.append("\n");
					}
					cmdOut.append(lineOut);
					numberOfOutline++;
				}
				result = cmdOut.toString();
				success = true;
				input.close();
			} catch (IOException e) {
				result = String.format("Falha ao executar comando %s. Erro: %s", commandLine, e.toString());
			}
			if (!success) {
				throw new IOException(result);
			}
			return result;
		}
}
