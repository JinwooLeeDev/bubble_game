package _test06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 물방울 출동 감지 서비스
 * <p>
 * 일반 클래스로 설계 (Thread 없음)
 * 버블 하나 생성할 때마다 이미 스레드 생성 중에 있음
 * 즉 100 버블 생성 기본적으로 백개 스레드 생성 중 (추가로 또 스레드로 설계하면 너무 과부화)
 */
public class BackgroundBubbleService {
    private BufferedImage image;
    private Bubble bubble;

    public BackgroundBubbleService(Bubble bubble) {
        this.bubble = bubble;
        try {
            image = ImageIO.read(new File("C:\\ljw\\workspace\\bubble\\bubble_game\\images\\backgroundMapService.png"));
        } catch (IOException e) {
            System.err.println("해당 경로의 이미지를 찾을 수 없음");
        }
    }

    // 오른쪽 벽 충돌 감지
    public boolean rightWall() {
        Color rightColor = new Color(image.getRGB(bubble.getX() + 60, bubble.getY()+25));
        return isRed(rightColor);
    }
    // 왼쪽 벽 충돌 감지
    public boolean leftWall() {
        Color leftColor = new Color(image.getRGB(bubble.getX() + 10, bubble.getY()+25));
        return isRed(leftColor);
    }
    // 천장 충돌 감지
    public boolean topWall() {
        Color topColor = new Color(image.getRGB(bubble.getX() + 35, bubble.getY()));
        return isRed(topColor);
    }

    private boolean isRed(Color color) {
        // R G B
        return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0;
    }
}
