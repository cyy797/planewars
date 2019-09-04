package com.cxy.runtime;

import com.cxy.base.BaseSprite;
import com.cxy.base.Drawable;
import com.cxy.base.Moveable;
import com.cxy.constant.FrameConstant;
import com.cxy.main.GameFrame;
import com.cxy.util.DataStore;
import com.cxy.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Moveable, Drawable {

    private int speed = FrameConstant.GAME_SPEED * 3;

    private List<Image> imageList = new ArrayList<>();

    private Random random = new Random();


    public Boss() {
        for (int i = 1; i < 6; i++) {
            imageList.add(ImageMap.get("boss" + i));
        }
    }

    public Boss(int x, int y) {
        super(x, y);

    }

    int index = 0;

    @Override
    public void draw(Graphics g) {
        collisionTesting();
            if (FrameConstant.score>99){
        g.drawImage(imageList.get(index++ / 3), getX(), 50,
                imageList.get(0).getWidth(null) / 2,
                imageList.get(0).getHeight(null) / 2,
                null);
        move();
        borderTesting();

        fire();
        if (index >= 15) {
            index = 0;
        }
            }

    }

    private boolean right = true;

    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
        } else {
            setX(getX() - speed);
        }
    }

    public void borderTesting() {
        if (getX() + imageList.get(0).getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            right = false;
        } else if (getX() < 0) {
            right = true;
        }
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 980) {
            gameFrame.bossBulletList.add(new BossBullet(
                    getX() + imageList.get(0).getWidth(null) / 2 - ImageMap.get("bob01").getWidth(null) / 2,
                    getY() + imageList.get(0).getHeight(null),
                    ImageMap.get("bob01")));

        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(),
                imageList.get(0).getWidth(null) / 2, imageList.get(0).getHeight(null) / 2);
    }

    public void collisionTesting() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        List<Bullet> bulletList = gameFrame.bulletList;
        for (Bullet bullet : bulletList) {
            if (bullet.getRectangle().intersects(this.getRectangle())) {
                gameFrame.hp1 -= 3;
                FrameConstant.score += 10;
                gameFrame.bulletList.remove(bullet);
            }
            if (gameFrame.hp1 <= 0) {
                gameFrame.hp1 = 0;
                gameFrame.gameOver2 = true;
            }
        }

    }

}
