
ArrayList实际上就是基于数据结构中的数组进行封装的一个集合类，它里面所封装的方法核心都是对数组的操作。

回顾一下数组的特点：
- 采用连续的内存块存储
- 数据的随机访问时间复杂度为O(1)
- 数据的插入、删除操作，需要有数据搬移,效率很低，时间复杂度为O(n)

下面来学习一下ArrayList是如何对于数组进行操作的。


## 属性

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData; // non-private to simplify nested class access
    private int size;
}
```
首先大致解读一下有哪些属性，具体后面解析是如何使用它们的。
* DEFAULT_CAPACITY=10，默认容量为10，这里是针对ArrayList初始化大小指定的初始值为10，也就是初次分配数组的容量为10。
* EMPTY_ELEMENTDATA = {} 定义空数组
* DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {} 同样定义空数组，只不过从定义上看，加了一个默认容量的空数组。
* transient Object[] elementData，空数组，这里加了transient关键字
* int size,ArrayList的数据量的大小

看了下属性定义，产生问题：
- EMPTY_ELEMENTDATA和DEFAULTCAPACITY_EMPTY_ELEMENTDATA有什么区别？
- 属性都定义为空数组，那么如果有数据插入的时候，数组是如何扩容的？
- 本身使用了数组，为什么还要定义一个size?这个size有什么用？

下面来看一下具体的实现

## 构造函数

### 无参构造
``` java
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
```
这里直接分配了一个空数组，注意，并没有设置数组大小（jdk1.7之前不是这样的），此时DEFAULT_CAPACITY=10还没有用到。所以此时还没有分配内存空间，所以如果new了一个空对象，是不会分配内存空间的。
jdk1.7之前是直接分配容量为10的数组，即使不用，也会去默认申请内存空间，也就是容量为10的连续内存模块。

### 有参构造
``` java
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
```
有参构造，接收的参数为初始化容量参数initialCapacity，并且要求initialCapacity>=0.

- 如果initialCapacity>0 ,则直接分配容量为initialCapacity的Object数组，这里就会申请容量initialCapacity的连续的内存空间
- 如果initialCapacity=0，此时用到了EMPTY_ELEMENTDATA，分配一个空数组，并没有分配内存块。注意这里使用了EMPTY_ELEMENTDATA，而不是DEFAULTCAPACITY_EMPTY_ELEMENTDATA，离我们上面的问题又近一点了。

### 基于集合的有参构造

``` java
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
```
ArrayList支持传入集合类，我们来解析细如果传入集合的话，它做了什么处理。





