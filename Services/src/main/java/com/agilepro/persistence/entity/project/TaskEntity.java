package com.agilepro.persistence.entity.project;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agilepro.commons.TaskStatus;
import com.agilepro.commons.models.project.StoryModel;
import com.agilepro.commons.models.project.TaskModel;
import com.agilepro.persistence.entity.admin.EmployeeEntity;
import com.agilepro.persistence.entity.admin.ProjectEntity;
import com.yukthi.persistence.annotations.DataType;
import com.yukthi.persistence.annotations.DataTypeMapping;
import com.yukthi.utils.annotations.PropertyMapping;
import com.yukthi.webutils.annotations.ExtendableEntity;
import com.yukthi.webutils.repository.WebutilsExtendableEntity;

/**
 * Maintains the Task created by Teams.
 * 
 * @author Bhavana.
 */
@ExtendableEntity(name = "Task")
@Table(name = "TASK")
public class TaskEntity extends WebutilsExtendableEntity
{
	/**
	 * The task title.
	 **/
	@Column(name = "TITLE", length = 50, nullable = false)
	private String title;

	/**
	 * The task description.
	 **/
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * The estimation of time.
	 **/
	@Column(name = "TIME_TAKEN")
	private Long timeTaken;
	/**
	 * The project id.
	 */
	@Column(name = "PROJECT_ID")
	@ManyToOne
	@PropertyMapping(type = StoryModel.class, from = "projectId", subproperty = "id")
	private ProjectEntity projectId;

	/**
	 * The owner.
	 **/
	@ManyToOne
	@PropertyMapping(type = TaskModel.class, from = "ownerId", subproperty = "id")
	@Column(name = "TASK_OWNER_ID")
	private EmployeeEntity owner;

	/**
	 * The status.
	 **/
	@Column(name = "STATUS")
	@DataTypeMapping(type = DataType.STRING)
	private TaskStatus status;

	/**
	 * The actualTime.
	 **/
	@Column(name = "ACTUAL_TIME")
	private Long actualTime;

	/**
	 * list of stories.
	 */
	@Column(name = "STORY_ID")
	@ManyToOne
	@PropertyMapping(type = TaskModel.class, from = "storyId", subproperty = "id")
	private StoryEntity story;

	/**
	 * Instantiates a new task entity.
	 */
	public TaskEntity()
	{}

	/**
	 * Instantiates a new task entity.
	 *
	 * @param title
	 *            the title
	 * @param description
	 *            the description
	 * @param timeTaken
	 *            the time taken
	 * @param actualTime
	 *            the actual time
	 */
	public TaskEntity(String title, String description, Long timeTaken, Long actualTime)
	{
		this.title = title;
		this.description = description;
		this.timeTaken = timeTaken;
		this.actualTime = actualTime;
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
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public ProjectEntity getProjectId()
	{
		return projectId;
	}

	/**
	 * Sets the project id.
	 *
	 * @param projectId
	 *            the new project id
	 */
	public void setProjectId(ProjectEntity projectId)
	{
		this.projectId = projectId;
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public EmployeeEntity getOwner()
	{
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner
	 *            the new owner
	 */
	public void setOwner(EmployeeEntity owner)
	{
		this.owner = owner;
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
	 * Gets the story.
	 *
	 * @return the story
	 */
	public StoryEntity getStory()
	{
		return story;
	}

	/**
	 * Sets the story.
	 *
	 * @param story
	 *            the new story
	 */
	public void setStory(StoryEntity story)
	{
		this.story = story;
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

	public Long getActualTime()
	{
		return actualTime;
	}

	public void setActualTime(Long actualTime)
	{
		this.actualTime = actualTime;
	}

}
