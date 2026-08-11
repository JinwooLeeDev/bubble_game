package my_test.ch02;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap = new JLabel();
    private Player player;
    private boolean isPressed = false;

    public BubbleFrame() {
        initData();
        setInitLayout();
        addEventListener();
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
                System.out.println("keyCode : " + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (isPressed) return;
                        isPressed = true;
                        player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (isPressed) return;
                        isPressed = true;
                        player.right();
                        break;
                    case KeyEvent.VK_UP:
                        if (isPressed) return;
                        isPressed = true;
                        player.up();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (isPressed) return;
                        isPressed = true;
                        player.down();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("KeyReleased : " + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        isPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        isPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        isPressed = false;
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