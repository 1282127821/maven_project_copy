package com.xy.future;

/**
 * @fileName:RealData
 * @author:xy
 * @date:2018/12/2
 * @description:
 */
@SuppressWarnings("all")
public class RealData implements Data {
    private String result;

    public RealData(String result) {
        StringBuffer sb=new StringBuffer();
    for (int i = 0; i < 5; i++) {
        sb.append(result);
        try {
            Thread.sleep(1000);//模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        this.result = result;
    }

    @Override
    public String getData() {
        return result;
    }
}
