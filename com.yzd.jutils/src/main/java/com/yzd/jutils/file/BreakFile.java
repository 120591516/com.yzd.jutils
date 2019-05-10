package com.yzd.jutils.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by on 2017/12/8.
 */
public class BreakFile {

	public static void getRow() {
		try {
			FileReader read = new FileReader("E:/backup/backup");
			BufferedReader br = new BufferedReader(read);
			String row;
			int rownum = 1;
			while ((row = br.readLine()) != null) {
				rownum++;
			}
			System.out.println("rownum=" + rownum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void cuttingFile() {
		
		try {
			FileReader read = new FileReader("E:/backup/backup");
			BufferedReader br = new BufferedReader(read);
			String row;

			int rownum = 1;

			int fileNo = 1;
			FileUtils.makefile("E:/backup/backup1/backup" + fileNo + ".log");
			FileWriter fw = new FileWriter("E:/backup/backup1/backup" + fileNo + ".log");
			while ((row = br.readLine()) != null) {
				rownum++;
				fw.append(row + "\r\n");

				if ((rownum / 304183) > (fileNo - 1)) {
					fw.close();
					fileNo++;
					fw = new FileWriter("E:/backup/backup1/backup" + fileNo + ".log");
				}
			}
			fw.close();
			System.out.println("rownum=" + rownum + ";fileNo=" + fileNo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		cuttingFile();
	}

}
