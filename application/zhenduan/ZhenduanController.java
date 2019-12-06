package application.zhenduan;

import javafx.fxml.FXML;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;

import application.model.Bingzhong;
import application.model.DiseaseType;
import application.model.Diseases;
import application.model.Doctor;
import application.model.TomJimKim;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import logic.BoundedQueue;

public class ZhenduanController {
	@FXML
	private TreeView<String> treeview;
	@FXML
	private Label lab;
	@FXML
	private Label keshi;
	
	
	public void initialize() {

		
		//找出当前医生
		List<String>list=TomJimKim.read();
		String str=null;
		int i=list.size();
		int c=1;
		for(String a:list) {
			if(c==i) {
			str=a;
			break;
			}
			c++;
		}
		
		String roots=null;
		List<Doctor>listd=Doctor.readD();
		for(Doctor dd:listd) {
			if(dd.getName().equals(str)) {
				roots=dd.getDepartment();
				break;
			}
		}
		
		keshi.setText(roots);
		
		Vector<DiseaseType>dd=Bingzhong.read();
		
		if(dd.isEmpty()) {
			return;
		}
		
		DiseaseType rot=new DiseaseType(null,null);


//		找出根节点
		for(DiseaseType d:dd) {
			
			if(d.getName().equals(roots)) {
	    		rot=d;
	    		break;
	    	}
		}

		CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>(rot.getName());
	    	
	    		treeview.setCellFactory(CheckBoxTreeCell.forTreeView());
	    		treeview.setRoot(rootItem);
	    		
	    		
	    		//调用递归函数来将节点加入treeview中	    		
	    		digui(rot,rootItem);
	    		
	}
	
	
	
	
	
	
	
	
	//医生给病人选择病种
	public void findIfChecked() {
		
		List<DiseaseType>list=Diseases.readDiseases();
		
		CheckBoxTreeItem<String> rootItem=(CheckBoxTreeItem<String>) treeview.getRoot();
		Vector<CheckBoxTreeItem<String>>v=bfs(rootItem);
		for(CheckBoxTreeItem<String> vv:v) {
			if(vv.isSelected()) {
				
				Vector<DiseaseType> vd=Bingzhong.read();
				for(DiseaseType d:vd) {
					if(d.getName().equals(vv.getValue())) {
						list.add(d);
						break;
					}
				}
				
				
			}	 
		}
		
		
		OutputStream out;
		try {
			out = new FileOutputStream("src/diseases");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(list);
			dout.writeUTF(str);
			dout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		lab.setText("添加成功！");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	递归方法，将所有节点加入treeview中
	public void digui(DiseaseType d,CheckBoxTreeItem<String>t) {
		
		if(d.getSub_diseases().isEmpty()) {
			return;
		}
		
		//	获得某一个节点的子节点集合
		Vector<DiseaseType>subroot=d.getSub_diseases();
		
		for(DiseaseType dt:subroot) {
			CheckBoxTreeItem<String> Item1 = new CheckBoxTreeItem<>(dt.getName());
			t.getChildren().add(Item1);
			
			//	递归，使用每一个子节点作为root来获取它的子节点集合
			digui(dt,Item1);
	
		}
	}
	
	
	
	
	
	
//	广度优先遍历 (add进list也可以在前面)，也适用于图的广度优先遍历
	public static Vector<CheckBoxTreeItem<String>> bfs(CheckBoxTreeItem<String> rootItem) {
		
		Vector<CheckBoxTreeItem<String>>v=new Vector<>();
		
		BoundedQueue q=new BoundedQueue(20,0,0);
		
		CheckBoxTreeItem<String>temp=new CheckBoxTreeItem<String>();
		
		q.insert(rootItem);
		v.add(rootItem);
		
		while(!q.isEmpty()) {
			
			CheckBoxTreeItem<String> d=(CheckBoxTreeItem<String>) q.getfront();
			q.remove();
			
			if(!d.getChildren().isEmpty()) {
				for(TreeItem<String> di:d.getChildren()) {
					if(!v.contains(di)) {
						v.add((CheckBoxTreeItem<String>) di);
						q.insert((CheckBoxTreeItem<String>) di);
					}
					
				}
			}
					
		}
		return v;
	}
	
	
	
	
	
	
	
	
	
	
	
}
