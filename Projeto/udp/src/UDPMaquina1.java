import java.io.*; 
import java.net.*;

import javax.swing.JOptionPane; 
public class UDPMaquina1 {    
	public static void main(String args[]) throws Exception{     
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024]; 
		byte[] receiveData = new byte[1024]; 
		String sentence = JOptionPane.showInputDialog("teste");
		sendData = sentence.getBytes();    
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); 
		clientSocket.send(sendPacket); 
		clientSocket.close();   
	} 
	
	
	
}