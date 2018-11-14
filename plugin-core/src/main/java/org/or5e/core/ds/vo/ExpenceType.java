package org.or5e.core.ds.vo;

public class ExpenceType {
	private Integer id;
	private String name;
	private Boolean status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public void setStatus(String status) {
		setStatus(status.equals("Y"));
	}
}
