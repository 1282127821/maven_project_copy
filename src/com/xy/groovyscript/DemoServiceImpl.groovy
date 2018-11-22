package com.xy.groovyscript

import com.xy.DemoService


class DemoServiceImpl implements DemoService{
    def msg
    String sayHello(){
        return 'hello'+msg+' ok' //第一次打印后修改成为'hello'+msg+' not ok'
    }
}
