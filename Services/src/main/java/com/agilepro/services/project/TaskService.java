package com.agilepro.services.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilepro.commons.models.project.StoryAndTaskResult;
import com.agilepro.commons.models.project.TaskModel;
import com.agilepro.controller.CbillerUserDetails;
import com.agilepro.persistence.entity.project.TaskEntity;
import com.agilepro.persistence.repository.project.ITaskRepository;
import com.agilepro.services.admin.CustomerService;
import com.yukthi.persistence.ITransaction;
import com.yukthi.utils.exceptions.InvalidStateException;
import com.yukthi.utils.exceptions.NullValueException;
import com.yukthi.webutils.InvalidRequestParameterException;
import com.yukthi.webutils.services.BaseCrudService;
import com.yukthi.webutils.services.CurrentUserService;
import com.yukthi.webutils.utils.WebUtils;

/**
 * The Class TaskService.
 */
@Service
public class TaskService extends BaseCrudService<TaskEntity, ITaskRepository>
{

	/**
	 * The current user service.
	 **/
	@Autowired
	private CurrentUserService currentUserService;

	/**
	 * Used to fetch customer info.
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * The story repo.
	 **/
	private ITaskRepository taskRepo;

	/**
	 * Instantiates a new sprint service.
	 */
	public TaskService()
	{
		super(TaskEntity.class, ITaskRepository.class);
	}

	/**
	 * Initialize the iprojectMemberRepository.
	 */
	@PostConstruct
	private void init()
	{
		taskRepo = repositoryFactory.getRepository(ITaskRepository.class);
	}

	/**
	 * Save.
	 *
	 * @param model
	 *            the model
	 * @return the sprint entity
	 */
	public TaskEntity save(TaskModel model)
	{
		CbillerUserDetails cbiller = (CbillerUserDetails) currentUserService.getCurrentUserDetails();

		Long customerId = cbiller.getCustomerId();

		TaskEntity taskEntity = WebUtils.convertBean(model, TaskEntity.class);
		customerService.fetch(customerId);

		super.save(taskEntity, model);

		return taskEntity;
	}

	/**
	 * Update.
	 *
	 * @param model
	 *            the model
	 * @return the sprint entity
	 */
	public TaskEntity updateTask(TaskModel model)
	{
		if(model == null)
		{
			throw new NullValueException("task Object is null");
		}

		try(ITransaction transaction = repository.newOrExistingTransaction())
		{
			// updating campaign
			TaskEntity taskEntity = super.repository.findById(model.getId());
			transaction.commit();
			return taskEntity;
		} catch(RuntimeException ex)
		{
			throw ex;
		} catch(Exception ex)
		{
			throw new InvalidStateException(ex, "An error occurred while updating model - {}", model);
		}
	}

	/**
	 * Update task model.
	 *
	 * @param model
	 *            the model
	 * @return the integer
	 */
	public Integer updateTaskModel(TaskModel model)
	{
		try(ITransaction transaction = repository.newOrExistingTransaction())
		{
			Long timeTaken = model.getTimeTaken();
			Long modelExtraTime = model.getExtraTime();
			if(timeTaken == null)
			{
				model.setTimeTaken(modelExtraTime);
			}
			else
			{
				model.setTimeTaken(timeTaken + modelExtraTime);
			}
			System.out.println(" +++++++++******************************************" + timeTaken + " " + modelExtraTime);

			super.update(model);
			transaction.commit();

			TaskEntity updateEntity = super.fetch(model.getId());

			return updateEntity.getVersion();
		} catch(Exception ex)
		{
			throw new IllegalStateException("An error occurred while updating tasks - " + model, ex);
		}
	}

	/**
	 * Fetch all stories.
	 *
	 * @param storyId
	 *            the story id
	 * @return the list
	 */
	public List<TaskModel> fetchAllStories(Long storyId)
	{
		List<TaskModel> taskmodel = null;
		taskRepo = repositoryFactory.getRepository(ITaskRepository.class);
		List<TaskEntity> taskentity = taskRepo.fetchAllStories(storyId);
		System.out.println(" hello        --------------->" + storyId);
		if(taskentity != null)
		{
			taskmodel = new ArrayList<TaskModel>(taskentity.size());
			for(TaskEntity entity : taskentity)
			{
				taskmodel.add(super.toModel(entity, TaskModel.class));
			}
		}

		return taskmodel;
	}

	/**
	 * Search by story.
	 *
	 * @param storyId
	 *            the story id
	 * @return the list
	 */
	public List<StoryAndTaskResult> searchByStory(Long storyId)
	{
		List<StoryAndTaskResult> storiesmodel = null;

		taskRepo = repositoryFactory.getRepository(ITaskRepository.class);
		List<TaskEntity> taskEntity = taskRepo.findByStoryId(storyId);

		if(taskEntity != null)
		{

			storiesmodel = new ArrayList<StoryAndTaskResult>((taskEntity.size()));
			System.out.println("taskservicessssssss" + storyId);

			for(TaskEntity task : taskEntity)
			{
				StoryAndTaskResult storyandTask = new StoryAndTaskResult(task.getTitle(), task.getId());
				storiesmodel.add(storyandTask);
				System.out.println("in loop " + storyandTask);
			}
		}
		return storiesmodel;
	}

	/**
	 * Delete task.
	 *
	 * @param TaskId
	 *            the task id
	 */
	public void deleteTask(long TaskId)
	{
		try(ITransaction transaction = repository.newOrExistingTransaction())
		{

			TaskEntity taskEntity = super.repository.findById(TaskId);

			if(taskEntity == null)
			{
				throw new InvalidRequestParameterException("Invalid taskId id specified - " + TaskId);
			}

			Long tskId = taskEntity.getId();

			// delete backlogEntity
			super.deleteById(taskEntity.getId());

			transaction.commit();
		} catch(Exception ex)
		{
			throw new IllegalStateException("An error occurred while deleting task - " + TaskId, ex);
		}
	}

	/**
	 * Deletes all entities.
	 */
	public void deleteAll()
	{
		repository.deleteAll();
	}
}
