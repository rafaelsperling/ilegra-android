package com.example.lancamentodehoras.domain;

public class Horas {
	private String id;
	private String data;
	private String dataFinal;
	private String desc;

	public Horas(String id, String data, String dataFinal, String desc) {
		super();
		this.id = id;
		this.data = data;
		this.dataFinal = dataFinal;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
