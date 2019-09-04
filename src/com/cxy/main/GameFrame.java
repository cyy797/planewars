package com.cxy.main;

import com.cxy.constant.FrameConstant;
import com.cxy.runtime.*;
import com.cxy.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    private Random random=new Random();


    //创建背景
    private Background background = new Background();

    //创建飞机对象
    private Plane plane = new Plane();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //敌方子弹集合
    public final List<EnemyBullet> enemyBulletsList = new CopyOnWriteArrayList<>();

    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //创建心集合
    public final List<BloodProp> bloodPropList = new CopyOnWriteArrayList<>();

    //创建幸运草集合
    public final List<LuckyProp> luckyPropList = new CopyOnWriteArrayList<>();

    public final Boss boss = new Boss();

    //创建boss子弹集合
    public final List<BossBullet> bossBulletList = new CopyOnWriteArrayList<>();

    public boolean gameOver = false;

    public boolean gameOver2 = false;

    public int hp = 50;

    public int hp1=100;


    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);

            plane.draw(g);

            boss.draw(g);

            //随机生成心a
            if (random.nextInt(1000)>985){
                bloodPropList.add(new BloodProp(random.nextInt
                        (FrameConstant.FRAME_WIDTH-ImageMap.get("blood").getWidth(null)),
                        30-ImageMap.get("blood").getHeight(null),
                        ImageMap.get("blood")));
            }

            //随机生成幸运草
            if (random.nextInt(1000)>993){
                luckyPropList.add(new LuckyProp(random.nextInt
                        (FrameConstant.FRAME_WIDTH-ImageMap.get("lucky").getWidth(null)),
                        30-ImageMap.get("lucky").getHeight(null),
                        ImageMap.get("lucky")));
            }

            //随机生成敌机1
            if (random.nextInt(1000)>985){
                enemyPlaneList.add(new EnemyPlane(random.nextInt
                        (700),
                        30-ImageMap.get("epb01").getHeight(null),
                        1));
            }
            //随机生成敌机2
            if (random.nextInt(1000)>985){
                enemyPlaneList.add(new EnemyPlane(random.nextInt
                        (FrameConstant.FRAME_WIDTH-ImageMap.get("ep02").getWidth(null)),
                        30,
                        2));
            }


            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);
            }

            for (EnemyBullet enemyBullet : enemyBulletsList) {
                enemyBullet.draw(g);
            }
            for (EnemyBullet enemyBullet : enemyBulletsList) {
                enemyBullet.collisionTesting(plane);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }

            for (BloodProp bloodProp : bloodPropList) {
                bloodProp.draw(g);
            }
            for (BloodProp bloodProp : bloodPropList) {
                bloodProp.collisionTesting(plane);
            }

            for (LuckyProp luckyProp : luckyPropList) {
                luckyProp.draw(g);
            }
            for (LuckyProp luckyProp : luckyPropList) {
                luckyProp.collisionTesting(plane);
            }

            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.draw(g);
            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.collisionTesting(plane);
            }

            g.setFont(new Font("楷体",Font.BOLD,28));
            g.setColor(new Color(200, 169, 57));
            g.drawString("得分"+ FrameConstant.score,0,60);

            g.setFont(new Font("楷体",Font.BOLD,28));
            g.setColor(Color.RED);
            g.drawString("HP:"+hp,0,100);


            g.setFont(new Font("楷体",Font.BOLD,28));
            g.setColor(Color.RED);
            g.drawString("Boss HP:"+hp1,500,100);

//        g.setColor(Color.RED);
//        g.drawString(""+bossBulletList.size(),300,100);

        }
        if (gameOver){
            g.setFont(new Font("楷体",Font.BOLD,55));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!",255,400);

        }
        if (gameOver2){
            g.setFont(new Font("楷体",Font.BOLD,55));
            g.setColor(Color.RED);
            g.drawString("WIN!",300,400);

        }
    }

    /**
     * 使用这个方法初始化窗口
     */

    public void init(){

        //设置好尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);

        //不让它启动窗口时有这个图片
        enableInputMethods(false);

        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });

        new Thread(){
            @Override
            public void run() {

                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

//        游戏初始时添加一些敌方飞机,
//        enemyPlaneList.add(new EnemyPlane(330,55,2));
//        enemyPlaneList.add(new EnemyPlane(600,0,1));
//        enemyPlaneList.add(new EnemyPlane(130,55,1));
//        enemyPlaneList.add(new EnemyPlane(88,-750,1));
//        enemyPlaneList.add(new EnemyPlane(600,-950,1));
//        enemyPlaneList.add(new EnemyPlane(160,-1080,1));
//        enemyPlaneList.add(new EnemyPlane(330,-1255,1));
//        enemyPlaneList.add(new EnemyPlane(600,-1855,1));
//        enemyPlaneList.add(new EnemyPlane(130,-2055,1));
//        enemyPlaneList.add(new EnemyPlane(88,-2555,1));


        //添加血量道具
/*
        bloodPropList.add(new BloodProp(500,280,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(100,0,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(680,-225,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(560,-950,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(100,-950,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(680,-1555,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(200,-1850,ImageMap.get("blood")));
        bloodPropList.add(new BloodProp(200,-2550,ImageMap.get("blood")));
*/

        setVisible(true);

        }

    public Image offScreenImage = null; //创建缓冲区

    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        }
        Graphics goff = offScreenImage.getGraphics();

        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }

}
