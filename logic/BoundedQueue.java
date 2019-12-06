package logic;


public class BoundedQueue {

	private int size;
	private Object[] objarr=null;
	private int first;
	private int firstfree;

	
	
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Object[] getObjarr() {
		return objarr;
	}

	public void setObjarr(Object[] objarr) {
		this.objarr = objarr;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getFirstfree() {
		return firstfree;
	}

	public void setFirstfree(int firstfree) {
		this.firstfree = firstfree;
	}

	public BoundedQueue(int size,int first,int firstfree) {
		this.size=size;
		this.first=first;
		this.firstfree=firstfree;
		this.objarr=new Object[size];
	}
	
	
	//insert
	public void insert(Object obj) {
		
		if((this.first==this.firstfree)&&(this.objarr[this.first]!=null)) {
			System.out.println("full");
			return;
		}
		this.objarr[this.firstfree]=obj;
		if(this.firstfree<this.size-1) {
		this.firstfree++;
		}
		else {
			this.firstfree=0;
		}
	}
	
	
	//	ÊÇ·ñÎª¿Õ
	public boolean isEmpty() {
		if((this.first==this.firstfree)&&(this.objarr[this.first]==null)) {
			return true;
		}
		return false;
		
	}
	
	
	
	
	//remove
	public Object[] remove(){
		if((this.first==this.firstfree)&&(this.objarr[this.first]==null)) {
			{
				System.out.println("empty");
				return null;
				}
			}
		//System.out.println("remove: "+this.objarr[this.first]);
		this.objarr[this.first]=null;
		this.first++;
		return this.objarr;
		
	}
	
	
	//getfront
	public Object getfront() {
		//System.out.println("front: "+this.objarr[this.first]);
		return this.objarr[this.first];
	}
	
	
	
	//length
	public int length() {
		int i=0;
		Object[] ob=this.objarr;
		for(Object o:ob) {
			if(o!=null) {
				i++;
			}
		}
		return i;
	} 
	
	
	
	public static void main(String[] args) {
		BoundedQueue b1=new BoundedQueue(5,0,4);
		Object[] o=b1.getObjarr();
		o[0]="fish";
		o[1]="pig";
		o[2]="dog";
		o[3]="goat";
		
		Object[] o1=b1.remove();
		
		System.out.println(b1.getFirst());
		System.out.println(b1.getFirstfree());
		for(Object oo:o1) {
			System.out.println(oo);
		}
		
		System.out.println(b1.length());
		
		b1.getfront();
	}
	
}