package cn.com.qjun.dmsoft.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 矩形区域
 *
 * @author RenQiang
 * @date 2024/2/11
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rect {
    /**
     * X坐标
     */
    @Getter(AccessLevel.PRIVATE)
    private int x;
    /**
     * Y坐标
     */
    @Getter(AccessLevel.PRIVATE)
    private int y;
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;

    /**
     * 根据坐标和大小创建实例
     *
     * @param x      X坐标
     * @param y      Y坐标
     * @param width  宽度
     * @param height 高度
     * @return 矩形区域
     */
    public static Rect newInstance(int x, int y, int width, int height) {
        return new Rect(x, y, width, height);
    }

    /**
     * 根据左上角和右下角坐标构建一个矩形区域
     *
     * @param x1 左上角X坐标
     * @param y1 左上角Y坐标
     * @param x2 右下角X坐标
     * @param y2 右下角Y坐标
     * @return 矩形区域
     */
    public static Rect of(int x1, int y1, int x2, int y2) {
        return new Rect(x1, y1, x2 - x1, y2 - y1);
    }

    /**
     * 根据坐标和尺寸构建一个矩形区域
     *
     * @param point 坐标
     * @param size  大小
     * @return 矩形区域
     */
    public static Rect of(Point point, Size size) {
        return new Rect(point.getX(), point.getY(), size.getWidth(), size.getHeight());
    }

    public int getX1() {
        return x;
    }

    public int getY1() {
        return y;
    }

    public int getX2() {
        return x + width;
    }

    public int getY2() {
        return y + height;
    }

    public Point getPoint() {
        return Point.of(x, y);
    }

    public Size getSize() {
        return Size.of(width, height);
    }
}
