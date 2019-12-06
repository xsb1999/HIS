package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.model.Bingzhong;
import application.model.DiseaseType;
import application.model.Diseases;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.BoundedQueue;

public class Administrator2Controller {
	@FXML
	private TextField ttt;
	@FXML
	private TextField t1;
	@FXML
	private TextField t2;
	@FXML
	private Button chuangjian;
	@FXML
	private Button bianli;
	@FXML
	private Button tianjia;
	@FXML
	private Button chaxun;
	@FXML
	private Button qk;
	@FXML
	private TreeView<String> treeview;
	@FXML
	private Label cg;

	
	public void initialize() {
		
//		TreeItem<String> rootItem = new TreeItem<>("Root");
//	    rootItem.setExpanded(true);
//	    treeview.setRoot(rootItem);
//	    
//	    TreeItem<String> item = new TreeItem<>("A");
//	    rootItem.getChildren().add(item);
//	    
//	    TreeItem<String> item1 = new TreeItem<>("B");
//	    TreeItem<String> item2 = new TreeItem<>("c");
//	    item.getChildren().addAll(item1,item2);
	    
		
		Vector<DiseaseType>dd=Bingzhong.read();
		
		if(dd.isEmpty()) {
			return;
		}
		
		DiseaseType rot=new DiseaseType(null,null);
		
		
		
		
//		�ҳ����ڵ�
		for(DiseaseType d:dd) {
			
			if(d.getParentID().equals("-1")) {
	    		rot=d;
	    		break;
	    	}
		}

		CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>(rot.getName());
	    	
	    		treeview.setCellFactory(CheckBoxTreeCell.forTreeView());
	    		treeview.setRoot(rootItem);
	    		
	    		
	    		//���õݹ麯�������ڵ����treeview��	    		
	    		digui(rot,rootItem);
	    		
	}		
	    	
	    		
   
	//	���󷽷�!!!!!!
	
//	    for(DiseaseType d:dd) {
//	    	
//	    	TreeItem<String> Item = new TreeItem<>(d.getName());
//	    	
//	    	
//	    	if(d.getParentID().equals("-1")) {
//	    		
//	    		treeview.setRoot(Item);
//	    	}
//	    	
//	    		Vector<DiseaseType>subroot=d.getSub_diseases();
//	    		
//	    		
//	    		for(DiseaseType dt:subroot) {
//	    			TreeItem<String> Item1 = new TreeItem<>(dt.getName());
//	        		
//	    			Item.getChildren().add(Item1);
//	    		}
//	
//	    }
		
	
	
	
	
	
	//ҽ��������ѡ����
//	public void findIfChecked() {
//		
//		List<DiseaseType>list=Diseases.readDiseases();
//		
//		CheckBoxTreeItem<String> rootItem=(CheckBoxTreeItem<String>) treeview.getRoot();
//		Vector<CheckBoxTreeItem<String>>v=bfs(rootItem);
//		for(CheckBoxTreeItem<String> vv:v) {
//			if(vv.isSelected()) {
//				
//				Vector<DiseaseType> vd=Bingzhong.read();
//				for(DiseaseType d:vd) {
//					if(d.getName().equals(vv.getValue())) {
//						list.add(d);
//						break;
//					}
//				}
//				
//				
//			}	 
//		}
//		
//		
//		OutputStream out;
//		try {
//			out = new FileOutputStream("src/diseases");
//			DataOutputStream dout=new DataOutputStream(out);
//			Gson gson=new Gson();
//			String str=gson.toJson(list);
//			dout.writeUTF(str);
//			dout.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		cg1.setText("��ӳɹ���");
//		
//		
//	}
	

	
	
	//	�ݹ鷽���������нڵ����treeview��
	public void digui(DiseaseType d,CheckBoxTreeItem<String>t) {
		
		if(d.getSub_diseases().isEmpty()) {
			return;
		}
		
		//	���ĳһ���ڵ���ӽڵ㼯��
		Vector<DiseaseType>subroot=d.getSub_diseases();
		
		for(DiseaseType dt:subroot) {
			CheckBoxTreeItem<String> Item1 = new CheckBoxTreeItem<>(dt.getName());
			t.getChildren().add(Item1);
			
			//	�ݹ飬ʹ��ÿһ���ӽڵ���Ϊroot����ȡ�����ӽڵ㼯��
			digui(dt,Item1);
	
		}
	}
	
	
	
	
	
//	��Ӱ�ť
	public void tianjia() {
		
		
		String str1=t1.getText();
		String str2=t2.getText();
		
		
		//�� bingzhong1 �д������еĲ���		
		InputStream in;
		try {
			in = new FileInputStream("src/bingzhong1");
			DataInputStream din=new DataInputStream(in);
			String str11=din.readUTF();
			Gson gson=new Gson();
			Vector<DiseaseType>dd1=gson.fromJson(str11,new TypeToken<Vector<DiseaseType>>() {}.getType() );
			
			
			in = new FileInputStream("src/bingzhong.txt");
			DataInputStream din1=new DataInputStream(in);
			String str111=din1.readUTF();
			Gson gson1=new Gson();
			Vector<DiseaseType>dd=gson1.fromJson(str111,new TypeToken<Vector<DiseaseType>>() {}.getType() );
			

			
			//�ж�str2�Ƿ�Ϊ�գ�Ҫ���Ƿ�Ϊ���� �����жϣ�������null�ж�			
			if(str2.equals("")) {
				for(DiseaseType d:dd1) {
					if(d.getName().equals(str1)) {
						dd.add(d);
						break;
					}
					
				}
			}
			
			
			
			for(DiseaseType d:dd1) {
				
				
				if(d.getName().equals(str1)) {
					for(DiseaseType d1:dd1) {
						if(d1.getName().equals(str2)) {
							d.setParentID(d1.getID());
							dd.add(d);
							break;
						}
					}
					
					
				}
			}
			
			
			Gson g=new Gson(); 
			String s=g.toJson(dd);
			OutputStream out;
			
				out = new FileOutputStream("src/bingzhong.txt");
				DataOutputStream dout=new DataOutputStream(out);
				dout.writeUTF(s);
				dout.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cg.setText("��ӳɹ���");

}	
	
	
	
	
	
//	������ť
	
	
//	���մ����õ�����bingzhong.txt�С��ʼ֪��ÿ���ڵ��parent������������setһ��ÿ���ڵ���ӽڵ㼯��
	public void chuangjian() {
		
		InputStream in;
		try {
			in = new FileInputStream("src/bingzhong.txt");
			DataInputStream din=new DataInputStream(in);
			String str1=din.readUTF();
			Gson gson=new Gson();
			Vector<DiseaseType>dd=gson.fromJson(str1,new TypeToken<Vector<DiseaseType>>() {}.getType() );
			
			//����һ����			
			Vector<DiseaseType>dis=DiseaseType.getTree(dd);
			
			
			Gson g=new Gson(); 
			String s=g.toJson(dis);
			OutputStream out;
			
				out = new FileOutputStream("src/bingzhong.txt");
				DataOutputStream dout=new DataOutputStream(out);
				dout.writeUTF(s);
				dout.close();
				
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		ˢ�½���
		shuaxin();
		
	}
	
	
	
	

//	������ť
	public void bianli() {
		
		
		InputStream in;
		try {
			in = new FileInputStream("src/bingzhong.txt");
			DataInputStream din=new DataInputStream(in);
			String str1=din.readUTF();
			Gson gson=new Gson();
			Vector<DiseaseType>dd=gson.fromJson(str1,new TypeToken<Vector<DiseaseType>>() {}.getType() );
			
			//��ȡ������ȱ������Vector			
			Vector<DiseaseType>d=DiseaseType.bfs(dd);
			
			for(DiseaseType dd1:d) {
				System.out.println(dd1.getName());
			}
			
	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	
//	ˢ��
	public void shuaxin() {
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("administrator/Administrator2.fxml"));
			Scene s1=new Scene(root);
			
			Stage primaryStage=(Stage) chuangjian.getScene().getWindow();

			primaryStage.setScene(s1);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

		
	
	
//	������ȱ��� (add��listҲ������ǰ��)��Ҳ������ͼ�Ĺ�����ȱ���
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
	
	
	
	//��հ�ť	
	public void qingkong() {

		Bingzhong.qingkong();	
		shuaxin();
		
	}
	
	
	
	
	
	
	//��ѯĳһ�����µĲ���
	public void chaxun() {
		String str=ttt.getText();
		Vector<DiseaseType> v=Bingzhong.read();
		DiseaseType.searchPatient(v,str);
	}
	
	
	
	
	
	
	
	
}
