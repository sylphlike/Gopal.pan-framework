package com.github.sylphlike.framework.security;

import com.github.sylphlike.framework.security.vo.ImageVO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 图片验证码
 * <p>  time 23/10/2020 17:10  星期五 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */
public class ImageVerification {

    private static final Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE, Color.CYAN };
    private static final int width = 90;
    private static final int height = 42;

    public static ImageVO generateImageVerificationCode(){

        ByteArrayOutputStream os = null;
        ImageVO imageVO = new ImageVO();
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.getColor("F8F8F8"));
            graphics.fillRect(0, 0, width, height);

            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < 10; i++) {
                graphics.setColor(colors[random.nextInt(colors.length)]);
                final int x = random.nextInt(width);
                final int y = random.nextInt(height);
                final int w = random.nextInt(width);
                final int h = random.nextInt(height);
                final int signA = random.nextBoolean() ? 1 : -1;
                final int signB = random.nextBoolean() ? 1 : -1;
                graphics.drawLine(x, y, x + w * signA, y + h * signB);
            }


            StringBuilder stringBuilder = new StringBuilder();

            graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
            for (int i = 0; i < 4; i++) {
                int temp = random.nextInt(26) + 97;
                String text = String.valueOf((char) temp);
                graphics.setColor(colors[random.nextInt(colors.length)]);
                graphics.drawString(text, i * (width / 4) + 7, height - (height / 3));
                stringBuilder.append(text);
            }


            graphics.dispose();

            os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            imageVO.setImageByte(os.toByteArray());
            imageVO.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageVO;



    }


}
