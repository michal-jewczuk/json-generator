package nodomain.jsongenerator.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import nodomain.jsongenerator.config.AppConfig;

public class ReadWriteUtil {

	public static String readStructure() {
		StringBuilder structure = new StringBuilder();
		
		try (BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(AppConfig.CONFIGURATION_FILE), "UTF-8"))) {
			
			String line;
			while ((line = in.readLine()) != null) {
				structure.append(line).append("\n");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return structure.toString();
	}
	
	public static String writeToUniqueFile(StringBuilder output, String fileName) {
		fileName = generateUniqueName(fileName);
		
		return writeToFile(output, fileName);
	}
	
	public static String writeToFile(StringBuilder output, String fileName) {		
		try (BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(fileName), "UTF-8"))) {
			
			out.write(output.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}

	private static String generateUniqueName(String fileName) {
		StringBuilder output = new StringBuilder(fileName);
		output.append("_");
		output.append(System.currentTimeMillis());
		output.append(".json");
		return output.toString();
	}
	
}
