package cn.com.qjun.dmsoft;

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
        dmSoft = new DmSoft("", "", ".");
    }

    @Test
    public void testBindWindow() {
        dmSoft.opsForWindow().enumProcess("navicat.exe");
    }
}
