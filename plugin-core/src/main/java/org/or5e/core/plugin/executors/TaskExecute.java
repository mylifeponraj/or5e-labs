package org.or5e.core.plugin.executors;

public interface TaskExecute<R, P> {
	public R executeTask(P param);
}
