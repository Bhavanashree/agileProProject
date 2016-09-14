package com.agilepro.commons;

import com.yukthi.webutils.common.annotations.Label;

/**
 * The Enum TaskStatus.
 */
public enum TaskStatus
{

	/**
	 * Indicates task is not started.
	 */
	@Label("Not started") NOT_STARTED,

	/**
	 * Indicates task is in progress.
	 */
	@Label("In Progress") IN_PROGRESS,

	/**
	 * The completed.
	 */
	@Label("Completed") COMPLETED
}
