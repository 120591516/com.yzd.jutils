package com.yzd.jutils.image;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by lifan on 2019/7/10.
 */
public class BufferedImageTest {
    /**
     * 文件镜像
     * @return
     * @throws Exception
     */
    public File fileMirrorToFile()throws  Exception{
        BufferedImage bufImage = ImageIO.read(new File("aa.jpg"));

        // 获取图片的宽高
        final int width = bufImage.getWidth();
        final int height = bufImage.getHeight();

        // 读取出图片的所有像素
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width / 2; col++) {
                int temp = rgbs[row * width + col];
                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                rgbs[row * width + (width - 1 - col)] = temp;
            }
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);

        // 把修改过的 bufImage 保存到本地
        File resultFile = new File("bb.jpg");
        ImageIO.write(bufImage, "JPEG", resultFile);
        return resultFile;
    }

    /**
     * 文件镜像
     * @return base64
     * @throws Exception
     */
    public String fileMirrorToBase64()throws  Exception{
        BufferedImage bufImage = ImageIO.read(new File("aa.jpg"));

        // 获取图片的宽高
        final int width = bufImage.getWidth();
        final int height = bufImage.getHeight();

        // 读取出图片的所有像素
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width / 2; col++) {
                int temp = rgbs[row * width + col];
                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                rgbs[row * width + (width - 1 - col)] = temp;
            }
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        ImageIO.write(bufImage, "png", baos);//写入流中
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        return png_base64;
    }
}
