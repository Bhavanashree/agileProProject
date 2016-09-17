package com.agilepro.controller.project;

import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_DELETE;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_DELETE_ALL;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_READ_ALL;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_READ;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_SAVE;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_UPDATE;
import static com.agilepro.commons.IAgileproActions.ACTION_PREFIX_STORY;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_READ_STORY_SPRINT;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_READ_STORY_PROJECT_ID;
import static com.agilepro.commons.IAgileproActions.PARAM_ID;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agilepro.commons.UserRole;
import com.agilepro.commons.controllers.project.IStoryController;
import com.agilepro.commons.models.project.StoryModel;
import com.agilepro.persistence.entity.project.StoryEntity;
import com.agilepro.services.common.Authorization;
import com.agilepro.services.project.StoryService;
import com.yukthi.webutils.InvalidRequestParameterException;
import com.yukthi.webutils.annotations.ActionName;
import com.yukthi.webutils.common.models.BaseResponse;
import com.yukthi.webutils.common.models.BasicReadResponse;
import com.yukthi.webutils.common.models.BasicSaveResponse;
import com.yukthi.webutils.controllers.BaseController;

/**
 * The Class StoryController is responsible for receiving the requests from
 * Client. Once received , it directs the request to the service class
 * (StoryService). It also takes care for sending the response back to the
 * client received from service class.
 */
@RestController
@ActionName(ACTION_PREFIX_STORY)
@RequestMapping("/story")
public class StoryController extends BaseController implements IStoryController
{

	/**
	 * The logger.
	 **/
	private static Logger logger = LogManager.getLogger(StoryController.class);

	/**
	 * The story service.
	 **/
	@Autowired
	private StoryService storyService;

	/**
	 * Save the StoryModel.
	 *
	 * @param model
	 *            StoryModel
	 * @return the StoryModel save response
	 */
	@Override
	@ActionName(ACTION_TYPE_SAVE)
	@Authorization(roles = { UserRole.BACKLOG_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public BasicSaveResponse save(@RequestBody @Valid StoryModel model)
	{

		StoryEntity entity = storyService.saveStory(model);

		return new BasicSaveResponse(entity.getId());
	}

	/**
	 * Read the Story.
	 *
	 * @param id
	 *            id of StoryModel
	 * 
	 * @return the StoryModel read response
	 */
	@Override
	@ActionName(ACTION_TYPE_READ)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.BACKLOG_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/read/{" + PARAM_ID + "}", method = RequestMethod.GET)
	@ResponseBody
	public BasicReadResponse<StoryModel> read(@PathVariable(PARAM_ID) Long id)
	{
		StoryModel storyModel = storyService.fetchFullModel(id, StoryModel.class);

		return new BasicReadResponse<StoryModel>(storyModel);
	}
	
	/**
	 * Read the list of stories.
	 *
	 * @param storyTitle the story title
	 * @return the StoryModel read response
	 */
	@Override
	@ActionName(ACTION_TYPE_READ_ALL)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.BACKLOG_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/readAll", method = RequestMethod.GET)
	@ResponseBody
	public BasicReadResponse<List<StoryModel>> fetchAllStory(@RequestParam(value = "projectId", required = true) Long projectId)
	{
		return new BasicReadResponse<List<StoryModel>>(storyService.fetchAllStory(projectId));
	}
	
	/**
	 * Fetch story by project id.
	 *
	 * @param projectId the project id
	 * @return the basic read response
	 */
	@ActionName(ACTION_TYPE_READ_STORY_PROJECT_ID)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.BACKLOG_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/storyProjectId", method = RequestMethod.GET)
	@ResponseBody
	public BasicReadResponse<List<StoryModel>> fetchstoryByProjId(@RequestParam(value = "projectId", required = true) Long projectId) 
	{
		return new BasicReadResponse<List<StoryModel>>(storyService.fetchStories(projectId));
	}

	/* (non-Javadoc)
	 * @see com.agilepro.commons.controllers.project.IStoryController#fetchStoryBySprintId(java.lang.Long)
	 */
	@Override
	@ActionName(ACTION_TYPE_READ_STORY_SPRINT)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.BACKLOG_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/readStoriesBySprint", method = RequestMethod.GET)
	@ResponseBody
	public BasicReadResponse<List<StoryModel>> fetchStoryBySprintId(@RequestParam(value = "sprintId", required = true) Long sprintId)
	{

		return new BasicReadResponse<List<StoryModel>>(storyService.fetchStoryBySprintId(sprintId));
	}

	/**
	 * Update the stories.
	 *
	 * @param model
	 *            id of StoryModel
	 * 
	 * @return the StoryModel response
	 */
	@ActionName(ACTION_TYPE_UPDATE)
	@Authorization(entityIdExpression = "parameters[0].id", roles = { UserRole.BACKLOG_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse update(@RequestBody @Valid StoryModel model)
	{
		logger.trace("Recieved request to update ", model.getId());
		if(model.getId() == null || model.getId() <= 0)
		{
			throw new InvalidRequestParameterException("Invalid id specified for update: " + model.getId());
		}

		storyService.updateStories(model);

		return new BaseResponse();
	}

	/**
	 * Delete the story.
	 *
	 * @param id
	 *            the id of story
	 * 
	 * @return the story save response
	 */

	@ActionName(ACTION_TYPE_DELETE)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.BACKLOG_DELETE, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/delete/{" + PARAM_ID + "}", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse delete(@PathVariable(PARAM_ID) Long id)
	{
		storyService.deleteParentId(id);

		return new BaseResponse();
	}

	/**
	 * Delete all story.
	 *
	 * @return the base response
	 */
	@Authorization(roles = { UserRole.BACKLOG_DELETE_ALL, UserRole.CUSTOMER_SUPER_USER })
	@ActionName(ACTION_TYPE_DELETE_ALL)
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse deleteAll()
	{
		storyService.deleteAll();
		return new BaseResponse();
	}
}
