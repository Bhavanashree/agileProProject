package com.agilepro.commons.controllers.project;

import java.util.List;

import com.agilepro.commons.models.project.StoryModel;
import com.yukthi.webutils.common.RemoteService;
import com.yukthi.webutils.common.models.BaseResponse;
import com.yukthi.webutils.common.models.BasicReadResponse;
import com.yukthi.webutils.common.models.BasicSaveResponse;

/**
 * The Interface IStoryController.
 */
@RemoteService
public interface IStoryController
{

	/**
	 * Save.
	 *
	 * @param model
	 *            the model
	 * @return the basic save response
	 */
	public BasicSaveResponse save(StoryModel model);

	/**
	 * Read.
	 *
	 * @param id
	 *            the id
	 * @return the basic read response
	 */
	public BasicReadResponse<StoryModel> read(Long id);

	/**
	 * Fetch all story.
	 *
	 * @param projectId
	 *            the project id
	 * @return the basic read response
	 */
	public BasicReadResponse<List<StoryModel>> fetchAllStory(Long projectId);

	/**
	 * Update.
	 *
	 * @param model
	 *            the model
	 * @return the base response
	 */
	public BaseResponse update(StoryModel model);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return the base response
	 */
	public BaseResponse delete(Long id);

	/**
	 * Delete all.
	 *
	 * @return the base response
	 */
	public BaseResponse deleteAll();

	/**
	 * Fetch story by sprint id.
	 *
	 * @param sprintId
	 *            the sprint id
	 * @return the basic read response
	 */
	public BasicReadResponse<List<StoryModel>> fetchStoryBySprintId(Long sprintId);

	/**
	 * Fetchstory by proj id.
	 *
	 * @param projectId
	 *            the project id
	 * @return the basic read response
	 */
	public BasicReadResponse<List<StoryModel>> fetchstoryByProjId(Long projectId);
}
