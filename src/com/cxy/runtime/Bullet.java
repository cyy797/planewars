package com.cxy.runtime;

import com.cxy.base.BaseSprite;
import com.cxy.base.Drawable;
import com.cxy.base.Moveable;
import com.cxy.constant.FrameConstant;
import com.cxy.main.GameFrame;
import com.cxy.util.DataStore;
import com.cxy.util.ImageMap;
import java.awt.*;
import java.util.List;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private Image image2;

    private Image image3;

    private int type;

    private int speed = FrameConstant.GAME_SPEED * 6;

    public Bullet() {

        this(0, 0, 1);
    }

    public Bullet(int x, int y, int type) {
        super(x, y);
        this.image = ImageMap.get("myb01");
        this.image2 = ImageMap.get("myb02");
        this.image3 = ImageMap.get("myb03");
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        }
        if (type == 2) {
            g.drawImage(image2, getX(), getY(), image.getWidth(null)*2, image.getHeight(null), null);
        } else if (type == 3) {
            g.drawImage(image3, getX(), getY(), image.getWidth(null)*3, image.getHeight(null), null);
        }
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    public void borderTesting() {

        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                enemyPlaneList.remove(enemyPlane);
                gameFrame.bulletList.remove(this);
                FrameConstant.score += enemyPlane.getType() * 3;
            }
        }
    }




}
