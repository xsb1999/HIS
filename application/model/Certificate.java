package application.model;

import java.util.List;

public class Certificate {

	private String main;
	private String past;
	private String now;
	private String examine;
	private List<DiseaseType>diseases;
	
	
	
	
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
	}

	public String getNow() {
		return now;
	}
	public void setNow(String now) {
		this.now = now;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public List<DiseaseType> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<DiseaseType> diseases) {
		this.diseases = diseases;
	}



}
