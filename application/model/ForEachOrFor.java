package application.model;

import java.util.ArrayList;
import java.util.List;

public class ForEachOrFor {

	public static void main(String[] args) {
		
		List<String>listS=new ArrayList<>();
		listS.add("a");
		listS.add("b");
		
		listS.set(0, "t");
		
		//forѭ������List<String>������for-each��ֻ������ͨ��forѭ��!!!!!!
		
		for(int i = 0; i < listS.size(); i++) {
			if(listS.get(i).equals("a"))
				listS.set(i, "c");
		}
		
		for(String s:listS) {
			System.out.println(s);
		}		
		
		}

}
