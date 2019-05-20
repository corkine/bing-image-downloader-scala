package com.mazhangjing.wallpaper;

import org.junit.Test;
import scala.Option;

import java.io.File;
import java.io.IOException;

public class BingImageUtilsTest {

    @Test public void  test() throws IOException {
        Option<String> bingTodayImageUrl = BingImageUtils.getBingTodayImageUrl(0);
        assert (!bingTodayImageUrl.isEmpty());
        System.out.println("bingTodayImageUrl = " + bingTodayImageUrl.get());
        String res = bingTodayImageUrl.get();
        Option<File> imageFromFile = BingImageUtils.getImageFromFile(res, new File("result.jpeg"));
        assert (!imageFromFile.isEmpty());
        File file = imageFromFile.get();
        assert (file.exists());
    }

}