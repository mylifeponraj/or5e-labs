package org.or5e.ffmpeg.core.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.or5e.core.BaseObject;
import org.or5e.ffmpeg.core.CallbackHandler;
import org.or5e.ffmpeg.core.FFMPEG;

public class FFMPEGWorkerThread extends BaseObject implements Runnable {
	private Socket _clientSocket = null;
	private String _serverMessage = null;
	private JobRequest _request = null;

	public FFMPEGWorkerThread(Socket _clientSocket, String _serverMessage) {
		this._clientSocket = _clientSocket;
		this._serverMessage = _serverMessage;
	}

	@Override public void run() {
		try {
			InputStream input = this._clientSocket.getInputStream();
			OutputStream output = this._clientSocket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
			ObjectMapper mapper = new ObjectMapper();

			String command = reader.readLine();
			if(command.equals("NewJob")) {
				processJobRequest(reader, writer, mapper);
			}
			else if(command.equals("JobStatus")) {
				processJobStatusQuery(reader, writer, mapper);
			}
			writer.close();
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void processJobStatusQuery(BufferedReader reader, BufferedWriter writer, ObjectMapper mapper) 
			throws IOException, JsonGenerationException, JsonMappingException {
		String jobID = reader.readLine();
		JobRequest jobRequest = JobMaster.getJobMaster().retriveJob(jobID);
		String response = mapper.writeValueAsString(jobRequest);
		writer.write(response);
	}

	private void processJobRequest(BufferedReader reader, BufferedWriter writer, ObjectMapper mapper)
			throws IOException, JsonParseException, JsonMappingException {
		info("Waiting for job input from cluster...");
		String jobRequestInString = reader.readLine();
		this._request = mapper.readValue(jobRequestInString, JobRequest.class);
		if(_request != null) {
			JobMaster master = JobMaster.getJobMaster();
			master.addJob(_request);
			FFMPEG ffmpeg = new FFMPEG("C:\\ffmpeg\\bin\\ffmpeg.exe", Boolean.TRUE);
			ffmpeg.addSource(_request.getSrcFileName());
			ffmpeg.addDestination(_request.getDistFileName());
			writer.write("About to Start Executing the Job: "+_request.getJobID());
			ffmpeg.execute(new CallbackHandler() {
				@Override public void outputReceived(String readLine, Integer lineNumner) {
					System.out.println(readLine);
					_request.setDistFileName("");
				}
			});
		}
		else {
			writer.write("Request is not received properly (or) it is not in the proper format.");
		}
	}

	@Override
	public String getName() {
		return "FFMPEGWorkerThread";
	}

}
