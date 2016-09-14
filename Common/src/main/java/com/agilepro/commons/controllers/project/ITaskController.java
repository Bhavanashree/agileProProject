package com.agilepro.commons.controllers.project;

import java.util.List;

import com.agilepro.commons.BasicVersionResponse;
import com.agilepro.commons.models.project.StoryAndTaskResult;
import com.agilepro.commons.models.project.TaskModel;
import com.yukthi.webutils.common.RemoteService;
import com.yukthi.webutils.common.models.BaseResponse;
import com.yukthi.webutils.common.models.BasicReadResponse;
import com.yukthi.webutils.common.models.BasicSaveResponse;

/**
 * The Interface IStoryController.
 */
@RemoteService
public interface ITaskController
{

	/**
	 * Save.
	 *
	 * @param model
	 *            the model
	 * @return the basic save response
	 */
	public BasicSaveResponse save(TaskModel model);

	/**
	 * Read.
	 *
	 * @param id
	 *            the id
	 * @return the basic read response
	 */
	public BasicReadResponse<TaskModel> read(Long id);

	public BasicReadResponse<List<TaskModel>> fetchAllStories(Long storyId);

	/**
	 * Update.
	 *
	 * @param model
	 *            the model
	 * @return the base response
	 */
	public BasicVersionResponse update(TaskModel model);

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

}
