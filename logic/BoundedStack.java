package logic;

public class BoundedStack {
	private int size;
	private Object[] obj=null;
	private int firstfree=0;
	

	public int getFirstfree() {
		return firstfree;
	}

	public void setFirstfree(int firstfree) {
		this.firstfree = firstfree;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Object[] getObj() {
		return obj;
	}

	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public BoundedStack(int size) {
		this.size=size;
		this.obj=new Object[size];
		
	}
	
	public void push(Object o) {

		Object[]o1;
		o1=this.obj;
		int f=this.firstfree;
		
		if(f<this.size) {
			o1[f]=o;
			this.obj=o1;
			this.firstfree++;
		}
		else {
			throw new StackOverflowError();
		}
	}
	
	
	public void pop() {
		
		if(this.firstfree==0) {
			throw new StackOverflowError( );
		}
		
		int f1=this.firstfree-1;
		this.obj[f1]=null;
		this.firstfree=this.firstfree-1;

	}
	
	public int depth() {
		return this.firstfree;
	}
	

	
//	public static void main(String[] args) {
//		BoundedStack b=new BoundedStack(5);
//		Object[]ooo= {"tom","kim","jim","ok","yes"};
//		b.setObj(ooo);
//		b.setFirstfree(5);
//		String str="ok1";
//		String str1="ok2";
//		String str2="ok3";
////		b.pop();
//		b.push(str1);
////		b.push(str2);
//		Object[]oo=b.getObj();
//		System.out.println(b.depth());
//		for(Object o5:oo) {
//		System.out.println(o5);
//		}
//		
//	}

}
