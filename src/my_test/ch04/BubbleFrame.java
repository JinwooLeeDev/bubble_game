package my_test.ch04;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap = new JLabel();
    private Player player;

    public BubbleFrame() {
        initData();
        setInitLayout();
        addEventListener();

        // 플레이어의 위치에 따라 픽셀 감지하는 백그라운드 서비스 객체 생성.
        new Thread(new BackgroundPlayerService(player)).start();
        new BackgroundPlayerService(player);
    }

    private void initData() {
        setTitle("버블버블");
        setSize(1000, 640); // 상수 사용 권장하지만 일단은 숫자 쓰는걸로
        setDefaultCloseOperation(3);
        backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
        setContentPane(backgroundMap);  // 루트 패널에 JLabel(backgroundMap) 넣기
        player = new Player();
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);    // JFrame을 화면 가운데 배치

        add(player);
        setVisible(true);
    }

    private void addEventListener() {
        // 프레임에 키보드 리스너 등록하기
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        // 이미 왼쪽으로 이동중이면 무시 (스레드 중복 생성 방지)
                        if (!player.isLeft() && !player.isLeftWallCrash()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight() && !player.isRightWallCrash()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        // 점프 중이거나 낙하중이면 무시 (이중 점프 방지)
                        if (!player.isUp() && !player.isDown()) {
                            player.up();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        player.down();
                        break;
                    case KeyEvent.VK_SPACE:
                        player.fireBubble(BubbleFrame.this);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_UP:

                        break;
                    case KeyEvent.VK_DOWN:

                        break;
                }
            }
        });
    }

    // 테스트 코드 작성
    public static void main(String[] args) {
        new BubbleFrame();
    }

}