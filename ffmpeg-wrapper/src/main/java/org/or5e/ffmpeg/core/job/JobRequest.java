package org.or5e.ffmpeg.core.job;

public class JobRequest {
	public String jobID = null;
	public String srcFileName = null;
	public String distFileName = null;
	public String status = null;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public String getSrcFileName() {
		return srcFileName;
	}
	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}
	public String getDistFileName() {
		return distFileName;
	}
	public void setDistFileName(String distFileName) {
		this.distFileName = distFileName;
	}
	
}