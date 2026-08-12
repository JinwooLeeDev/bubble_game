package my_test.ch04;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;


@Setter
@Getter
public class Bubble extends JLabel implements Moveable{
    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon;

    @Setter
    private boolean flag = true;

    // 버블의 움직임 속도
    private final int SPEED = 4;

    // 버블의 벽 충돌 상태
    @Setter
    private boolean leftWallCrash;
    @Setter
    private boolean rightWallCrash;

    // 버블의 움직임 상태
    @Setter
    private boolean left;
    @Setter
    private boolean right;
    @Setter
    private boolean up;
    @Setter
    private boolean down;

    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();
    }

    private void initData() {
        bubbleIcon = new ImageIcon("images/bubble.png");
    }
    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setLocation(x, y);
        setSize(50,50);
        setIcon(bubbleIcon);
    }

    @Override
    public void left() {
        left = true;
        new Thread(() -> {
            while(flag) {
                x -= SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                up();
            }
        }).start();
    }

    @Override
    public void right() {
        right = true;
        new Thread(() -> {
            while(flag) {
                x += SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                up();
            }
        }).start();
    }

    @Override
    public void up() {
        if (up) {
            flag = false;
            new Thread(() -> {
                while (true) {
                    y -= SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }

    @Override
    public void down() {

    }
}
