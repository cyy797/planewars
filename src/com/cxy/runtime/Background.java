package com.cxy.runtime;

import com.cxy.base.BaseSprite;
import com.cxy.base.Drawable;
import com.cxy.base.Moveable;
import com.cxy.util.ImageMap;

import java.awt.*;

public class Background extends BaseSprite implements Moveable, Drawable {

    private Image image;

    public Background() {
        this(0,0, ImageMap.get("bg01"));
    }

    public Background(int x, int y, Image bgImage) {
        super(x, y);
        this.image = bgImage;
    }

    @Override
    public void move() {
        setY(getY()- 1);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
    }
}
