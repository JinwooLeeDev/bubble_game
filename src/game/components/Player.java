package game.components;

import game.BubbleFrame;
import game.interfaces.Moveable;
import game.state.PlayerWay;
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
    private final int JUMP_HEIGHT = 65;

    // 플레이의 움직임 상태
    @Setter
    private boolean left;
    @Setter
    private boolean right;
    private boolean up;
    @Setter
    private boolean down;

    // 플레이어의 벽 충돌 상태
    @Setter
    private boolean leftWallCrash;
    @Setter
    private boolean rightWallCrash;

    // 플레이어의 방향 상태
    private PlayerWay playerWay;
    // 왼쪽/ 오른쪽 이벤트가 들어오면 상태값 계속 변경해주면 된다.


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
        // 최초 방향상태 설정
        playerWay = PlayerWay.RIGHT;
    }

    private void setInitLayout() {
        setSize(50, 50);
        setLocation(x, y);
        setIcon(playerR);
    }


    @Override
    public void left() {
        left = true;

        playerWay = PlayerWay.LEFT;

        setIcon(playerL);
        new Thread(() -> {
            while (left) {
                x = x - SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void right() {
        right = true;

        playerWay = PlayerWay.RIGHT;

        setIcon(playerR);
        new Thread(() -> {
            while (right) {
                x = x + SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            for (int i = 0; i < JUMP_HEIGHT; i++) {
                y -= JUMP_SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(5); // 5ms (낙하 속도 보다는 느리게 설정)
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            up = false;
            down();
        }).start();
    }

    // TODO - 0  while 로 교체
    @Override
    public void down() {
        down = true;
        new Thread(() -> {
            while (down) {
                y += JUMP_SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            down = false;
        }).start();
    }

    // 물방울 발사
    public void fireBubble(BubbleFrame bubbleFrame) {
        Bubble bubble = new Bubble(this);
        // Player add() 아니라 상위 개념인 Frame 에 add 메서드를 호출 시켜야 한다.
        //add(bubble);
        bubbleFrame.add(bubble);
    }
}
