# Bing Image Downloader

## 简介

自动下载每日 Bing 封面壁纸的命令行程序，分辨率 1920 * 1080，地区为中国。

这个东西其实可以用很多已经写好的带有 GUI 的 Mac app 程序来做，但是那些程序一般界面都很丑，逻辑简单，只能拉伸作为桌面。而我想要用来将壁纸作为待机壁纸。因为 MacOS 有很好的对于一个文件夹中图片作为待机壁纸的支持（Windows 也是），因此这个程序只用简单的从 API 下载壁纸到某个文件夹即可。MacOS 和 Windows 以及 Ubuntu 都适用。

## 编译

编译代码，执行主类 com.mazhangjing.wallpaper.BingImageRunner 即可运行，第一个参数为下载壁纸的日期，今天为 0，昨天为 1，前天为 2，依次类推。第二个参数为下载到的路径，不确定是否支持 Windows 风格路径。

```bash
java -cp xxx.jar com.mazhangjing.wallpaper.BingImageRunner 0 /User/xxx/wallpaper
```

上述代码下载当日壁纸到 wallpaper 文件夹。

## 直接运行

在 /dist 目录下提供了二级制文件，可直接运行：

```bash
java -jar downloader.jar
```

如果不指定参数，自动下载当日壁纸到当前工作文件夹。

程序采用 Scala + fastJson + commonIO + httpClient 实现，单元测试使用 JUNIT，日志记录使用  Slf4J 以及 Logback。

## 尾巴

可以直接将此命令放入 sh 脚本，chmod 755 后，在 MacOS 的启动项中添加启动即可。之后指定壁纸为指定文件夹中图片，每天就会使用最新的壁纸作为当日锁屏和休眠图片了。