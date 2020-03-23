String深度解析以及扩展学习

String类可以说是java里面最简单最基础也最常用的一个类了，对于它了解有多深呢？
学习了数据结构-数组后，重新来温习下String类的具体实现，温故而知新。才发现越挖越深，就这么一个看似简单的类，涉及到的知识点是真深！！

查看String类的源码，看着很简单，底层维护了一个字符数组，也就是说它具体实现的数据结构是数组。

源码
``` java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];

    /** Cache the hash code for the string */
    private int hash; // Default to 0


```
接下来看下这个数据是如何存储的。

String的构造方法如下：
1. 默认构造
``` java
   public String() {
        this.value = "".value;
    }
```
默认构造返回一个空字符串，没什么好说的。

2.传参String构造
``` java
    public String(String original) {
        this.value = original.value;
        this.hash = original.hash;
    }
```
该构造方法，传入的是一个String类对象，直接取value && hash赋值给新的String对象。

会想起，有一个很基础的经典面试题：
``` java
    String aa = new String("aa");
```
创建了几个对象？

这个问题看似简单，其实还有点深奥。

这个问题需要了解jvm的字符串常量池。














