package com.example.demo.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
*
* [설명] Raspberry Pi와 통신을 위한 클래스
*
* @file : RaspPiClientConfig.java
* @package : com.example.demo.config
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 5. 7.
* @link : http://hunit.tistory.com/256
*/
public class RaspPiClientConfig {
	public void clientRun(String data) {

		Socket socket = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			socket = new Socket("172.20.14.8", 7777);
			os = socket.getOutputStream();
			osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw); // 서버로 전송을 위한 OutputStream

			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr); // 서버로부터 Data를 받음

			bw.write(data);
			bw.newLine();
			bw.flush();

			String receiveData = "";
			receiveData = br.readLine(); // 서버로부터 데이터 한줄 읽음
			System.out.println("서버로부터 받은 데이터 : " + receiveData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				osw.close();
				os.close();
				br.close();
				isr.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
