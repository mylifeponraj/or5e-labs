package org.or5e.ffmpeg.core.job;

import java.util.HashMap;
import java.util.Map;

public class JobMaster {
	private static JobMaster _master = null;
	private Map<String, JobRequest> _jobRequest = null;
	private Map<String, JobRequest> _completedJobRequest = null;

	public JobMaster() {
		this._jobRequest = new HashMap<String, JobRequest>();
		this._completedJobRequest = new HashMap<>();
	}
	public static JobMaster getJobMaster() {
		if(_master == null) _master = new JobMaster();
		return _master;
	}

	public JobRequest getJobInformation(String jobID) {
		if(this._jobRequest.get(jobID) != null) {
			return this._jobRequest.get(jobID);
		}
		else if(this._completedJobRequest.get(jobID) != null) {
			return this._completedJobRequest.get(jobID);
		}
		return null;
	}

	public Boolean addJob(JobRequest request) {
		if(this._jobRequest.containsKey(request.getJobID())) {
			return false;
		}
		this._jobRequest.put(request.getJobID(), request);
		return Boolean.TRUE;
	}

	public JobRequest retriveJob(String jobID) {
		if(this._jobRequest.containsKey(jobID)) {
			return this._jobRequest.get(jobID);
		}
		return null;
	}

	public Boolean updateJob(String jobID, JobRequest request) {
		JobRequest jobRequest = this._jobRequest.get(jobID);
		if(jobRequest != null) {
			this._jobRequest.remove(jobID);
			this._jobRequest.put(jobID, request);
		}
		else {
			addJob(request);
		}
		return Boolean.TRUE;
	}

	public Boolean completeJob(String jobID, String status) {
		if(!this._jobRequest.containsKey(jobID)) {
			return false;
		}

		JobRequest request = this._jobRequest.get(jobID);
		request.setStatus(status);

		this._completedJobRequest.put(jobID, request);
		this._jobRequest.remove(jobID);
		return Boolean.TRUE;
	}
}