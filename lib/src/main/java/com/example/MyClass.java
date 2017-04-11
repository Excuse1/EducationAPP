package com.example;

public class MyClass {
    public static void main(String[] args) {

   /*     System.out.println(printHeart("*"));
    }
    private static String printHeart(String input){
        int[] array = {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 4, 5, 2, 3, 4, 1, 0, 1,0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if(i % 7 == 0)
                sb.append("\n");
            if(array[i] == 0)
                sb.append("   ");
            else if(array[i] == 4)
                sb.append("    ");
            else if(array[i] == 5)
                sb.append(" ");
            else if(array[i] == 2)
                sb.append("Sorry");
            else if(array[i] == 3)
                sb.append("");
            else
                sb.append("  "+input);
        }
        return sb.toString();
    }*/


        //字符串  截取 与 分割
        String sub = "AA:BB";
       /* String aa=null;
        String bb=null;
        int length = sub.length();
        for (int i=0;i<length;i++){
            if (sub.substring(i,i+1).equals(":")){
                aa = sub.substring(0, i);
                 bb = sub.substring(i+1, length);

            }
        }
        System.out.println(aa);
        System.out.print(bb);*/


/*     //截取
        String[] array = sub.split(":");

        System.out.print(array[0]);
        System.out.print(array[1]);*/


        //阶乘
        //recursive(5);

        System.out.print(F(6));

        //使用非递归 黄金分割
        // System.out.print(F1(5));


        //冒泡排序
//        int[] ints = maoPao(new int[]{4, 2, 5, 9});
//        for (int i:ints){
//            System.out.println(i);
//        }

        //插入排序
/*        int[] ints = chaRu(new int[]{3, 1, 5, 6});
        for (int i:ints){
            System.out.println(i);
        }*/

        //十进制转十六进制
        // String s = stringToHexString("796!");
        // System.out.println(s);

/*        BigInteger target = new BigInteger("97");
        System.out.println(target.toString(10));
        System.out.println(Long.toHexString(97));*/

    }

    //阶乘
    private static int recursive(int i) {
        int sum = 0;
        if (0 == i) {
            System.out.println(sum);
            System.out.print("j");
            return 1;

        } else {
            sum = i * recursive(i - 1);

            System.out.println(sum);
            return sum;
        }

    }

    //黄金分割
    public static int F(int i) {

        if (i == 1 || i == 2) {
            System.out.println("==AAA=="+i);
            return 1;
        } else {
            System.out.println("==BBBB=="+F(i - 1) + F(i - 2));
            return F(i - 1) + F(i - 2);
        }

    }

    //使用非递归 黄金分割
    public static int F1(int index) {
        if (index == 1 || index == 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 0; i < index - 2; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    //冒泡排序
    //i跟后面的每一个数比较
    //开始：9 4 5 6 8 3 2 7 10 1  （下标从左到右分别是0~9）按照上面的程序进行对比交换
    public static int[] maoPao(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int item;
                if (array[i]>array[j]) {
                item = array[i];
                array[i] = array[j];
                array[j] = item;
                }
            }
        }
        return array;


    }

    //插入排序   从第二位开始依次跟前面的比较,比前面的小就往前面移
    public static int[] chaRu(int[] array) {
        int i, j;
        for (i = 1; i < array.length; i++) {
            int temp = array[i];
            //temp 依次跟前面的比较
            for (j = i; j > 0 && array[j - 1] > temp; j--) {

                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
        return array;
    }


    //把10进制字符串转换为16进制字符串
    public static String stringToHexString(String strPart) {
        String hexString = "";
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            System.out.println(strPart.charAt(i));
            System.out.println("===AAAA===" + ch);
            String strHex = Integer.toHexString(ch);
            hexString = hexString + strHex;
            System.out.println("===BBBB===" + hexString);
        }
        return hexString;
    }
}