package com.cxy.runtime;

import com.cxy.base.BaseSprite;
import com.cxy.base.Drawable;
import com.cxy.base.Moveable;
import com.cxy.constant.FrameConstant;
import com.cxy.main.GameFrame;
import com.cxy.util.DataStore;
import com.cxy.util.ImageMap;

import java.awt.*;

public class BloodProp extends BaseSprite implements Moveable,Drawable{
    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 2;

    public BloodProp() {
        this(0,0, ImageMap.get("blood"));
    }

    public BloodProp(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        borderTesting();
    }

    @Override
    public void move() {

        setY(getY()+speed);
    }


    public void borderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT ){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bloodPropList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),
                image.getWidth(null) ,
                image.getHeight(null)) ;
    }

    public void  collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bloodPropList.remove(this);
            if (gameFrame.hp<100) {
                gameFrame.hp++;
            }

        }
    }

}
