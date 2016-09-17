package com.agilepro.commons.models.project;

import java.util.Date;

import com.yukthi.persistence.repository.annotations.Condition;
import com.yukthi.persistence.repository.annotations.Operator;
import com.yukthi.webutils.common.annotations.Model;

/**
 * The Class SprintSearchQuery.
 */
@Model
public class SprintSearchQuery
{
	/**
	 * The name.
	 **/
	@Condition(value = "name", op = Operator.LIKE, ignoreCase = true)
	private String name;

	/**
	 * The description.
	 **/
	@Condition(value = "description", op = Operator.LIKE, ignoreCase = true)
	private String description;

	/**
	 * The start date.
	 **/
	@Condition(value = "startDate", op = Operator.GE)
	private Date startDate;

	/**
	 * The end date.
	 **/
	@Condition(value = "endDate", op = Operator.LE)
	private Date endDate;

	/**
	 * Instantiates a new sprint search query.
	 *
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 * @param endDate
	 *            the end date
	 * @param startDate
	 *            the start date
	 */
	public SprintSearchQuery(String name, String description, Date endDate, Date startDate)
	{
		this.name = name;
		this.description = description;
		this.endDate = endDate;
		this.startDate = startDate;
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
}
