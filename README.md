# HotXposed
这是一个关于xposed 热更新的demo

xposed每次都要重启，是不是烦得要死？

憋怕，窝来了。
##  吐槽&起因

相信熟悉Xposed的小伙伴们都知道，每次写完Xposed都要重启啊！有木有！反射错了，写错了名字，改一个log，都要重启啊有木有！重启浪费时间啊有木有！一个字母导致一次重启！要命啊有木有！

所以就花时间想了想有木有不重启就立即生效的办法呢。


Xposed每次都需要重启才能生效，这里我并不知道具体原因，但是大题可以猜测为烧入系统的代码，所以如果代码有变动，必须经过重启。那么有没有只烧一次，就可以动态变动的方法呢？

## 原理

参考android里classloader实现，发现findClass()最后都是调用了DexFile来loadClass，那我们也可以偷懒，不包裹classloader，直接拿DexFile来动态加载我们的代码呀。


## 栗子
首先，准备hook一下我的zoomheader，在主界面onCreate()的时候，让他弹出一个Toast. (其实做什么都可以
，这里只是方便演示)


![](https://dn-mhke0kuv.qbox.me/a62b00e1d4f452ff2b6b)

此时，打开我的app，就可以弹出“哈哈”这个Toast
![](https://dn-mhke0kuv.qbox.me/e86eee5a45da7400f46f)

可是，这时候我发现“哈哈”其实写错了，我想写成"呵呵"，这时候我不得不修改“哈哈”为“呵呵”，然后重启，为了这一个字等上个三五六分钟。。


## 代码热加载，热更新

所以，这个时候，我们把**需要写的逻辑代码**单独放到一个dex里，然后使用DexFile加载，这时候虽然只烧了一次代码，但是这个烧了以后的代码可以根据动态的dex的代码来进行逻辑变化。

原理很简单，代码也很简单，只需要几句话。

![](https://dn-mhke0kuv.qbox.me/418d20c2b50d63bb2295)

这里为了展示原理，写了最简单的，这里去读取/sdcard/classes.dex文件，直接导入Hotfix类，调用他的invoke方法，由于弹出toast需要用到activity参数，所以把这个Activity传入即可。

![](https://dn-mhke0kuv.qbox.me/e129390b6b1f60f0dfcf)

HotFix代码如图：把Toast逻辑写在了里面。

这个时候，只要把hotfix的apk打包，然后提取dex，通过adb push到sdcard， 就可以实现热更新了！


## 自动化热更新
不过这样未免还是有些麻烦。所以当然要用gradle帮我们了。

这里要感谢下 [@小小张]() [@神来一巴掌]() [@pighead]()

观察application插件，有个叫做transformClassesWithDex的任务，他就是把class文件转化为dex的任务，这个时候，我们去hook这个任务，在他执行完毕以后，自动把dex push到/sdcard/中。


![](https://dn-mhke0kuv.qbox.me/4049ef505445c828da13)

写一个任务，叫做pushDex ，并且依赖于transformClassesWithDex。

![](https://dn-mhke0kuv.qbox.me/1241c868edcc298e10fc)

此时，只要修改完HotFix.java的代码，然后执行Task pushDex，代码就自动热更新完毕！

**判断adb路径代码 使用了[AppMethodOrder](https://github.com/zjw-swun/AppMethodOrder)的代码，感谢原作者 [@三斤-虹猫]()**


demo中，hook的app目标为:xposed_target.apk

动态加载的代码在hotfix项目中，修改完毕HotFix.java中的内容，运行 pushDex Task即可让最新的代码生效。

