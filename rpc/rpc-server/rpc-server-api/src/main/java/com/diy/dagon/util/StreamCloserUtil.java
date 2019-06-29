package com.diy.dagon.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Dagon on 2019/6/29 - 9:00
 */
public class StreamCloserUtil {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                System.err.println("关闭操作失败：" + closeable);
                e.printStackTrace();
            }
        }
    }
}
