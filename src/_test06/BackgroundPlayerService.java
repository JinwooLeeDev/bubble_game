package _test06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// 클래스 역할 : 플레이어의 벽 충돌 감시 서비스 (백드라운드에서 계속 돌아감)
// 메인 스레드는 너무 바쁘다. (처리 해야할게 많음)
public class BackgroundPlayerService implements Runnable {

    // Image / ImageIcon : 좌표값으로 현재 픽셀값을 추출을 못한다.
    // 메모리에 픽셀 배열로 저장된 이미지
    // getRGB(x, y)로 특정 좌표의 색상값을 직접 읽을 수 있음
    private BufferedImage image;
    private Player player;

    // 생성자 주입 (DI Dependency Injection)
    public BackgroundPlayerService(Player player) {
        this.player = player;

        try {
            image = ImageIO.read(new File("images/backgroundMapservice.png"));
        } catch (IOException e) {
            System.out.println("이미지 경로 및 파일명을 확인하세요");
        }
    }


    @Override
    public void run() {
        // 게임이 종료 될 때까지 계속 실행 예정
        while (true) {
            // x = 55;
            // y = 535;
            Color leftColor = new Color(image.getRGB(player.getX()+5, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY()+ 25));

            // System.out.println("left Color : " + leftColor);
            // System.out.println("right Color : " + rightColor);

            if (isRed(leftColor)) {
                // 현재 플레이어 왼쪽 벽에 충돌된 상태
                player.setLeftWallCrash(true);
                player.setLeft(false);
            } else {
                player.setLeftWallCrash(false);
            }

            if (isRed(rightColor)) {
                player.setRightWallCrash(true);
                player.setRight(false);
            } else {
                player.setRightWallCrash(false);
            }
            // 왼쪽 벽 감지 판단 - 빨간색 이라면
            // 플레이어가 왼쪽 충돌
            // 오른쪽 벽 감지 판단 - 빨간색 일라면

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isRed(Color color) {
        return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0;
    }

}
