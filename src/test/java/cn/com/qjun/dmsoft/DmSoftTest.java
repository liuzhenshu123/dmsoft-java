package cn.com.qjun.dmsoft;

import cn.com.qjun.dmsoft.plugin.DmSoft;
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
        dmSoft = new DmSoft("jv965720b239b8396b1b7df8b768c919e86e10f", "jzv1plmlhlhkpt7", ".");
    }

    @Test
    public void testBindWindow() {
        dmSoft.opsForWindow().enumProcess("navicat.exe");
    }
}
