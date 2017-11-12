package com.neogrid.avaliacao.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUtil {

	public static List<String> read(String fileName) {
		List<String> talkList = null;
		try {
			talkList = new ArrayList<>();
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			while (strLine != null) {
				talkList.add(strLine);
				strLine = br.readLine();
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Erro na leitura do arquivo: ");
			System.out.println(e.getStackTrace());
			return null;
		}
		return talkList;
	}

}
