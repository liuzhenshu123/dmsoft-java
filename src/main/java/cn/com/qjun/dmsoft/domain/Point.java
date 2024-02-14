package cn.com.qjun.dmsoft.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 点
 *
 * @author RenQiang
 * @date 2024/2/11
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {
    /**
     * X坐标
     */
    private int x;
    /**
     * Y坐标
     */
    private int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
