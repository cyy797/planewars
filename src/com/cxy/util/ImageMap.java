package com.cxy.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static{
        //背景
        map.put("bg01",ImageUtil.getImage("com\\cxy\\imgs\\bg\\bg2.jpg"));

        //我方飞机
        map.put("my01",ImageUtil.getImage("com\\cxy\\imgs\\plane\\my_1.png"));

        //我方子弹
        map.put("myb01",ImageUtil.getImage("com\\cxy\\imgs\\bullet\\myb_1.png"));
        map.put("myb02",ImageUtil.getImage("com\\cxy\\imgs\\bullet\\myb_2.png"));
        map.put("myb03",ImageUtil.getImage("com\\cxy\\imgs\\bullet\\myb_3.png"));
        //敌机
        map.put("ep01",ImageUtil.getImage("com\\cxy\\imgs\\plane\\ep_1.png"));
        map.put("ep02",ImageUtil.getImage("com\\cxy\\imgs\\plane\\ep_2.png"));

        //敌机子弹
        map.put("epb01",ImageUtil.getImage("com\\cxy\\imgs\\bullet\\epb_1.png"));
        map.put("epb02",ImageUtil.getImage("com\\cxy\\imgs\\bullet\\epb_2.png"));

        //心
        map.put("blood",ImageUtil.getImage("com\\cxy\\imgs\\prop\\blood.png"));
        //幸运草
        map.put("lucky",ImageUtil.getImage("com\\cxy\\imgs\\prop\\lucky.png"));

        //boss
        map.put("boss1",ImageUtil.getImage("com\\cxy\\imgs\\boss\\BOSS02_1.png"));
        map.put("boss2",ImageUtil.getImage("com\\cxy\\imgs\\boss\\BOSS02_2.png"));
        map.put("boss3",ImageUtil.getImage("com\\cxy\\imgs\\boss\\BOSS02_3.png"));
        map.put("boss4",ImageUtil.getImage("com\\cxy\\imgs\\boss\\BOSS02_4.png"));
        map.put("boss5",ImageUtil.getImage("com\\cxy\\imgs\\boss\\BOSS02_5.png"));

        //boss子弹
        map.put("bob01",ImageUtil.getImage("com\\cxy\\imgs\\bullet\\bob_1.png"));
    }
    public static Image get(String key){

        return map.get(key);
    }
}
