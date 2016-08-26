package com.agilepro.services.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilepro.commons.models.project.StoryAndTaskResult;
import com.agilepro.commons.models.project.StoryModel;
import com.agilepro.commons.models.project.TaskModel;
import com.agilepro.controller.CbillerUserDetails;
import com.agilepro.persistence.entity.project.StoryEntity;
import com.agilepro.persistence.entity.project.TaskEntity;
import com.agilepro.persistence.repository.project.IStoryRepository;
import com.agilepro.persistence.repository.project.ITaskRepository;
import com.agilepro.services.admin.CustomerService;
import com.yukthi.persistence.ITransaction;
import com.yukthi.utils.exceptions.InvalidStateException;
import com.yukthi.utils.exceptions.NullValueException;
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
	 * 
	 * /** Used to fetch current user info.
	 */
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
	 * Initialize the iprojectMemberRepository.
	 */
	@PostConstruct
	private void init()
	{
		taskRepo = repositoryFactory.getRepository(ITaskRepository.class);
	}

	/**
	 * Instantiates a new sprint service.
	 */
	public TaskService()
	{
		super(TaskEntity.class, ITaskRepository.class);
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
	public TaskEntity update(TaskModel model)
	{
		if(model == null)
		{
			throw new NullValueException("task Object is null");
		}

		try(ITransaction transaction = repository.newOrExistingTransaction())
		{
			// updating campaign
			TaskEntity taskEntity = super.update(model);
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

	public List<TaskModel> fetchAllStories(Long storyId)
	{
		List<TaskModel> taskmodel = null;
		taskRepo = repositoryFactory.getRepository(ITaskRepository.class);
		List<TaskEntity> taskentity = taskRepo.fetchAllStories(storyId);
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
	 * Deletes all entities.
	 */
	public void deleteAll()
	{
		repository.deleteAll();
	}
}
