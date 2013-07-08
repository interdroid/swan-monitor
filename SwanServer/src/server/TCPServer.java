package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;




public class TCPServer {

	/**
	 * Simple Server that retrieves waits for json string from SwanMonitor app on port 6789 and prints it contents
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		System.out.println("Starting server");
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			
			parseJSONString(clientSentence);
			
			//System.out.println("Received: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
	
	private static void parseJSONString(String JSONString){
		try {
			JSONObject json = new JSONObject(JSONString);
			printJSONObject(json);
		} catch (JSONException e) {
			System.out.println("Failed to parse JSON String or Key not Found");
			e.printStackTrace();
		}
		
		
	}

	private static void printJSONObject(JSONObject json) throws JSONException {
		String expression = (String) json.get("expression");
		String sensorName = (String) json.get("sensorName");
		String valuepath = (String) json.get("valuePath");
		String unit = (String) json.get("unit");
		Long timestamp = (Long) json.get("timestamp");
		String reading = (String) json.get("reading");
		System.out.println(sensorName + ":" + valuepath + "\nExpression: " + expression );
		System.out.println("data: " + reading + " " + unit + " @ " + timestamp  );
		
	}

}
