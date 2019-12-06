package application.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import com.google.gson.Gson;

import logic.BoundedQueue;


public class DiseaseType {
	private String ID; // 编号
	private String name; //名字   
	private String ParentID= "-1"; //上一级病的ID
	private Vector<DiseaseType> sub_diseases=new Vector<>(); //存储该病种下的子病种类型(所有子节点)
	private Vector<Patient> patient=new Vector<>();//存储得有该病的所有患者ID（叶节点中有病人列表）
	
	
	

	public DiseaseType(String iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}


	public DiseaseType(String iD, String name, String parentID) {
		this.ID = iD;
		this.name = name;
		this.ParentID = parentID;
	}
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentID() {
		return ParentID;
	}
	public void setParentID(String parentID) {
		ParentID = parentID;
	}
	public Vector<DiseaseType> getSub_diseases() {
		return sub_diseases;
	}
	public void setSub_diseases(Vector<DiseaseType> sub_diseases) {
		this.sub_diseases = sub_diseases;
	}
	




	public Vector<Patient> getPatient() {
		return patient;
	}


	public void setPatient(Vector<Patient> patient) {
		this.patient = patient;
	}


	// 创建树
	public static Vector<DiseaseType> getTree(Vector<DiseaseType> Dvector) {
		
        // 遍历节点列表
        for (DiseaseType node : Dvector) {
            if (node.getParentID().equals("-1")) {
                // parentID为-1（根节点）作为入口
            	node.setSub_diseases(getChildrenNode(node.getID(), Dvector));
            	
            }
        }
        return Dvector;
    }

	
	
	
    // 获取子节点集合的递归方法
    public static Vector<DiseaseType> getChildrenNode(String id, Vector<DiseaseType> Dvector) {
    	Vector<DiseaseType> vector = new Vector<>();
        for (DiseaseType node : Dvector) {
            if (node.getParentID().equals(id)) {
                // 递归获取子节点
            	node.setSub_diseases(getChildrenNode(node.getID(), Dvector));
            	vector.add(node);
            }
        }
        return vector;
    }

	
    
    
  // 递归遍历树
    public static void tranverse(Vector<DiseaseType>v2) {
    		
    	//BoundedStack stack=new BoundedStack(20);

    	for(DiseaseType d2:v2) {
    		if(d2.getParentID().equals("-1")) {
    			
    			
    			recursive(d2);
    			break;
    		}
    	}
	
    }
    // 遍历树时的递归方法  
	public static void recursive (DiseaseType node) {	
		
		if(node!=null) {
    		System.out.println(node.getName());

    		Vector<DiseaseType>v11=node.getSub_diseases();
    		for(DiseaseType d1:v11) {
    			//递归遍历
    			recursive(d1);
    		}
    		
    	}
	
	}

    
    
	

	
	
	//广度优先遍历
	public static Vector<DiseaseType> bfs(Vector<DiseaseType>v2) {
		
		DiseaseType dd=new DiseaseType(null,null);
		for(DiseaseType d2:v2) {
    		if(d2.getParentID().equals("-1")) {		
    			dd=d2;
    			break;	
    		}
    	}
		
		BoundedQueue q=new BoundedQueue(20,0,0);
		
		Vector<DiseaseType>v=new Vector<>();
		
		q.insert(dd);
		v.add(dd);
		
		while(!(q.isEmpty())) {
			
			DiseaseType d=(DiseaseType) q.getfront();
			q.remove();
			
			for(DiseaseType di:d.getSub_diseases()) {
				if(!v.contains(di)) {
					v.add(di);
					q.insert(di);
				}
				
			}		
		}
		return v;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 查询某一病种下的所有病人
	
	public static void searchPatient(Vector<DiseaseType>v,String name) {
		
		// Vector<DiseaseType>vb=new Vector<>();
		
		
		for(DiseaseType d:v) {
			if(d.getName().equals(name)) {
				rec(d);
			}
		}
		
		
	}
	
	
	// 递归查询某一个大的病种下的所有病种及病人
	private static void rec(DiseaseType node) {
		//Vector<Patient>vp=new Vector<Patient>();
		
		if(node.getSub_diseases().isEmpty()) {
			
			System.out.println(node.getName());
			
			Vector<Patient>vp=node.getPatient();
			System.out.println(vp.isEmpty());
			
			for(Patient p:vp) {
				System.out.println(p.getId()+"   "+p.getName());
			}
			
			
			System.out.println("==================");
			return;
		}
		
		
		else {
			System.out.println(node.getName());
			
			System.out.println("==================");
			
			Vector<DiseaseType>v11=node.getSub_diseases();
					
    		for(DiseaseType d1:v11) {
    			rec(d1);
    		}
		}
		
		
	}
	
	
	
	
	
	
	
 
	public static void main(String[]args) {
		
		Vector<DiseaseType> v1=Bingzhong.read();
		
		
		
		searchPatient(v1,"内科");
		
		
	}
	
	
	

}
