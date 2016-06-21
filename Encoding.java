import utils.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Encoding {

	public static void main(String[] args) {
		
		if (args.length < 2){
			System.out.println("Please Provide arguments for: \n"
					           + "* Frequency table filepath   "
					           + "* Output file to store results");
			System.exit(0);
		}
		
		String fileNameFreqTable = args[0];
		String oFileName = args[1];
		String iFileName="";
		String action = "";
		
		if (args.length > 2){
			action = args[2];
			iFileName = args[3];
		}

		
		File inputfileName = null;
		File outputFileName = new File(oFileName);
		BufferedReader bufferReader = null;
		BufferedWriter bufferWriter = null;
		FileReader fileReader = null;
		
		String line;
		
		try{
			Node[] freqTable = getFrequencyTable(fileNameFreqTable);
			HuffmanEncoding hf = new HuffmanEncoding(freqTable);
			
			FileWriter fileWriter = new FileWriter(outputFileName);
			bufferWriter = new BufferedWriter(fileWriter);
			
			String title = "***************************************";
			title += "\nCrypto HuffmanEncoding\n";
			title += "***************************************\n";
			println(title, bufferWriter);
			
			println("* Tree Representation Huffman Encoding *", bufferWriter);
			println(hf.getTree("preOrder") + "\n", bufferWriter);
			
			println("* Encoding Table from Frequency Table *", bufferWriter);
			println(hf.getTableCode(), bufferWriter);
			
			//Encode - decode
			if (iFileName != "" && action != "" ){
				inputfileName = new File (iFileName);
				fileReader = new FileReader(inputfileName);
				bufferReader = new BufferedReader(fileReader);
				String originalMessage = "";
				String message = "";
				
				while((line = bufferReader.readLine())!= null){
					if (!line.trim().isEmpty()){
						originalMessage += line + "\n";
						if (action.compareTo("encode")==0) {
							message += hf.encode(line) + "\n";
						}
						else if (action.compareTo("decode")==0) {
							message += hf.decode(line) + "\n";
						}
				    			
					}		
				}
				
				String action_msg = "";
				if (action.compareTo("encode")==0) {
					action_msg = "Encoded";
				}
				else {
					action_msg = "Decoded";
				}
				
				println("* Original Message * ", bufferWriter);
				println(originalMessage, bufferWriter);
				
				println("* " + action_msg + " Message *", bufferWriter);
				println(message, bufferWriter);
				
			}
		}
		catch (InvalidInputException ex){
			 System.out.println(ex.getMessage());
		}	
		catch(FileNotFoundException ex) {
		    System.out.println("Unable to open file '" + 
		    		inputfileName.getName() + "'");                
		}
		catch(IOException ex) {
		    System.out.println("Error reading file '" + 
		    		inputfileName.getName() + "'");                  
		}
		finally {
			try {
				if (iFileName != ""){
					bufferReader.close();
				}
				bufferWriter.close();
			}
			catch(IOException ex) {
				System.out.println("Failed to close Buffer");
		    }		
		}
		
	}
	
	private static Node[] getFrequencyTable(String filepath) 
			              throws InvalidInputException{
		/**
		 * Extract frequency table from file:
		 * 
		 * input:
		 * A - 1
		 * B - 3
		 */
		
		File inputfileName = new File (filepath);
		BufferedReader bufferReader = null;
		String line;
		Node[] freqTable = new Node[26];
		int counter = 0;
		try{
			FileReader fileReader = new FileReader(inputfileName);
			bufferReader = new BufferedReader(fileReader);
			
			while((line = bufferReader.readLine())!= null){
				
			    if (!line.trim().isEmpty()){
			    	String[] arr = line.split("\\s+");
			    	if (arr.length > 3){
			    		throw new InvalidInputException("Input format error. Please Provide a "
			    										+ "valid Frequency table format as "
			    										+ "instructed in the Readme");
			    	}
			    	Node node = new Node(arr[0].trim().toUpperCase(), Integer.parseInt(arr[2].trim()));
			    	freqTable[counter++] = node;
				}
		    }
			
			//adjust array
			Node[] resizedfreqTable = new Node[counter];
			for (int i=0; i<resizedfreqTable.length; i++) {
				resizedfreqTable[i] = freqTable[i];
			}
			
			return resizedfreqTable;
		}
		catch(FileNotFoundException ex) {
		    System.out.println("Unable to open file '" + 
		    		inputfileName.getName() + "'");                
		}
		catch(IOException ex) {
		    System.out.println("Error reading file '" + 
		    		inputfileName.getName() + "'");                  
		}
		finally {
			try {
				bufferReader.close();
			}
			catch(IOException ex) {
				System.out.println("Failed to close Buffer");
		    }		
		}
		
		return null;
	}
	
	/**
	 * Writes to screen and into file
	 * 
	 * @param String   Output to write into file
	 * @param BufferedWriter  Buffer used to write string into file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void println(String msg, BufferedWriter bw)
	throws FileNotFoundException, IOException {
		/**
		 * Print to screen and to file
		 * @param String to print
		 * @param bufferedwriter to write the msg into
		 */
		System.out.println(msg);
		bw.write(msg + "\n");	
	}

}
