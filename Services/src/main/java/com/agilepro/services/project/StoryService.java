package com.agilepro.services.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilepro.commons.models.admin.EmployeeModel;
import com.agilepro.commons.models.customer.ProjectMemberModel;
import com.agilepro.commons.models.project.StoryAndTaskResult;
import com.agilepro.commons.models.project.StoryModel;
import com.agilepro.persistence.entity.admin.DesignationEntity;
import com.agilepro.persistence.entity.admin.ProjectMemberEntity;
import com.agilepro.persistence.entity.project.StoryEntity;
import com.agilepro.persistence.entity.project.TaskEntity;
import com.agilepro.persistence.repository.project.IStoryRepository;
import com.yukthi.persistence.ITransaction;
import com.yukthi.persistence.repository.RepositoryFactory;
import com.yukthi.utils.exceptions.InvalidStateException;
import com.yukthi.utils.exceptions.NullValueException;
import com.yukthi.webutils.InvalidRequestParameterException;
import com.yukthi.webutils.services.BaseCrudService;

/**
 * The Class StoryService is responsible to save,read,update and delete the
 * stories.
 */
@Service
public class StoryService extends BaseCrudService<StoryEntity, IStoryRepository>
{

	/**
	 * The repository factory.
	 */
	@Autowired
	private RepositoryFactory repositoryFactory;

	/**
	 * The story repo.
	 **/
	private IStoryRepository storyRepo;

	/**
	 * Instantiates a new StoryService.
	 */
	public StoryService()
	{
		super(StoryEntity.class, IStoryRepository.class);
	}

	/**
	 * Initialize the iprojectMemberRepository.
	 */
	@PostConstruct
	private void init()
	{
		storyRepo = repositoryFactory.getRepository(IStoryRepository.class);
	}

	/**
	 * Save story.
	 *
	 * @param model
	 *            the model
	 * @return the story entity
	 */
	public StoryEntity saveStory(StoryModel model)
	{
		try(ITransaction transaction = repository.newOrExistingTransaction())
		{

			StoryEntity entity = super.save(model);

			transaction.commit();

			return entity;
		} catch(Exception ex)
		{

			throw new IllegalStateException("An error occurred while saving  story - " + model, ex);
		}
	}

	/**
	 * Update stories.
	 *
	 * @param model
	 *            the model
	 * @return the story entity
	 */
	public StoryEntity updateStories(StoryModel model)
	{
		if(model == null)
		{
			throw new NullValueException("StoryModel Object is null");
		}

		try(ITransaction transaction = repository.newOrExistingTransaction())
		{
			// updating campaign
			StoryEntity story = super.update(model);

			transaction.commit();
			return story;
		} catch(RuntimeException ex)
		{
			throw ex;
		} catch(Exception ex)
		{
			throw new InvalidStateException(ex, "An error occurred while updating model - {}", model);
		}
	}

	/**
	 * Fetch all story.
	 *
	 * @param storyTitle
	 *            the story title
	 * @return the list
	 */
	public List<StoryModel> fetchAllStory(String storyTitle)
	{
		List<StoryModel> storymodel = null;
		storyRepo = repositoryFactory.getRepository(IStoryRepository.class);
		List<StoryEntity> storyEntity = storyRepo.fetchAllStory(storyTitle);
		if(storyEntity != null)
		{
			storymodel = new ArrayList<StoryModel>(storyEntity.size());
			for(StoryEntity entity : storyEntity)
			{
				storymodel.add(super.toModel(entity, StoryModel.class));
			}
		}

		return storymodel;
	}

	/**
	 * Fetch story by sprint id.
	 *
	 * @param sprintId
	 *            the sprint id
	 * @return the list
	 */
	public List<StoryModel> fetchStoryBySprintId(Long sprintId)
	{
		List<StoryModel> storymodel = null;
		storyRepo = repositoryFactory.getRepository(IStoryRepository.class);

		List<StoryEntity> storyEntity = storyRepo.fetchStoryBySprintId(sprintId);
		if(storyEntity != null)
		{
			storymodel = new ArrayList<StoryModel>(storyEntity.size());
			for(StoryEntity entity : storyEntity)
			{
				storymodel.add(super.toModel(entity, StoryModel.class));
			}
		}

		return storymodel;
	}

	/**
	 * fetch a stories by project id.
	 *
	 * @param projectId
	 *            the project id
	 * @return the list
	 */
	public List<StoryModel> fetchStories(Long projectId)
	{
		List<StoryEntity> storyEntity = storyRepo.fetchstoryByProjId(projectId);

		List<StoryModel> storiesmodel = new ArrayList<StoryModel>();

		StoryModel storyModel;
		for(StoryEntity stories : storyEntity)
		{
			storyModel = super.toModel(stories, StoryModel.class);
			storiesmodel.add(storyModel);
		}
		return storiesmodel;
	}

	public List<StoryAndTaskResult> searchByTitle(String title)
	{
		List<StoryEntity> storyEntities = storyRepo.findByTitle(title);
		List<StoryAndTaskResult> storiesmodel = new ArrayList<StoryAndTaskResult>();
		for(StoryEntity story : storyEntities)
		{
			StoryAndTaskResult storyandTask = new StoryAndTaskResult(story.getTitle(), story.getId());
			storiesmodel.add(storyandTask);
			System.out.println("in loop " + storyandTask);

		}
		return storiesmodel;
	}

	/**
	 * Delete expense.
	 *
	 * @param parentStoryId
	 *            the parentStoryId id
	 */
	public void deleteParentId(long parentStoryId)
	{
		try(ITransaction transaction = repository.newOrExistingTransaction())
		{

			StoryEntity backlogEntity = super.repository.findById(parentStoryId);

			if(backlogEntity == null)
			{
				throw new InvalidRequestParameterException("Invalid parentStoryId id specified - " + parentStoryId);
			}

			Long parentId = backlogEntity.getParentStoryId();

			// delete backlogEntity
			super.deleteById(backlogEntity.getId());

			transaction.commit();
		} catch(Exception ex)
		{
			throw new IllegalStateException("An error occurred while deleting expenses - " + parentStoryId, ex);
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