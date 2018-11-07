/**
 * @fileName:CopyTest
 * @author:xy
 * @date:2018/8/12
 * @description:
 */
package com.xy.copy;

/**
 *@fileName:CopyTest
 *@author:xy
 *@date:2018/8/12
 *@description:
 */
public class CopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Body body=new Body(1,new Head(2,new Eye()));
        Body clone =(Body) body.clone();
        System.out.println("body==clone?"+(clone==body));
        System.out.println(clone.head==body.head);
        System.out.println(clone.head.eye==body.head.eye);
    }
}

class Body implements Cloneable{
    public int b;
    public Head head;

    public Body(int b, Head head) {
        this.b = b;
        this.head = head;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Body body = (Body) super.clone();
        body.head = (Head)(head.clone());
        return body;
    }
}
class Head implements  Cloneable{
    public int h;
    public Eye eye;

    public Head(int h, Eye eye) {
        this.h = h;
        this.eye = eye;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Head head = (Head)(super.clone());
        head.eye=(Eye) eye.clone();
        return head;
    }
}
class Eye implements  Cloneable{
    public Eye() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
