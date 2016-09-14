package com.agilepro.commons.models.project;

import com.yukthi.persistence.repository.annotations.Field;
import com.yukthi.webutils.common.AbstractExtendedSearchResult;
import com.yukthi.webutils.common.annotations.Model;
import com.yukthi.webutils.common.annotations.NonDisplayable;

/**
 * The Class TaskSearchResult.
 */
@Model
public class TaskSearchResult extends AbstractExtendedSearchResult
{
	/**
	 * Id.
	 */
	@NonDisplayable
	@Field(value = "id")
	private long id;

	/**
	 * Title of the story.
	 */
	@Field(value = "title")
	private String title;

	/**
	 * description of story.
	 */
	@Field(value = "description")
	private String description;

	/**
	 * Estimation.
	 */
	@Field(value = "actualTime")
	private Long actualTime;

	/**
	 * The status.
	 */
	@Field(value = "status")
	private String status;

	/**
	 * The time taken.
	 **/
	@Field(value = "timeTaken")
	private Long timeTaken;

	/**
	 * The extra time.
	 **/
	@Field(value = "extraTime")
	private Long extraTime;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id)
	{
		this.id = id;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	public Long getActualTime()
	{
		return actualTime;
	}

	public void setActualTime(Long actualTime)
	{
		this.actualTime = actualTime;
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

	public Long getExtraTime()
	{
		return extraTime;
	}

	public void setExtraTime(Long extraTime)
	{
		this.extraTime = extraTime;
	}
}
