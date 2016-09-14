package com.agilepro.controller.project;

import static com.agilepro.commons.IAgileproActions.ACTION_PREFIX_TASK;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_DELETE;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_DELETE_ALL;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_READ;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_READ_STORY_ID;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_SAVE;
import static com.agilepro.commons.IAgileproActions.ACTION_TYPE_UPDATE;
import static com.agilepro.commons.IAgileproActions.PARAM_ID;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agilepro.commons.BasicVersionResponse;
import com.agilepro.commons.UserRole;
import com.agilepro.commons.controllers.project.ITaskController;
import com.agilepro.commons.models.project.TaskModel;
import com.agilepro.persistence.entity.project.TaskEntity;
import com.agilepro.services.common.Authorization;
import com.agilepro.services.project.TaskService;
import com.yukthi.webutils.InvalidRequestParameterException;
import com.yukthi.webutils.annotations.ActionName;
import com.yukthi.webutils.common.models.BaseResponse;
import com.yukthi.webutils.common.models.BasicReadResponse;
import com.yukthi.webutils.common.models.BasicSaveResponse;
import com.yukthi.webutils.controllers.BaseController;

/**
 * The Class TaskController.
 */
@RestController
@ActionName(ACTION_PREFIX_TASK)
@RequestMapping("/task")
public class TaskController extends BaseController implements ITaskController
{

	/**
	 * The task service.
	 **/
	@Autowired
	private TaskService taskService;

	/**
	 * Save the TaskModel.
	 *
	 * @param model
	 *            TaskModel
	 * @return the TaskModel save response
	 */
	@Override
	@ActionName(ACTION_TYPE_SAVE)
	@Authorization(roles = { UserRole.TASK_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public BasicSaveResponse save(@RequestBody @Valid TaskModel model)
	{

		TaskEntity entity = taskService.save(model);

		return new BasicSaveResponse(entity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.agilepro.commons.controllers.project.ITaskController#read(java.lang.
	 * Long)
	 */
	@Override
	@ActionName(ACTION_TYPE_READ)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.TASK_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/read/{" + PARAM_ID + "}", method = RequestMethod.GET)
	@ResponseBody
	public BasicReadResponse<TaskModel> read(@PathVariable(PARAM_ID) Long id)
	{
		TaskModel taskModel = taskService.fetchFullModel(id, TaskModel.class);

		return new BasicReadResponse<TaskModel>(taskModel);
	}

	@Override
	@ActionName(ACTION_TYPE_READ_STORY_ID)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.TASK_EDIT, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/readStoryId", method = RequestMethod.GET)
	@ResponseBody
	public BasicReadResponse<List<TaskModel>> fetchAllStories(@RequestParam(value = "storyId", required = true) Long storyId)
	{
		return new BasicReadResponse<List<TaskModel>>(taskService.fetchAllStories(storyId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agilepro.commons.controllers.project.ITaskController#update(com.
	 * agilepro.commons.models.project.TaskModel)
	 */
	@ActionName(ACTION_TYPE_UPDATE)
	@Authorization(entityIdExpression = "parameters[0].id", roles = { UserRole.TASK_UPDATE, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public BasicVersionResponse update(@RequestBody @Valid TaskModel model)
	{
		if(model.getId() == null || model.getId() <= 0)
		{
			throw new InvalidRequestParameterException("Invalid id specified for update: " + model.getId());
		}

		Integer updatedVersion = taskService.updateTaskModel(model);

		return new BasicVersionResponse(updatedVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.agilepro.commons.controllers.project.ITaskController#delete(java.lang
	 * .Long)
	 */
	@ActionName(ACTION_TYPE_DELETE)
	@Authorization(entityIdExpression = "parameters[0]", roles = { UserRole.TASK_DELETE, UserRole.CUSTOMER_SUPER_USER })
	@RequestMapping(value = "/delete/{" + PARAM_ID + "}", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse delete(@PathVariable(PARAM_ID) Long id)
	{
		taskService.deleteTask(id);

		return new BaseResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agilepro.commons.controllers.project.ITaskController#deleteAll()
	 */
	@Authorization(roles = { UserRole.TASK_DELETE_ALL, UserRole.CUSTOMER_SUPER_USER })
	@ActionName(ACTION_TYPE_DELETE_ALL)
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse deleteAll()
	{
		taskService.deleteAll();
		return new BaseResponse();
	}
}