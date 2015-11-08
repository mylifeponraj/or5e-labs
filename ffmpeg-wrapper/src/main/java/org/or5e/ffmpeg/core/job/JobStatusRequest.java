package org.or5e.ffmpeg.core.job;

public class JobStatusRequest {
	public String jobID = null;
	public String jobStatus = null;
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
}
