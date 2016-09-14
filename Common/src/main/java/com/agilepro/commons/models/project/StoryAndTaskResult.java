package com.agilepro.commons.models.project;

import com.agilepro.commons.TaskSearchStatus;
import com.yukthi.persistence.repository.annotations.Field;
import com.yukthi.webutils.common.AbstractExtendedSearchResult;
import com.yukthi.webutils.common.annotations.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class StoryAndTaskResult.
 */
@Model
public class StoryAndTaskResult extends AbstractExtendedSearchResult
{

	/**
	 * The id.
	 **/
	@Field(value = "id")
	private long id;

	/**
	 * The title.
	 **/
	@Field(value = "title")
	private String title;

	/**
	 * The type.
	 **/
	private TaskSearchStatus type;
	
	/**
	 * Instantiates a new story and task result.
	 */
	public StoryAndTaskResult()
	{}

	/**
	 * Instantiates a new story and task result.
	 *
	 * @param title
	 *            the title
	 * @param id
	 *            the id
	 */
	public StoryAndTaskResult(String title, Long id)
	{
		this.title = title;
		this.id = id;
	}

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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TaskSearchStatus getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(TaskSearchStatus type)
	{
		this.type = type;
	}
}
