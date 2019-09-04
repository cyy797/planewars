package com.cxy.runtime;

import com.cxy.base.BaseSprite;
import com.cxy.base.Drawable;
import com.cxy.base.Moveable;
import com.cxy.constant.FrameConstant;
import com.cxy.main.GameFrame;
import com.cxy.util.DataStore;
import com.cxy.util.ImageMap;

import java.awt.*;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private Image image2;

    private int type;

    private int speed = FrameConstant.GAME_SPEED * 4;

    public EnemyBullet() {
        this(0,0,1);
    }

    public EnemyBullet(int x, int y,int type) {
        super(x, y);
        this.image = ImageMap.get("epb01");
        this.image2 = ImageMap.get("epb02");
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {

        move();
        if (type == 1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }else if (type == 2){
            g.drawImage(image2,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }

    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }
    public void borderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void  collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
            if (plane.getRectangle().intersects(this.getRectangle())) {
                gameFrame.enemyBulletsList.remove(this);
                gameFrame.hp-=speed;
                if (gameFrame.hp<=0){
                    gameFrame.hp=0;
                    gameFrame.gameOver=true;
                }
            }
        }

    }

