## HotXposed:

[![](https://jitpack.io/v/githubwing/HotXposed.svg)](https://jitpack.io/#githubwing/HotXposed)

## Useage


### Step 1

compile the library in your build.gralde

```
gradle

allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

dependencies {
	        compile 'com.github.githubwing:HotXposed:v1.0.0'
}

```

### Step 2

Implement IHookerDispatcher interface And call HotXposed.hook(YourClass.class,lpparam);



# Tips

Only need kill the target app in MainActivity, not reboot the device anymore ,Enjoy! XD