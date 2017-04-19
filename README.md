# HotXposed
这是一个关于xposed 热更新的demo

xposed每次都要重启，是不是烦得要死？

憋怕，窝来了。

详细的原理介绍文章在[这里](http://androidwing.net/index.php/211)

demo中，hook的app目标为:xposed_target.apk

动态加载的代码在hotfix项目中，修改完毕HotFix.java中的内容，运行 pushDex Task即可让最新的代码生效。

