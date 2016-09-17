package com.agilepro.commons.models.project;

import java.util.Date;
import java.util.List;
import com.yukthi.validation.annotations.MaxLen;
import com.yukthi.validation.annotations.MinLen;
import com.yukthi.validation.annotations.Required;
import com.yukthi.webutils.common.AbstractExtendableModel;
import com.yukthi.webutils.common.annotations.ExtendableModel;
import com.yukthi.webutils.common.annotations.IgnoreField;
import com.yukthi.webutils.common.annotations.Model;
import com.yukthi.webutils.common.annotations.MultilineText;
import com.yukthi.webutils.common.annotations.NonDisplayable;

/**
 * The Class SprintModel.
 */
@ExtendableModel(name = "Sprint")
@Model
public class SprintModel extends AbstractExtendableModel
{
	/**
	 * The Sprint Id.
	 **/
	@NonDisplayable
	private Long id;

	/**
	 * The version.
	 **/
	@NonDisplayable
	private Integer version;

	/**
	 * The name of the Sprint.
	 **/
	@MinLen(3)
	@MaxLen(50)
	@Required
	private String name;

	/**
	 * The description of the sprint.
	 **/
	@MaxLen(200)
	@MultilineText
	private String description;

	/**
	 * The start date of sprint.
	 */
	@Required
	private Date startDate;

	/**
	 * The end date of sprint.
	 */
	@Required
	private Date endDate;

	/**
	 * The list of stories under this sprint.  
	 * */
	@NonDisplayable
	@IgnoreField
	private List<Long> stories;
	
	/**
	 * projectd.
	 */
	@NonDisplayable
	private Long projectId;

	/**
	 * Instantiates a new sprint model.
	 *
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 */
	public SprintModel(String name, String description, Date startDate, Date endDate)
	{
		this.name = name;
		this.endDate = endDate;
		this.startDate = startDate;
		this.description = description;
	}

	/**
	 * Instantiates a new sprint model.
	 */
	public SprintModel()
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the stories.
	 *
	 * @return the stories
	 */
	public List<Long> getStories()
	{
		return stories;
	}

	/**
	 * Sets the stories.
	 *
	 * @param stories
	 *            the new stories
	 */
	public void setStories(List<Long> stories)
	{
		this.stories = stories;
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
}