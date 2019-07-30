package com.cxy.main;

import com.cxy.constant.FrameConstant;
import com.cxy.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {

    private Background background = new Background();

    @Override
    public void paint(Graphics g) {
        background.draw(g);
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
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
