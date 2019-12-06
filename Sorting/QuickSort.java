package Sorting;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String[] args) {
		
		int[] a = {5,2,8,9,10,1,3,4,9};
		System.out.println(Arrays.toString(a));
		quickSort(a);
		System.out.println(Arrays.toString(a));
	}
 
	public static void quickSort(int[] a) {
		if(a.length>0) {
			quickSort(a, 0 , a.length-1);
		}
	}
 
	private static void quickSort(int[] a, int low, int high) {
		//1,找到递归算法的出口
		if( low > high) {
			return;
		}
		//2, 存
		int i = low;
		int j = high;
		//3,key
		int key = a[ low ];
		//4，完成一趟排序
		while( i< j) {
			//4.1 ，从右往左找到第一个小于key的数
			while(i<j && a[j] > key){
				j--;
			}
			// 4.2 从左往右找到第一个大于key的数
			while( i<j && a[i] <= key) {
				i++;
			}
			//4.3 交换
			if(i<j) {
				int p = a[i];
				a[i] = a[j];
				a[j] = p;
			}
		}
		// 4.4，调整key的位置
		int p = a[i];
		a[i] = a[low];
		a[low] = p;
		//5, 对key左边的数快排
		quickSort(a, low, i-1 );
		//6, 对key右边的数快排
		quickSort(a, i+1, high);


		
		

	}

}
