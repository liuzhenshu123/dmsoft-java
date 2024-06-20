package cn.com.qjun.dmsoft;

import cn.com.qjun.dmsoft.domain.Point;
import cn.com.qjun.dmsoft.operations.DmOptions;
import cn.com.qjun.dmsoft.operations.DmSoft;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author RenQiang
 * @date 2024/2/11
 */
public class DmSoftTest {
    private static DmSoft dmSoft;

    @BeforeAll
    public static void init() {
        dmSoft = new DmSoft(DmOptions.builder("E:\\IdeaWorkSpace\\dmsoft-java\\data", "jv965720b239b8396b1b7df8b768c919e86e10f", "jzv1plmlhlhkpt7").build());
    }



    @Test
    public void testBindWindow() {
        // // 消息按钮
        //                FeatureMatching.<FeatureMultiColor>builder()
        //                        .matchingArea(Area.of(27, 261, 38, 265))
        //                        .matchingFeature(new FeatureMultiColor("bdb495-101010", "2|0|c3ba9d-101010,4|0|c6bca5-101010,6|0|c4baa2-101010,8|0|c9bfa7", 0.9))
        //                        .build(),
        //                // 红蓝条上的龙
        //                FeatureMatching.<FeatureMultiColor>builder()
        //                        .matchingArea(Area.of(475, 437, 483, 443))
        //                        .matchingFeature(new FeatureMultiColor("d4c8b3-101010", "0|2|94886b-101010,2|0|8c846c-101010,2|2|92886e-101010,4|0|988e7c-101010,4|2|6f644a", 0.9))
        //                        .build(),
        //                // 攻击按钮
        //                FeatureMatching.<FeaturePicture>builder()
        //                        .matchingArea(Area.of(879, 430, 903, 454).expand(1))
        //                        .matchingFeature(new FeaturePicture("攻击按钮.bmp", "101010", 0.9))
        //                        .build());

        //获取窗口句柄
        //通过进程ID 你可以使用GetWindowThreadProcessId函数来获取与进程关联的线程ID，然后用AttachThreadInput和GetWindow来找到相关窗口。
//        WindowsUtils windowsUtils = new WindowsUtils();
//        windowsUtils.getWindowThreadProcessId()

        //通过名字来获取窗口句柄
//        WindowsUtils windowsUtils = new WindowsUtils();
//        long hwnd=windowsUtils.findWindowByTitle("Navicat Premium");
//        dmSoft.opsForBackground().bindWindow(462174L, DisplayMode.DX, MouseMode.DX, KeypadMode.DX, 0);
//        dmSoft.opsForBackground().bindWindow(1315894l, DisplayMode.DX, MouseMode.DX, KeypadMode.DX, 0);
//        Point point = dmSoft.opsForColour().findMultiColor(Rect.of(0, 0, 805, 805), "efeded", "-4|7|e8b8bb,8|7|db5860,3|12|db5860,0|7|db5860", 0.5, FindDirection.L_TO_R_AND_B_TO_T);
//        System.out.println(point);
//        FindResult findResult = dmSoft.opsForColour().findPic(Rect.of(0, 0, 805, 805), Collections.singletonList("396044l"), "0000ff", 0.5, FindDirection.L_TO_R_AND_B_TO_T);
//        System.out.println(findResult);
//        dmSoft.opsForColour().captureJpg(Rect.of(0, 0, 805, 805),"D:1test",50);

        while (true) {
            try {
                Thread.sleep(20000);

//            System.out.println(dmSoft.opsForInput().getCursorPos());
                System.out.println(dmSoft.opsForInput().getMouseSpeed());
                dmSoft.opsForInput().moveTo(Point.of(55, 20));
                Thread.sleep(1000);
//                dmSoft.opsForInput().leftClick();
                dmSoft.opsForInput().rightClick();
                Thread.sleep(1000);
//                dmSoft.opsForInput().leftDown();
                Thread.sleep(1000);
                dmSoft.opsForInput().moveTo(Point.of(1000, 500));
                Thread.sleep(1000);
                dmSoft.opsForInput().rightClick();
//                dmSoft.opsForInput().keyDown(48);
                Thread.sleep(1000);
//                dmSoft.opsForInput().keyDown(48);
                Thread.sleep(1000);
//                dmSoft.opsForInput().keyDown(48);
                Thread.sleep(1000);
//                dmSoft.opsForInput().leftDoubleClick();
                Thread.sleep(1000);
//                dmSoft.opsForInput().moveR(1010, 510);
//                dmSoft.opsForInput().rightClick();
                Thread.sleep(1000);
//                dmSoft.opsForInput().rightClick();
                Thread.sleep(1000);
//                dmSoft.opsForInput().moveR(1050, 600);
//                dmSoft.opsForInput().rightClick();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
