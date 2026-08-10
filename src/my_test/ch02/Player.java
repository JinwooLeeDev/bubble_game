package my_test.ch02;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter

public class Player extends JLabel implements Moveable {

    private int x;
    private int y;

    private ImageIcon playerR;
    private ImageIcon playerL;

    // 플레이어 속도 상태
    private final int SPEED = 4;
    private final int JUMP_SPEED = 2;

    // Setter
    // 플레이어의 움직임 상태
    @Setter
    private boolean left;
    @Setter
    private boolean right;
    private boolean up;
    private boolean down;

    public Player() {
        initData();
        setInitLayout();
    }

    private void initData() {
        x = 55;
        y = 535;

        // 명시적으로 할당
        left = false;
        right = false;
        up = false;
        down = false;

        playerR = new ImageIcon("images/playerR.png");
        playerL = new ImageIcon("images/playerL.png");

    }

    private void setInitLayout() {
        setSize(50, 50);
        setLocation(x, y);
        setIcon(playerR);
        right = true;
    }

    @Override
    public void left() {
        left = true;
        setIcon(playerL);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (left) {
                    x -= SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void right() {
        right = true;
        setIcon(playerR);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (right) {
                    x += SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    // 점프 기능 구현해보기

    @Override
    public void up() {
        up = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<=25; i++) {
                    y -= SPEED;
                    setLocation(x,y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i=0; i<=25; i++) {
                    y += SPEED;
                    setLocation(x,y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void down() {

    }
}

