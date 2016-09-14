package com.agilepro.commons.models.project;

import com.agilepro.commons.StoryStatus;
import com.yukthi.persistence.repository.annotations.Condition;
import com.yukthi.persistence.repository.annotations.Operator;
import com.yukthi.webutils.common.annotations.IgnoreField;
import com.yukthi.webutils.common.annotations.LOV;
import com.yukthi.webutils.common.annotations.Model;
import com.yukthi.webutils.common.annotations.NonDisplayable;

/**
 * The Class StorySearchQuery.
 */
@Model
public class StorySearchQuery
{

	/**
	 * The title.
	 **/
	@Condition(value = "title", op = Operator.LIKE, ignoreCase = true)
	private String title;

	/**
	 * The status.
	 */
	@NonDisplayable
	@IgnoreField
	@Condition(value = "status")
	private StoryStatus status;
	
	/**
	 *  The project id. 
	 **/
	@NonDisplayable
	@Condition(value = "projectId.id", op = Operator.EQ)
	private Long projectId;
	
	@Condition(value = "ownerId.name", op = Operator.EQ)
	private Long ownerId;
	/**
	 * Instantiates a new backlog search query.
	 */
	public StorySearchQuery()
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
	public StorySearchQuery(String title, String description, Integer estimate)
	{
		this.title = title;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the name.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public StoryStatus getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(StoryStatus status)
	{
		this.status = status;
	}

	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public Long getProjectId()
	{
		return projectId;
	}

	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public Long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(Long ownerId)
	{
		this.ownerId = ownerId;
	}
}
