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
		//1,�ҵ��ݹ��㷨�ĳ���
		if( low > high) {
			return;
		}
		//2, ��
		int i = low;
		int j = high;
		//3,key
		int key = a[ low ];
		//4�����һ������
		while( i< j) {
			//4.1 �����������ҵ���һ��С��key����
			while(i<j && a[j] > key){
				j--;
			}
			// 4.2 ���������ҵ���һ������key����
			while( i<j && a[i] <= key) {
				i++;
			}
			//4.3 ����
			if(i<j) {
				int p = a[i];
				a[i] = a[j];
				a[j] = p;
			}
		}
		// 4.4������key��λ��
		int p = a[i];
		a[i] = a[low];
		a[low] = p;
		//5, ��key��ߵ�������
		quickSort(a, low, i-1 );
		//6, ��key�ұߵ�������
		quickSort(a, i+1, high);


		
		

	}

}
