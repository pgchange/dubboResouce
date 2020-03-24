# String深度解析以及扩展学习

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
## String常用方法
### 构造方法
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
    String aa = new String("abc");
```
创建了几个对象？这个问题看似简单，其实还有点深奥。这里需要去复习JVM内存结构

先说答案是：两个或者一个，实际是有两个对象。

原因是：
JVM首先会在字符串常量池里面查找有没有"abc"这个字符串对象
如果有，则不在池中再创建"abc"这个对象了，直接在堆中创建"abc"字符串对象，然后将这个对象的地址赋值给aa，这时候aa指向的是堆中的这个对象。这种情况下只创建了一个对象。
如果没有，则首先回去池中创建"abc"字符串对象，然后在堆中创建"abc"字符串对象，然后将这个对象的地址赋值给aa，这时候aa指向的是堆中的这个对象。这种情况创建了两个对象。

需补一张图

3.构造传入字符数组char[]

``` java
    public String(char value[]) {
        this.value = Arrays.copyOf(value, value.length);
    }
```
创建一个新的String对象，并且为该对象新建一个数组，使用了数组的复制功能copy数据，这样就防止当前字符串被改变。

这里涉及到数据结构数组的迁移，数组的存储？性能？

4.构造传入StringBuffer
``` java
 public String(StringBuffer buffer) {
        synchronized(buffer) {
            this.value = Arrays.copyOf(buffer.getValue(), buffer.length());
        }
    }
```

关键知识点synchronized、StringBuffer原理

5.构造传入StringBuilder
``` java
  public String(StringBuilder builder) {
        this.value = Arrays.copyOf(builder.getValue(), builder.length());
    }

```
关键知识点StringBuilder原理

6.构造传入byte[]
``` java
  public String(byte bytes[]) {
        this(bytes, 0, bytes.length);
    }
```
其他构造函数方法略


### 其他关键方法
1.substring方法
有两个方法
```  java
    public String substring(int beginIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        int subLen = value.length - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return (beginIndex == 0) ? this : new String(value, beginIndex, subLen);
    }
```

``` java
    public String substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > value.length) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return ((beginIndex == 0) && (endIndex == value.length)) ? this
                : new String(value, beginIndex, subLen);
    }

```
这里有一个关键点，就是起始位，这个有时候很难记住，末位到底是算还是不是，总容易弄混。
其实只要理解了原理，很容易分析。首先底层是数组结构存储，字符串截取其实就是对数据进行操作，而数组的起始位是0开始的（数据为什么是从0开始的？数据结构-数组中会有解释）。
而源码中的beginIndex、endIndex知识决定了要截取字符串的长度。
例如：
``` java
    public static void main(String[] args) {
        String a = "abcdefg";
        // 这里是从0开始，截取 1-0 = 1长度的字符串，那么结果就是a
        System.out.println(a.substring(0,1));
        // 这里从1开始，截取 3-1 = 2长度的字符串，结果就是bc
        System.out.println(a.substring(1,3));
        // 这里从4开始截取字符串，取的是 a的长度-4 长度的字符串，就是efg
        System.out.println(a.substring(4));
    }
```
运行结果：
``` java
a
bc
efg
```

2.equals && compareTo方法
这两个方法都可以比较字符串是否相等。
看源码
``` java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }

```
``` java
    public int compareTo(String anotherString) {
        int len1 = value.length;
        int len2 = anotherString.value.length;
        int lim = Math.min(len1, len2);
        char v1[] = value;
        char v2[] = anotherString.value;

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
```


3.hashCode方法
``` java
    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

```

4.intern方法

``` java
    public native String intern();
```

## 知识点扩展
1.String类为什么设计成final类


2.StringBuffer和StringBuilder的区别


3.









