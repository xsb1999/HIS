package Sorting;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

import application.model.Patient;

public class MyQuickSortP {			 //快速排序

	
	//入口
		public static Patient[] quickSort(List<Patient> listp, boolean isID) {

			Patient[] a = new Patient[listp.size()];
			for(int i=0;i<a.length;i++) {
				a[i]=listp.get(i);
			}
			
			
			if(a.length>0) {
				quickSort  (a, 0 , a.length-1,isID);
			}
			
			return a;
		}
		
		//递归快排
		private static void quickSort  (Patient[] a, int left, int right,boolean isID) {
			
		    if (left < right) {
		    	int centre = (left + right)/2;
		    	Patient pivot=a[centre];        //总是选择中间的元素作为pivot，时间复杂度更低
		    	int pivotPos = partition(a, left, right, pivot,isID);
		    	
		    	//递归pivot两边的元素
		    	quickSort(a,left,pivotPos-1,isID);
		    	quickSort(a,pivotPos+1,right,isID);	    	
		    }

		}
		
		
		//partition方法，用来找到每一个pivot的正确位置
		private static int partition(Patient[] a, int left, int right, Patient pivot,boolean isID) {
			
			
			int pivotPos=(left+right)/2;
			
			//将pivot移到最前面
			Patient temp=a[pivotPos];
			a[pivotPos]=a[left];
			a[left]=temp;
			int swapPos=left+1;
			int i=left+1;
			
			//按ID排序
			if(isID) {
				while(i<=right) {
					String s1=a[i].getId();
					String s2=pivot.getId();
					
					if(s1.endsWith("T")||s1.endsWith("U")) {
						if(s1.endsWith("UT")) {
							s1=s1.substring(0, s1.length()-2);
						}
						else {
							s1=s1.substring(0, s1.length()-1);
						}
					}
					
					
					if(s2.endsWith("T")||s2.endsWith("U")) {
						if(s2.endsWith("UT")) {
							s2=s2.substring(0, s2.length()-2);
						}
						else {
							s2=s2.substring(0, s2.length()-1);
						}
					}
					
						
					if(Integer.parseInt(s1)<Integer.parseInt(s2)) {
						Patient temp1=a[i];
						a[i]=a[swapPos];
						a[swapPos]=temp1;
						swapPos++;		
					}
					i++;		
				}			
			}
			
			
			//按姓名排序
			else {		
				Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
				
				while(i<=right) {
					if(com.compare(a[i].getName(), pivot.getName())<0) {
						Patient temp1=a[i];
						a[i]=a[swapPos];
						a[swapPos]=temp1;
						swapPos++;		
					}
					i++;		
				}	
			}
			
			pivotPos=swapPos-1;

			//将pivot放到正确的位置
			Patient temp2=a[left];
			a[left]=a[pivotPos];
			a[pivotPos]=temp2;
			
			
			
			return pivotPos;
		}

		
		


		public static void main(String[] args) {
			
			List<Patient>listp=Patient.read();
			
			
			Patient[]arrp=quickSort(listp,false);
			
			for(int i=0;i<arrp.length;i++) {
				System.out.println(arrp[i].getName());
			}
			
			
		}
		


}
