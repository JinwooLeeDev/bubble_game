package my_test.ch04;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundBubbleService implements Runnable {

    private Bubble bubble;
    private BufferedImage image;


    public BackgroundBubbleService(Bubble bubble) {
        this.bubble = bubble;
        try {
            image = ImageIO.read(new File("images/backgroundMapservice.png"));
        } catch (IOException e) {
            System.out.println("이미지 경로 및 파일명을 확인하세요");
        }
    }

    @Override
    public void run() {
        while (true) {
            if (bubble != null) {
                Color bubbleLeftColor = new Color(image.getRGB(bubble.getX() + 5, bubble.getY() + 25));
                Color bubbleRightColor = new Color(image.getRGB(bubble.getX() + 30, bubble.getY() + 25));

//                System.out.println("left Color : " + bubbleLeftColor);
//                System.out.println("right Color : " + bubbleRightColor);

                // 버블 벽면 충격 감지 //
                if (isRed(bubbleLeftColor)) {
                    bubble.setLeftWallCrash(true);
                    bubble.setUp(true);
                } else {
                    bubble.setLeftWallCrash(false);
                }

                if (isRed(bubbleRightColor)) {
                    bubble.setRightWallCrash(true);
                    bubble.setUp(true);
                } else {
                    bubble.setRightWallCrash(false);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private boolean isRed(Color color) {
        return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0;
    }
}
