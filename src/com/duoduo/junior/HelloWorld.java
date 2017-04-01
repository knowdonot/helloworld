package com.duoduo.junior;

import java.util.Arrays;

public class HelloWorld {
	/**
	 * 程序入口
	 * 2017年4月1日
	 * By duoduo
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
//		printMultiplication();
//		sortMax();
		getMin();
	}
	
	/**
	 * 打印99乘法表
	 * 首先9*9由两个变化的数即横和竖两个
	 * 2017年4月1日
	 * By duoduo
	 */
	public static void printMultiplication(){
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				if(j>=i){
					System.out.print("\t"+i+"*"+j+"="+(i*j));
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * 冒泡获取最大值
	 * 说明：冒泡规则，将第一个值与第二个值进行比较，如果从大到小则将大的放在最前，小的放第二，第二再跟第三进行比较，
	 * 大的放前面，小的放后面依次类推，第一轮可以得到最小值。
	 * 2017年4月1日
	 * By duoduo
	 * @param arrInt
	 */
	public static void sortMax() {
		int[] arrInt = new int[] { 2, 10, 33, 45, 6, 2, 8, 9, 12 };
		System.out.println("original=="+Arrays.toString(arrInt));  
		for (int j = 0; j < arrInt.length; j++) {
			for (int i = 1; i < arrInt.length - j; i++) {
				int temp = arrInt[0];
				if (arrInt[i] - arrInt[i - 1] > 0) {
					temp = arrInt[i - 1];
					arrInt[i - 1] = arrInt[i];
					arrInt[i] = temp;
				}
			}
			System.out.println("change"+j+"=="+Arrays.toString(arrInt));  
		}
		System.out.println("===== result =====");  
		System.out.println(Arrays.toString(arrInt));  
	}
	
	
	public static void getMin() {
		int[] arrInt = new int[] { 2, 10, 33, 45,1, 6, 2, 8, 9, 12 };
		System.out.println("original=="+Arrays.toString(arrInt));  
		for (int i = 1; i < arrInt.length; i++) {
			int temp = arrInt[0];
			if (arrInt[i] - arrInt[i - 1] > 0) {
				temp = arrInt[i - 1];
				arrInt[i - 1] = arrInt[i];
				arrInt[i] = temp;
			}
		}
		System.out.println("===== result =====");  
		System.out.println(Arrays.toString(arrInt));  
	}
	

}
