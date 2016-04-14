package com.eling.elcms.video.timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamGobbler extends Thread {
	InputStream is;

	String type;

	public StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public void readStream() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				/*if(line.contains("avcodec mux debug: writing header")){
					break;
				}*/
				if (type.equals("Error"))
					System.out.println(line);
				// LogManager.logError(line);
				else
					System.out.println(line);
				//LogManager.logDebug(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				/*if (type.equals("Error"))
					System.out.println(line);
				// LogManager.logError(line);
				else
					System.out.println(line);
				// LogManager.logDebug(line);
*/			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
