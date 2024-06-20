package cn.com.qjun.dmsoft.windows.soft.wx;

import cn.com.qjun.dmsoft.domain.FindResult;
import cn.com.qjun.dmsoft.domain.Point;
import cn.com.qjun.dmsoft.domain.Rect;
import cn.com.qjun.dmsoft.windows.index.IndexForm;

import java.awt.event.ActionEvent;
import java.util.Collections;

public class WXTestFunctions {
    /**
     * 鼠标移到微信界面红心
     */
    public static boolean mouse_to_redheart(long hwnd) {
        System.out.println("点击了——鼠标移到微信界面红心,hwnd:"+hwnd);
        if (2 == 1) {
            String dict = IndexForm.dmSoft.opsForOcr().getDict(0, 0);
            int nowDict = IndexForm.dmSoft.opsForOcr().getNowDict();
//            IndexForm.dmSoft.opsForOcr().dict
            System.out.println("当前字库："+nowDict+"    字库信息:" + dict);
            return true;
        }
        Rect windowRect = IndexForm.dmSoft.opsForWindow().getWindowRect(hwnd);
        System.out.println("windowRect:"+windowRect.toString());
        FindResult str = IndexForm.dmSoft.opsForOcr().findStr(windowRect, Collections.singletonList("红心"), "e81224-202020", 0.9);
//        FindResult str = IndexForm.dmSoft.opsForOcr().findStr(Rect.of(-115,529,585,1029), Collections.singletonList("红心"), "e81224-202020", 0.1);
        System.out.println("FindResult:"+str);
        if (str!=null) {
            int x = str.getPoint().getX();
            int y = str.getPoint().getY();
            System.out.println("坐标：(" + x + "," + y + ")");
            IndexForm.dmSoft.opsForInput().moveTo(Point.of(x, y));
        }
//        IndexForm.dmSoft.opsForColour().findPic(Rect.of(0,0,1080,1000), Collections.singletonList("396044l"), "0,0,0,0", 0.9, FindDirection.DOWN)
        return true;
    }

    /**
     * 鼠标移到微信界面君君字体
     */
    public static boolean mouse_to_junjun(long hwnd) {
        System.out.println("点击了——鼠标移到微信界面君君字体,hwnd:"+hwnd);
        if (2 == 1) {
            String dict = IndexForm.dmSoft.opsForOcr().getDict(0, 0);
            int nowDict = IndexForm.dmSoft.opsForOcr().getNowDict();
//            IndexForm.dmSoft.opsForOcr().dict
            System.out.println("当前字库："+nowDict+"    字库信息:" + dict);
            return true;
        }
        Rect windowRect = IndexForm.dmSoft.opsForWindow().getWindowRect(hwnd);
        System.out.println("windowRect:"+windowRect.toString());
        FindResult str = IndexForm.dmSoft.opsForOcr().findStr(windowRect, Collections.singletonList("君君"), "000000-202020|a4a5a6-202020|606161-202020|a4a5a6-202020", 0.8);
        System.out.println("FindResult:"+str);
        if (str!=null) {
            int x = str.getPoint().getX();
            int y = str.getPoint().getY();
            System.out.println("坐标：(" + x + "," + y + ")");
            IndexForm.dmSoft.opsForInput().moveTo(Point.of(x, y));
        }
//        IndexForm.dmSoft.opsForColour().findPic(Rect.of(0,0,1080,1000), Collections.singletonList("396044l"), "0,0,0,0", 0.9, FindDirection.DOWN)
        return true;
    }

}
