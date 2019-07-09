package com.yzd.jutils.exeExt;

/**
 * Created by zd.yao on 2017/6/13.
 */
public class ExeUtil {
    public static void openWindowsExe(final String command) {
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            //final String command = "notepad";// 记事本
            process = runtime.exec(String.format("\"%s\"",command));
        } catch (final Exception e) {
			System.out.println("Error win exec!!");
        }
    }

    public static void openWindowsExeCMD(final String command) {
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            //final String command = "notepad";// 记事本
            process = runtime.exec(String.format("cmd /c start %s",command));
        } catch (final Exception e) {
            System.out.println("Error win exec!");
        }
    }

	/**
	 * 执行bat Runtime rt = Runtime.getRuntime(); rt.exec(
	 * "cmd /k start d:/Run/hello.bat");
	 * 注意：在编写的简单的bat，可以按照上述方式执行，但是：当hello需要依赖外部环境变量时不行
	 * 
	 * 比如我需要启动tomcat来发布一个web
	 * 
	 * rt.exec("cmd /k start d:/Run/Tomcat/bin/startup.bat");//会报错 //
	 * 当使用exec需要执行多条命令时，使用&&进行拼接 rt.exec(
	 * "cmd /k d: && cd d:/Run/Tomcat/bin && startup.bat");//OK cmd /c dir
	 * 是执行完dir命令后关闭命令窗口。 cmd /k dir 是执行完dir命令后不关闭命令窗口。 cmd /c start dir
	 * 会打开一个新窗口后执行dir指令，原窗口会关闭。 cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭
	 * 
	 * @param command
	 */
	public static void execBat(final String command) {
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
//			String[] cmd = { "cmd", "/k", "D:", "cd D:/anfang/bin/", "startup.bat" };
//			Process proc = Runtime.getRuntime().exec(cmd);
			process = runtime.exec(String.format("cmd /k %s", command));
			// process = runtime.exec("cmd /k cmd.exe runAs /user:administrator
			// notepad");
		} catch (final Exception e) {
			System.out.println("Error win exec!");
		}
	}
	
	/**
	 * 很简单，用runas的方式来执行。
	 * 
	 * 比如以administrator的身份来启动记事本：
	 * 
	 * Runtime.getRuntime().exec("cmd /C cmd.exe runAs /user:administrator notepad");
	 * 
	 * @param args
	 */
	public static void runCMDByAdministrator(final String command) {
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			// final String command = "notepad";// 记事本
			process = runtime
					.exec(String.format("cmd /C start cmd.exe runas /user:administrator java -version", command));
		} catch (final Exception e) {
			System.out.println("Error win exec!");
		}
	}

	// 需要将windows的系统通知调到最低
	public static void runNIRCMDByAdministrator(final String command) {
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			// final String command = "notepad";// 记事本
			process = runtime.exec(String.format("c:/nircmd.exe elevate %S", command));
		} catch (final Exception e) {
			System.out.println("Error win exec!");
		}
	}

	public static void main(String[] args) {
		// openWindowsExe("cmd /C cmd.exe runAs /user:administrator notepad");
		// openWindowsExeCMD("notepad");
		// execBat("d: && cd D:/anfang/bin && startup.bat");
		// runNIRCMDByAdministrator("net start anfang");
		runCMDByAdministrator("&& java -version");
	}
}