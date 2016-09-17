package com.agilepro.commons.models.project;

import com.yukthi.persistence.repository.annotations.Field;
import com.yukthi.webutils.common.AbstractExtendedSearchResult;
import com.yukthi.webutils.common.annotations.Model;
import com.yukthi.webutils.common.annotations.NonDisplayable;

/**
 * The Class StorySearchResult.
 */
@Model
public class StorySearchResult extends AbstractExtendedSearchResult
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
	@Field(value = "storyPoints")
	private Integer storyPoints;

	/**
	 * The parent story id.
	 */
	@Field(value = "parentStoryId")
	private Long parentStoryId;

	/**
	 * The story owner.
	 */
	@NonDisplayable
	@Field(value = "owner.name")
	private String storyOwner;

	/**
	 * The priority id.
	 */
	@Field(value = "priorityId.name")
	private String priorityId;

	/**
	 * The indent.
	 */
	private int indent = 0;

	/**
	 * The project id.
	 **/
	@Field(value = "projectEntity.id")
	private Long projectId;
	
	/**
	 * The status.
	 */
	@Field(value = "status")
	private String status;
	
	/**
	 * Gets the story owner.
	 *
	 * @return the story owner
	 */
	public String getStoryOwner()
	{
		return storyOwner;
	}

	/**
	 * Sets the story owner.
	 *
	 * @param storyOwner
	 *            the new story owner
	 */
	public void setStoryOwner(String storyOwner)
	{
		this.storyOwner = storyOwner;
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
	 * Gets the story points.
	 *
	 * @return the story points
	 */
	public Integer getStoryPoints()
	{
		return storyPoints;
	}

	/**
	 * Sets the story points.
	 *
	 * @param storyPoints the new story points
	 */
	public void setStoryPoints(Integer storyPoints)
	{
		this.storyPoints = storyPoints;
	}

	/**
	 * Gets the parent story id.
	 *
	 * @return the parent story id
	 */
	public Long getParentStoryId()
	{
		return parentStoryId;
	}

	/**
	 * Sets the parent story id.
	 *
	 * @param parentStoryId
	 *            the new parent story id
	 */
	public void setParentStoryId(Long parentStoryId)
	{
		this.parentStoryId = parentStoryId;
	}

	/**
	 * Gets the indent.
	 *
	 * @return the indent
	 */
	public int getIndent()
	{
		return indent;
	}

	/**
	 * Sets the indent.
	 *
	 * @param indent
	 *            the new indent
	 */
	public void setIndent(int indent)
	{
		this.indent = indent;
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

	/**
	 * Gets the priority id.
	 *
	 * @return the priority id
	 */
	public String getPriorityId()
	{
		return priorityId;
	}

	/**
	 * Sets the priority id.
	 *
	 * @param priorityId
	 *            the new priority id
	 */
	public void setPriorityId(String priorityId)
	{
		this.priorityId = priorityId;
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
}
