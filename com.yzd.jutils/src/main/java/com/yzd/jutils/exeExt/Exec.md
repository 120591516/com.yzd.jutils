java使用RunTime调用windows命令行
当Java需要调用windows系统进行交互时，可以使用Runtime进行操作。

例子：

1、调用window中获取关于java相关的进行信息

复制代码
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("wmic process where caption=\"javaw.exe\" get caption,commandline /value");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while((line=br.readLine())!=null){
            System.out.println(new String(line.getBytes(), "GBK"));
        }
        br.close();
        System.out.println("over");
复制代码
显示结果如下：



 

2、在java中查询windows中端口 :80

复制代码
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("cmd /k netstat -an | findstr :80"); // OK
        
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while((line=br.readLine())!=null){
            System.out.println(new String(line.getBytes(), "GBK"));
        }
        br.close();
        System.out.println("over");
复制代码
结果为：



3、执行bat文件

Runtime rt = Runtime.getRuntime();
rt.exec("cmd /k start d:/Run/hello.bat");
注意：在编写的简单的bat，可以按照上述方式执行，但是：当hello需要依赖外部环境变量时不行

比如我需要启动tomcat来发布一个web

rt.exec("cmd /k start d:/Run/Tomcat/bin/startup.bat");//会报错
// 当使用exec需要执行多条命令时，使用&&进行拼接
rt.exec("cmd /k d: && cd d:/Run/Tomcat/bin && startup.bat");//OK
cmd /c dir 是执行完dir命令后关闭命令窗口。
cmd /k dir 是执行完dir命令后不关闭命令窗口。
cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。