package com.agilepro.commons.models.project;

import com.agilepro.commons.TaskStatus;
import com.yukthi.validation.annotations.MaxLen;
import com.yukthi.validation.annotations.MinLen;
import com.yukthi.validation.annotations.NotEmpty;
import com.yukthi.webutils.common.AbstractExtendableModel;
import com.yukthi.webutils.common.annotations.ExtendableModel;
import com.yukthi.webutils.common.annotations.LOV;
import com.yukthi.webutils.common.annotations.Model;
import com.yukthi.webutils.common.annotations.MultilineText;
import com.yukthi.webutils.common.annotations.NonDisplayable;

/**
 * The Class TaskModel.
 */
@ExtendableModel(name = "Task")
@Model
public class TaskModel extends AbstractExtendableModel
{
	/**
	 * The id.
	 **/
	@NonDisplayable
	private Long id;

	/**
	 * The version.
	 **/
	@NonDisplayable
	private Integer version;

	/**
	 * The name.
	 **/
	@NotEmpty
	@MinLen(3)
	@MaxLen(50)
	private String title;

	/**
	 * The description.
	 **/
	@MaxLen(200)
	@MultilineText
	private String description;

	/**
	 * The timeTaken.
	 **/
	private Long timeTaken;

	/**
	 * The extra time.
	 **/
	private Long extraTime;

	/**
	 * The actual time.
	 **/
	private Long actualTime;

	/**
	 * The owner id.
	 **/
	@LOV(name = "projectMembers")
	private Long ownerId;

	/**
	 * The status.
	 **/
	private TaskStatus status;

	/**
	 * the project id.
	 */
	@NonDisplayable
	private Long projectId;

	/**
	 * The story.
	 **/
	private Long storyId;

	/**
	 * Instantiates a new task model.
	 */
	public TaskModel()
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yukthi.webutils.common.IExtendableModel#getId()
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(Integer version)
	{
		this.version = version;
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

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the owner id.
	 *
	 * @return the owner id
	 */
	public Long getOwnerId()
	{
		return ownerId;
	}

	/**
	 * Sets the owner id.
	 *
	 * @param ownerId
	 *            the new owner id
	 */
	public void setOwnerId(Long ownerId)
	{
		this.ownerId = ownerId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public TaskStatus getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(TaskStatus status)
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
	 * @param projectId
	 *            the new project id
	 */
	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	/**
	 * Gets the story id.
	 *
	 * @return the story id
	 */
	public Long getStoryId()
	{
		return storyId;
	}

	/**
	 * Sets the story id.
	 *
	 * @param storyId
	 *            the new story id
	 */
	public void setStoryId(Long storyId)
	{
		this.storyId = storyId;
	}

	/**
	 * Gets the time taken.
	 *
	 * @return the time taken
	 */
	public Long getTimeTaken()
	{
		return timeTaken;
	}

	/**
	 * Sets the time taken.
	 *
	 * @param timeTaken
	 *            the new time taken
	 */
	public void setTimeTaken(Long timeTaken)
	{
		this.timeTaken = timeTaken;
	}

	/**
	 * Gets the actual time.
	 *
	 * @return the actual time
	 */
	public Long getActualTime()
	{
		return actualTime;
	}

	/**
	 * Sets the actual time.
	 *
	 * @param actualTime
	 *            the new actual time
	 */
	public void setActualTime(Long actualTime)
	{
		this.actualTime = actualTime;
	}

	/**
	 * Gets the extra time.
	 *
	 * @return the extra time
	 */
	public Long getExtraTime()
	{
		return extraTime;
	}

	/**
	 * Sets the extra time.
	 *
	 * @param extraTime
	 *            the new extra time
	 */
	public void setExtraTime(Long extraTime)
	{
		this.extraTime = extraTime;
	}
}
