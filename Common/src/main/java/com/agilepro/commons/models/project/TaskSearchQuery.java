package com.agilepro.commons.models.project;

import com.yukthi.persistence.repository.annotations.Condition;
import com.yukthi.persistence.repository.annotations.Operator;
import com.yukthi.webutils.common.annotations.Model;

/**
 * The Class TaskSearchQuery.
 */
@Model
public class TaskSearchQuery
{
	/**
	 * The title.
	 **/
	@Condition(value = "title", op = Operator.LIKE, ignoreCase = true)
	private String title;
	
	/**
	 * Instantiates a new backlog search query.
	 */
	public TaskSearchQuery()
	{}

	/**
	 * Instantiates a new backlog search query.
	 *
	 * @param title
	 *            the title
	 * @param description
	 *            the description
	 * @param estimate
	 *            the estimate
	 */
	public TaskSearchQuery(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
}
