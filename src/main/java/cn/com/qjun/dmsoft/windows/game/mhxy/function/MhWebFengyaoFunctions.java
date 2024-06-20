package cn.com.qjun.dmsoft.windows.game.mhxy.function;

import cn.com.qjun.dmsoft.domain.FindResult;
import cn.com.qjun.dmsoft.domain.Point;
import cn.com.qjun.dmsoft.domain.Rect;
import cn.com.qjun.dmsoft.enums.FindDirection;
import cn.com.qjun.dmsoft.windows.index.IndexForm;

import java.util.Collections;
import java.util.List;

public class MhWebFengyaoFunctions {
    /**
     * 点击主页
     */
    public static boolean clickHome(long hwnd) {
        Rect windowRect = IndexForm.dmSoft.opsForWindow().getWindowRect(hwnd);
        System.out.println("windowRect:"+windowRect.toString());
        FindResult findResult = IndexForm.dmSoft.opsForOcr().findStr(windowRect, Collections.singletonList("主页"), "0ef0e9-202020", 0.5);
        System.out.println("FindResult:"+findResult);
        if (findResult != null) {
            int x = findResult.getPoint().getX();
            int y = findResult.getPoint().getY();
            IndexForm.dmSoft.opsForInput().moveTo(Point.of(x, y));
            IndexForm.dmSoft.opsForInput().leftClick();
        }
        return true;
    }

    /**
     * 点击封妖图标
     *
     * @param hwnd
     * @return
     */
    public static boolean clickFengYaoHome(long hwnd) {
        Rect windowRect = IndexForm.dmSoft.opsForWindow().getWindowRect(hwnd);
        FindResult findResult = IndexForm.dmSoft.opsForColour().findPic(windowRect, Collections.singletonList("fengyao_home.bmp"), "", 0.7, FindDirection.L_TO_R_AND_B_TO_T);
        if (findResult != null) {
            int x = findResult.getPoint().getX();
            int y = findResult.getPoint().getY();
            IndexForm.dmSoft.opsForInput().moveTo(Point.of(x, y));
            IndexForm.dmSoft.opsForInput().leftClick();
        }
        return true;
    }

    /**
     * 点击前往封印（开始封妖）
     *
     * @param hwnd
     * @return
     */
    public static boolean clickFengYaoStart(long hwnd) {
        Rect windowRect = IndexForm.dmSoft.opsForWindow().getWindowRect(hwnd);
//        FindResult findResult = IndexForm.dmSoft.opsForColour().findPic(windowRect, Collections.singletonList("qianwangfengyin.bmp"), "", 0.9, FindDirection.L_TO_R_AND_T_TO_B);
        List<FindResult> findResultList = IndexForm.dmSoft.opsForColour().findPicEx(windowRect, Collections.singletonList("qianwangfengyin2.bmp"), "", 0.9, FindDirection.L_TO_R_AND_T_TO_B);
        if (findResultList != null && findResultList.size() > 0) {
            for (FindResult findResult : findResultList) {
                if (findResult != null) {
                    int x = findResult.getPoint().getX();
                    int y = findResult.getPoint().getY();
                    IndexForm.dmSoft.opsForInput().moveTo(Point.of(x, y));
                    IndexForm.dmSoft.opsForInput().leftClick();
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

}
