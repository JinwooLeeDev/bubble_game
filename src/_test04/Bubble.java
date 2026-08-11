package _test04;

import lombok.Getter;

import javax.swing.*;



@Getter
public class Bubble extends JLabel {
    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon;

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
}
