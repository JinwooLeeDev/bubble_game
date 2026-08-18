package _test06;

import lombok.Getter;

import javax.swing.*;


@Getter
public class Bubble extends JLabel implements Moveable {
    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon;
    private ImageIcon bombIcon;
    private BackgroundBubbleService backgroundBubbleService;


    // 버블 이동 상태 플래스
    private static final int HORIZONTAL_DISTANCE = 400;     // 버블의 수평 이동 거리
    private static final int BUBBLE_SPEED = 30;     // 이동 간격 (ms)
    private static final int SCREEN_TOP = 0;    // 화면 상단 경계 (y값)
    private static final int BUBBLE_SPEED_MS = 1;

    private boolean leftMoving;
    private boolean rightMoving;
    private boolean upMoving;


    public Bubble(Player player) {
        this.player = player;
        this.backgroundBubbleService = new BackgroundBubbleService(this);
        initData();
        setInitLayout();
        // 만약 이 시점에 플레이어의 방향 상태를 내가 알고 있다면
        // if 문을 사용해서 left(), right() 메서드를 분기하면 될 수 있을거 같은데 ?!
        if (player.getPlayerWay() == PlayerWay.LEFT) {
            new Thread(() -> {left();}).start();
        } else if (player.getPlayerWay()== PlayerWay.RIGHT) {
            new Thread(() -> {right();}).start();
        }
    }

    private void initData() {
        bubbleIcon = new ImageIcon("images/bubble.png");
        bombIcon = new ImageIcon("C:\\ljw\\workspace\\bubble\\bubble_game\\images\\bomb.png");
    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setLocation(x, y);
        setSize(50, 50);
        setIcon(bubbleIcon);
    }

    @Override
    public void left() {
        leftMoving = true;
        for(int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            if (backgroundBubbleService.leftWall()) break;
            x--;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        leftMoving = false;
        up();
    }

    @Override
    public void right() {
        rightMoving = true;
        for(int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            if (backgroundBubbleService.rightWall()) {
                // true 가 넘어오면 오른쪽 벽에 박은거임
                break;
            }
            x++;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        rightMoving = false;
        up();
    }

    @Override
    public void up() {
        upMoving = true;
        while (y > SCREEN_TOP) {
            if (backgroundBubbleService.topWall()) break;
            y-=5;
            setLocation(x,y);
            try {
                Thread.sleep(BUBBLE_SPEED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        upMoving = false;
        explode();
    }

    public void explode() {
        try {
            Thread.sleep(3000);
            setIcon(bombIcon);
            Thread.sleep(1000);
            // 부모 컴포넌트에서 제거
            if (getParent() != null) {
                this.setVisible(false);
                getParent().remove(this);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
