package org.or5e.ffmpeg.core;

public abstract class CallbackAdaptor<T> implements CallbackHandler{
	public T ds = null;
	public CallbackAdaptor(T ds) {
		this.ds = ds;
	}
}
