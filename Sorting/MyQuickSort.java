package Sorting;

import java.util.Arrays;

public class MyQuickSort {

	//���
	public static void quickSort(int[] a) {
		if(a.length>0) {
			quickSort  (a, 0 , a.length-1);
		}
	}
	
	//�ݹ����
	private static void quickSort  (int[] a, int left, int right) {
		
	    if (left < right) {
	    	int centre = (left + right)/2;
	    	int pivot=a[centre];        //����ѡ���м��Ԫ����Ϊpivot��ʱ�临�Ӷȸ���
	    	int pivotPos = partition(a, left, right, pivot);
	    	
	    	//�ݹ�pivot���ߵ�Ԫ��
	    	quickSort(a,left,pivotPos-1);
	    	quickSort(a,pivotPos+1,right);	    	
	    }

	}
	
	
	//partition�����������ҵ�ÿһ��pivot����ȷλ��
	private static int partition(int[] a, int left, int right, int pivot) {

		int pivotPos=(left+right)/2;
		
		//��pivot�Ƶ���ǰ��
		int temp=a[pivotPos];
		a[pivotPos]=a[left];
		a[left]=temp;
		int swapPos=left+1;
		int i=left+1;
		
		while(i<=right) {
			if(a[i]<pivot) {
				int temp1=a[i];
				a[i]=a[swapPos];
				a[swapPos]=temp1;
				swapPos++;		
			}
			i++;		
		}
				
		pivotPos=swapPos-1;

		//��pivot�ŵ���ȷ��λ��
		int temp2=a[left];
		a[left]=a[pivotPos];
		a[pivotPos]=temp2;
		
		return pivotPos;
	}

	
	


	public static void main(String[] args) {
		
		int[] a = {5,2,8,9,10,1,3,4,9,12,6,100,3};
		System.out.println(Arrays.toString(a));
		quickSort(a);
		System.out.println(Arrays.toString(a));
		
		

	}

}
