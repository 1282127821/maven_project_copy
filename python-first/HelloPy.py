#运算
print("helllo py")
a,b=9,2
c,d=9.1,2
print(a/b)
print(a//b)
print(a**b)
e=c//d
print(e)
print(type(e))

f,g,h=True,False,True
print(f+g+h)
print(f*g*h)
"""
注意 //如果运算的都是整数，那么得到的结果就是计算后截取的整数部分。如果运算有一个 浮点数，那么得到的就是 小数点固定为0 ，然后整数部分
为运算后的整数部分。 如 9.1//2 小数部分固定为0 然后整数计算后截取整数部分4  最终为4.0
在整数除法中，除法 / 总是返回一个浮点数，如果只想得到整数的结果，丢弃可能的分数部分，可以使用运算符 // ：
也就是  / 计算的时候无论是不是 浮点数，结果都是浮点数！！！！！！！！！！！！

由于python里面的bool值 true=1 false=0 而且可以运算，所以可以用数学结果判定真假
f+g+h=0 表示都为false  f+g+h>=1 说明至少有一个为真   f*g*h=0说明至少一个为假 f*g*h=1说明全部为真
字符串一旦定义，不允许使用的时候修改它的值，
str="qwer"   str[0]="w" 这样是不允许的（和java记忆，java 截取字符串是重新生成一个新的字符串，而在py是报错）
但是允许 str="qwer" str="asdw" 这个是叫做重新定义，而不是使用的时候修改（因为他是相当于再定义了一个变量str覆盖之前定义的str，这样理解）
"""
#字符串和基本类型 基本类型就是 number和boolen 。字符串属于引用类型 这个可以拿java作理解记忆
num1=100
num2=10.11
str="abcde"
str2="qwer"
b=False
print(str,num1,num2,b)
print(str[0],str[0:2],str[1:],str[-1],str[-2],str+str2,str*2)
del str,num2,b
print(str)
print(num1)
# print(num2)
# print(b)
noob=r"noo\nlm\n"
print(noob)
"""
py里面支持print 同时输出多个变量的，以 , 隔开
del是py里面用来移除变量引用的，比如你开始给str定义了一个值，然后del str后再打印他就相当于没有赋值
如果del的是数字类型那么就相当于从来没有定义过 该变量
所以del 分为引用类型和基本类型.引用类型就相当于清空之前的赋值，基本类型相当于移除了这个变量

py字符串的转义字符也是 \ 但是当我们不让\转义不是 使用\\而是  r"\"  noob=r"noo\nlm\n" 注意 r不在引号内部，而是在外面
"""
#python的几种复杂类型
"""
list 类型这里就不多说，和string类型操作好多类似，这里就说一点不同的操作
定义list使用 [ ]
"""
list=[1,1.0,"a",100,"c"]
print(list)
print(list[0])
del list
print(list)
ls=[1]
print(ls)
"""
元组 ，元组一旦定义，也不允许使用阶段改变（类似字符串），这也是他和list区别
定义元组 （）
构造包含 0 个或 1 个元素的元组比较特殊，所以有一些额外的语法规则：
tup1 = ()    # 空元组
tup2 = (20,) # 一个元素，需要在元素后添加逗号，注意一个元素必须这样！！！
"""
tuple=()
print(tuple)
tuple2=(1,1.0,"a",100,"c")
print(tuple2)
tuple3=(1,)
print(tuple3)
"""
字典 类似java的map集合，也是key-value的格式
字典只能够用过key获取，类似java的map操作，没有索引操作
keys和values是两个内置函数，后面会着重讲解
使用构造函数方式创建字典(行不通不知道为嘛，先这样)
"""
dict={}
print(dict)
dict2={"k1":"v1","k2":2,"k3":3.00}
print(dict2)
# print(dict2[0])
print(dict2["k1"])
print(dict2.keys())
print(dict2.values())

"""
set 集合
创建：set()  或{}
注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典，所以为了区分空的必须 set()
注意set和javaset一样无法使用索引，因为每次结果都不一样！无法使用索引，无序
使用set("abc")创建他会把字符串每一个字符都当做一个项,也就是这个会转化为  {"a","b","c"}
而且set()这种只能支持【字符串】我尝试过其他类型直接报错！！！
"""
student = {'Tom', 'Jim', 'Mary', 'Tom', 'Jack', 'Rose'}
print(student)
set1=set("qwer123")
print(set1)
set2=set()
print(set2)
# set3=set(123)
# print(set3)
#python的类型转换
"""
很简单  ,下面只是部分，都是这种格式！！
tuple(s)将序列 s 转换为一个元组

list(s)将序列 s 转换为一个列表

set(s)转换为可变集合

dict(d)创建一个字典。d 必须是一个序列 (key,value)元组。

"""
s=100
f=float(s)
ss=str(s)
print(type(f))
print(type(ss))
tolist=list(tuple2)
print(type(tolist))

