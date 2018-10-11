package com.yzd.jutils.test;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.yzd.jutils.file.FileUtils;

public class TestFile {
	public static void main(String[] args) throws Exception {
		MultipartFile zipFile = (MultipartFile) new File("D:\\测试照片\\测试照片.zip");
		// 获取文件后缀
		String suffix = zipFile.getName().trim().substring(zipFile.getName().indexOf("."));
		System.out.println("后缀：" + suffix);
		String num = UUID.randomUUID().toString();
		// 服务器存储文件路径
		String pathName = "D:\\测试照片\\" + num + suffix;
		System.out.println("新路径pathName：" + pathName);
		// 没有路径自动创建路径
		FileUtils.makefile(pathName);
		// 转存文件
		zipFile.transferTo(new File(pathName));
	}
}
