package com.cxy.runtime;

import com.cxy.base.BaseSprite;
import com.cxy.base.Drawable;
import com.cxy.base.Moveable;
import com.cxy.constant.FrameConstant;
import com.cxy.main.GameFrame;
import com.cxy.util.DataStore;
import com.cxy.util.ImageMap;

import java.awt.*;

public class BossBullet extends BaseSprite implements Moveable, Drawable {
    private Image image;

    private int speed = FrameConstant.GAME_SPEED*3;

    public BossBullet() {
        this(0,0, ImageMap.get("bob01"));
    }

    public BossBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }

    @Override
    public void move() {

        setY(getY()+speed);
    }

    public void borderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bossBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),
                image.getWidth(null),image.getHeight(null));
    }

    public void  collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bossBulletList.remove(this);
            gameFrame.hp-=speed*3;
            if (gameFrame.hp<=0){
                gameFrame.hp=0;
                gameFrame.gameOver=true;
            }
        }
    }
}
