package com.fall.gank;

/**
 * Created by qqq34 on 2016/12/1.
 */

public class PrettyTest {
    /**
     * 根据输入值的大小返回字符串
     * @param a 输入值
     * @return 返回的字符串结果
     */
    public String print(int a){
        System.out.println("==========current input number is: " + a + "==========");
        return  a > 0? "大":"小";
    }
}
