package com.yzd.jutils.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class SevenZUtils {
	/**
	 * @author kxl
	 * @param orgPath
	 *            源压缩文件地址
	 * @param tarpath
	 *            解压后存放的目录地址
	 */
	public static void apache7ZDecomp(String orgPath, String tarpath) {

		try {
			SevenZFile sevenZFile = new SevenZFile(new File(orgPath));
			SevenZArchiveEntry entry = sevenZFile.getNextEntry();
			while (entry != null) {

				// System.out.println(entry.getName());
				if (entry.isDirectory()) {

					new File(tarpath + entry.getName()).mkdirs();
					entry = sevenZFile.getNextEntry();
					continue;
				}
				FileOutputStream out = new FileOutputStream(tarpath + File.separator + entry.getName());
				byte[] content = new byte[(int) entry.getSize()];
				sevenZFile.read(content, 0, content.length);
				out.write(content);
				out.close();
				entry = sevenZFile.getNextEntry();
			}
			sevenZFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SevenZUtils.apache7ZDecomp("D:\\测试照片\\Security(1).7z", "D:\\测试照片\\测试照片");
	}
}
