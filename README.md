## 扫码功能是集成华为的扫码HMS Scankit
```
项目的build.gradle下
  buildscript {
       repositories {
        maven{url('http://developer.huawei.com/repo/')}
    }
    dependencies {
            classpath "com.huawei.agconnect:agcp:1.2.0.300"
        }
        allprojects {
            repositories {
                google()
                jcenter()
                maven{url'http://developer.huawei.com/repo/'}   //maven 地址
            }
        }
```

app的build.gradle下添加
```
dependencies{
  implementation 'com.huawei.hms:scanplus:1.1.1.301'
 }
```

## Handler按钮实现异步处理消息，不影响主线程中其他按钮得使用