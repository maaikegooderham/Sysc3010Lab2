import java.net.*;
import java.util.Scanner;

public class UDPSender {

	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
			 int numOfMsgs = Integer.parseInt(args[2]);
			 int msgsSent = 0;
	         socket = new DatagramSocket() ;
     
	         String message = null;
	         while (msgsSent < numOfMsgs)
	         {
	        	 	// Create message to send and insert it into a new packet
					 message = "Message" + msgsSent;
	        		 byte [] data = message.getBytes() ;
	        		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		 
	        		 // send the packet, increment the amount of msgs we sent, and wait to receive message of same length
	        		 socket.send( packet ) ;
	        		 msgsSent++;
					 packet = new DatagramPacket(data, data.length) ;
					 socket.receive(packet);							 
					 System.out.println("ACK: " + new String(packet.getData(), 0, packet.getLength()));
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
            socket.close() ;
      }
   }
}

