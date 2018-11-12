package 字符串相似度算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * @fileName:StringSimilar
 * @author:xy
 * @date:2018/11/9
 * @description:
 */
public class StringSimilar {
  @Test
  public void test() {
    String strA = "abc";
    String strB = "abe";
    similar(strA, strB);
  }

  private void similar(String strA, String strB) {
    char[] chars_strA = strA.toCharArray();
    char[] chars_strB = strB.toCharArray();
    int[][] similars=new int[chars_strA.length+1][chars_strB.length+1];
    // 初始化 表格的两边(两边是固定的从左上角，分别往下,上0...n),注意 假设A是横坐标，B是纵坐标 这个决定好就不好再反了，容易出错，这里可能不会因为 A，B一样长，但是如果不一样长度就容易出错
    for (int i = 1; i <=chars_strA.length; i++) {
        similars[i][0]=i;
    }
    for (int i = 1; i <= chars_strB.length; i++) {
        similars[0][i]=i;
    }
    for (int i = 1; i <=chars_strA.length; i++) { //从一开始，因为0需要空着，注意 这里ij是表格位置，而不是数组索引
      for (int j = 1; j <= chars_strB.length;j++ ) {
            if (chars_strA[i-1]==chars_strB[j-1]){ //如果两个相等，那么左上角的值+0，最开始左上角肯定是0
                /*similars表示左上角 把i看成横坐标， j看成纵坐标，因为档期坐标永远是下一个坐标的左上角，
                所以similars[i][j]=similars[i-1][j-1]
                */
                int min = min(similars[i - 1][j - 1] + 0, i, j);
                similars[i][j]=min;
            }else { //左上角+1
                int min=min(similars[i - 1][j - 1]  +1, i, j);
                similars[i][j]=min;
          System.out.println(similars[i][j]);
            }
        }
    }
      //公式 1- similars[n]/maxLength
      int maxLength=Math.max(chars_strA.length, chars_strB.length);
      int similar=similars[chars_strA.length][chars_strB.length];
    System.out.println(similar+"<<similar");
    System.out.println(maxLength+"<<maxLength");
    //这里就是公式  *1.0主要是 变成浮点数 否则 1/3=0
      System.out.println(1-(similar*1.0/maxLength));
  }
  private int min(int ...nums){
      int temp=Integer.MAX_VALUE;
    for (int num : nums) {
        if (temp>num){
            temp=num;
        }
    }
      return temp;
  }
  @Test
  public void test2(){
      int min = min(2, 11, 3);
    System.out.println(min);
  }
}
