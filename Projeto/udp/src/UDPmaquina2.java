import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane; 
public class UDPmaquina2 {    
		public static void main(String args[]) throws Exception{
			DatagramSocket serverSocket = new DatagramSocket(9876);             
			byte[] receiveData = new byte[1024];             
			byte[] sendData = new byte[1024];             
			while(true){
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String( receivePacket.getData()); 
				System.out.println("Recebido: " + sentence);
				InetAddress IPAddress = receivePacket.getAddress();  
				int port = receivePacket.getPort(); 
				String capitalizedSentence = execCommand(sentence); 
				JOptionPane.showMessageDialog(null, capitalizedSentence);
				
			}
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