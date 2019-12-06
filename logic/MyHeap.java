package logic;

import application.model.Patient;

public class MyHeap{
	
	private Patient[] nodes;
	private int firstfree=1;
	

	public Patient[] getNodes() {
		return nodes;
	}



	public void setNodes(Patient[] nodes) {
		this.nodes = nodes;
	}


	public int getFirstfree() {
		return firstfree;
	}



	public void setFirstfree(int firstfree) {
		this.firstfree = firstfree;
	}





	public MyHeap(int size) {
		this.nodes=new Patient[size];
	}
	
	
	
	//insert 方法
	public void insert(Patient sw) {
		
		String str=null;
		
		//加入头结点		
		if(this.firstfree==1) {
			this.nodes[this.firstfree]=sw;
			this.firstfree++;
			return;
		}
		
		
		else {
			//int a=this.firstfree/2;
			//Node no=this.nodes[a];
			//n.setParent(no);
			this.nodes[this.firstfree]=sw;
					
//			if(no.getLeft()==null) {
//				no.setLeft(n);
//				
//			}
//			else {
//				no.setRight(n);
//				
//			}	

			
			int i=this.firstfree;
			Patient temp=new Patient();
			
			
			while(Integer.parseInt(this.nodes[i].getWeight())<Integer.parseInt(this.nodes[i/2].getWeight())){
				temp=this.nodes[i/2];
				this.nodes[i/2]=this.nodes[i];
				this.nodes[i]=temp;
				i=i/2;
				if(i==1) {
					break;
				}
			}
			
			this.firstfree++;
			
			
			
			
			
			
			
//			while(n.getWeight()<n.getParent().getWeight()) {
//				
//				if(n.equals(n.getParent().getLeft())) {
//					str="left";
//				}
//				else {
//					str="right";
//				}
//	
//				n.setParent(no.getParent());
//				if(no.getParent().getLeft().equals(no)) {
//					no.getParent().setLeft(n);
//				}
//				
//				else {
//					no.getParent().setRight(n);
//				}
//				
//				no.setParent(n);
//				
//				if(str.equals("left")) {
//					n.setLeft(no);
//				}
//				else {
//					n.setRight(no);
//				}
//				
//				this.nodes[this.firstfree]=no;
//				this.nodes[a]=n;
//
//			}
			
		}
		
	}
	
	
	
	
	
	//remove 方法	
	public void remove() {
		
		
		if(this.firstfree==1) {
			System.out.println("empty!");
		}
		
		else if(this.firstfree==2) {
			this.nodes[1]=null;
			this.firstfree=1;
		}
		
		else if(this.firstfree==3) {
			this.nodes[1]=this.nodes[this.firstfree-1];
			this.nodes[this.firstfree-1]=null;
			this.firstfree=2;
		}
		
		else if(this.firstfree==4) {
			Patient temp=new Patient();
			this.nodes[1]=this.nodes[this.firstfree-1];
			this.nodes[this.firstfree-1]=null;
			this.firstfree=3;
			if(Integer.parseInt(this.nodes[1].getWeight())>Integer.parseInt(this.nodes[2].getWeight())) {
				temp=this.nodes[1];
				this.nodes[1]=this.nodes[2];
				this.nodes[2]=temp;
			}
			
			
			
		}
		
	else {
			
			this.nodes[1]=this.nodes[this.firstfree-1];
			this.nodes[this.firstfree-1]=null;
			this.firstfree--;
			

		Patient temp=new Patient();
		
		int i=1;
		while((Integer.parseInt(this.nodes[i].getWeight())>Integer.parseInt(this.nodes[2*i].getWeight()))||(Integer.parseInt(this.nodes[i].getWeight())>Integer.parseInt(this.nodes[2*i+1].getWeight()))) {
			
			if(Integer.parseInt(this.nodes[2*i].getWeight())<Integer.parseInt(this.nodes[2*i+1].getWeight())) {
				
				temp=this.nodes[i];
				this.nodes[i]=this.nodes[2*i];
				this.nodes[2*i]=temp;
				i=2*i;
				
				if(2*i>=this.firstfree) {
					break;
				}
				
				if(2*i+1>=this.firstfree) {
					if(Integer.parseInt(this.nodes[i].getWeight())>Integer.parseInt(this.nodes[2*i].getWeight())) {
						temp=this.nodes[i];
						this.nodes[i]=this.nodes[2*i];
						this.nodes[2*i]=temp;
						break;
					}
					
					else {
						break;
					}
				}
				
			}
			
			else {
				temp=this.nodes[i];
				this.nodes[i]=this.nodes[2*i+1];
				this.nodes[2*i+1]=temp;
				i=2*i+1;
				
				if(2*i>=this.firstfree) {
					break;
				}
				
				if(2*i+1>=this.firstfree) {
					if(Integer.parseInt(this.nodes[i].getWeight())>Integer.parseInt(this.nodes[2*i].getWeight())) {
						temp=this.nodes[i];
						this.nodes[i]=this.nodes[2*i];
						this.nodes[2*i]=temp;
						break;
					}
					
					else {
						break;
					}
					
				}
				
					
				
			}			
			
		}
		
		}
		
		
	}
	
	
	
	
	

	
//	public static void main(String[] args) {
//		MyHeap mh=new MyHeap(100);
//		
//		mh.insert("30");
//		mh.insert("14");
//		mh.insert("16");
//		mh.insert("40");
//		mh.insert("5");
//		mh.insert("8");
//		mh.insert("19");
//		mh.insert("20");
//		
//	
//		mh.remove();
//		mh.remove();
//		
//		
//		Node[]nn=mh.getNodes();
//		for(int i=1;i<mh.getFirstfree();i++) {
//			System.out.println(nn[i].getWeight());	
//		}
//		
//		
//		
//	}




}
