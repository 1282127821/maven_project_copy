
#Python位运算符
"""
基本运算和java类似  无非 + - * 、 %  += //= ......
这里主要说说位运算
"""
a = 60            # 60 = 0011 1100
b = 13            # 13 = 0000 1101
c = 0

c = a & b;        # 12 = 0000 1100
print ("1 - c 的值为：", c)

c = a | b;        # 61 = 0011 1101
print ("2 - c 的值为：", c)

c = a ^ b;        # 49 = 0011 0001
print ("3 - c 的值为：", c)

c = ~a;           # -61 = 1100 0011
print ("4 - c 的值为：", c)

c = a << 2;       # 240 = 1111 0000
print ("5 - c 的值为：", c)

c = a >> 2;       # 15 = 0000 1111
print ("6 - c 的值为：", c)
#Python成员运算符
"""
in	如果在指定的序列中找到值返回 True，否则返回 False。	x 在 y 序列中 , 如果 x 在 y 序列中返回 True。
not in	如果在指定的序列中没有找到值返回 True，否则返回 False。	x 不在 y 序列中 , 如果 x 不在 y 序列中返回 True。

is	is 是判断两个标识符是不是引用自一个对象	x is y, 类似 id(x) == id(y) , 如果引用的是同一个对象则返回 True，否则返回 False
is not	is not 是判断两个标识符是不是引用自不同对象	x is not y ， 类似 id(a) != id(b)。如果引用的不是同一个对象则返回结果 True，否则返回 False。

is 与 == 区别：

is 用于判断两个变量引用对象是否为同一个， == 用于判断引用变量的值是否相等。
注意这个一定要记住和java相反  java的==是完全相等(必须是同一个对象)  equals只需要保证值相等
"""
a = 10
b = 20
list = [1, 2, 3, 4, 5 ];

if ( a in list ):
    print ("1 - 变量 a 在给定的列表中 list 中")
else:
    print ("1 - 变量 a 不在给定的列表中 list 中")

"""
type() 类似java中的getClass    
isInstance 判断是否是某种类型，类似java instance  
java中instance人为 子类是父类的一种，py的这个isInstance也是这样
java中的getClass 获取某个具体类型 父类就是父类 子类就是子类  type也类似
所以  type(a)==type(b)  就算a ，b是父子类型  他们也不相等
但是  isinstance（a,b）  他要是父子关系就是相等
"""

if ( b not in list ):
    print ("2 - 变量 b 不在给定的列表中 list 中")
else:
    print ("2 - 变量 b 在给定的列表中 list 中")

# 修改变量 a 的值
a = 2
if ( a in list ):
    print ("3 - 变量 a 在给定的列表中 list 中")
else:
    print ("3 - 变量 a 不在给定的列表中 list 中")

"""
进制问题  java支持 16进制，但是py支持 8进制和16进制  （2进制 10进制不说）
16进制是 0x 开头  8进制是  0o开头
"""
num16=0x7f
print(num16)
num8=0o11
print(num8)
"""
py 里面如果只有计算，但是没有将计算结果赋给某个变量  如  7/3 他会默认将计算结果赋给 "_"  _这个是一个内置变量就好比 orcal有一个dual
不知道为嘛不生效，先这样
"""
7/3
# print(_+1)
"""
由于使用 内置数学函数需要导包 ，这里总结下 py导包
import 与 from...import
在 python 用 import 或者 from...import 来导入相应的模块。

将整个模块(somemodule)导入，格式为： import somemodule

从某个模块中导入某个函数,格式为： from somemodule import somefunction

从某个模块中导入多个函数,格式为： from somemodule import firstfunc, secondfunc, thirdfunc

将某个模块中的全部函数导入，格式为： from somemodule import *

也就是一种直接把某个类导入进来，一种是只导入该类某个函数，所以常用的就是导入类！
数学相关函数 常量
"""
import math
print("pi：",math.pi)
print("ceil:",math.ceil(5.3))
"""
py字符串可以使用 "  和 '   效果一样的  单引号 双引号
这里主要说下转义字符 ，常见的，太特殊的算了，需要的时候看文档
py里面的“+”不支持类似java的 字符串换行，只能是本行，所以要想换行写 就需要使用  "\"但是注意只能写在行尾，否则就成了转义符合而不是换行符
注意 \\和java一致，就是让转义符不生效，但是这个只能转换他相邻的\ ，如果想一个字符串所有的\都不生效就是使用 r
str =r"ab\\c\nd"   注意 r在引号外面

Python字符串格式化
其实这个有点类似 java的 ScriptEngine  或者说el表达式。
写一个占位符，然后 给值  常用的占位符两个 %s  %d  刚好对应 英文（正则表达式里面的）
格式 ：print("大叫好，我叫%s，今年%s岁" %("肖咏",23))
这一种是通用的，其实python2.6后有个更好的，字符串有一个内置 函数format 但是本质就是上面的.这种更像是mybatis里面的渲染
根据 顺序渲染{0}，根据名称渲染{name}
print("你好啊，{0}。欢迎来到{1}".format("xy","中国"))
print("你好啊，{name}。欢迎来到{country}".format(name="xy",country="中国"))
这两种方式又让我们联想到了 list 和 字典 因为list -->0,1,2...  字典{key：value}
所以可以直接给format传递一个list或者字典

python的三符号 \"\"\"字符串(忽略转义符合否则报错)，他和之前的\ 换行区别在于 \换行不会包含换行符，也就是看着是换行了，但是输出还是一行
而三个符号的多好，他会包括换行符，就看看到几行输出就是几行
2018年8月15日17:41:43今天就这么多的怕消化不了
"""
str="ab" \
    "cd"
print(str)
str2="ab\\c\nd"
str3=r"ab\\c\nd"
print(str2)
print(str3)
print("大叫好，我叫%s，今年%s岁" %("肖咏",23))
print("你好啊，{0}。欢迎来到{1}".format("xy","中国"))
print("你好啊，{name}。欢迎来到{country}".format(name="xy",country="中国"))
list=["xy","中国"]
dict={"name":"xy","country":"中国"}
print("你好啊，{0[0]}。欢迎来到{0[1]}".format(list))#注意这个0是必须的，必须是{0[n]}这种格式
print("你好啊，{name}。欢迎来到{country}".format(**dict))
# 通过字典设置参数
site = {"name": "菜鸟教程", "url": "www.runoob.com"}
print("网站名：{name}, 地址 {url}".format(**site))

three="""第一行
第二行
第三行"""
print(three)
"""
字符串内置操作函数，和Java的字符串操作对比记忆
capitalize() capital表示首都 可以理解为第一位，所以就是首字母大写
center(width, fillchar) 这个是把我们的调用者放到中间，旁边用 fillchar指定的填充字符填充，但是保证新的字符串他们的总长度为width
str4="abcde123"  print(str4.center(10,"*"))  效果就是 *abcde123*
如果width指定宽度小于原字符串长度，那么就会按照原样字符串输出 abcde123

注意一点 python里面给形参赋值，必须这样  参数名="要赋的值"  而java直接写值就行
太多了周末看看吧
"""
str4="abcde123"
print(str4.capitalize(),"--对比--",str4)
print(str4.center(10,"*"))
str5=str4.encode(encoding="utf-8",errors="ignore")
print(str5,type(str5))
print(str5.decode("ISO-8859-1",errors="ignore"))
