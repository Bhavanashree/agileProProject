package com.agilepro.services.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilepro.commons.models.project.SprintModel;
import com.agilepro.controller.CbillerUserDetails;
import com.agilepro.persistence.entity.project.SprintEntity;
import com.agilepro.persistence.repository.project.ISprintRepository;
import com.agilepro.services.admin.CustomerService;
import com.yukthi.persistence.ITransaction;
import com.yukthi.persistence.repository.RepositoryFactory;
import com.yukthi.utils.exceptions.InvalidStateException;
import com.yukthi.utils.exceptions.NullValueException;
import com.yukthi.webutils.services.BaseCrudService;
import com.yukthi.webutils.services.CurrentUserService;
import com.yukthi.webutils.utils.WebUtils;

/**
 * The Class SprintService is responsible to save,read,update and delete the
 * sprints.
 */
@Service
public class SprintService extends BaseCrudService<SprintEntity, ISprintRepository>
{

	/**
	 * Used to fetch current user info.
	 */
	@Autowired
	private CurrentUserService currentUserService;

	/**
	 * Used to fetch customer info.
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * The repository factory.
	 */
	@Autowired
	private RepositoryFactory repositoryFactory;
	
	/**
	 * Instantiates a new sprint service.
	 */
	public SprintService()
	{
		super(SprintEntity.class, ISprintRepository.class);
	}

	/**
	 * Save.
	 *
	 * @param model
	 *            the model
	 * @return the sprint entity
	 */
	public SprintEntity save(SprintModel model)
	{
		CbillerUserDetails cbiller = (CbillerUserDetails) currentUserService.getCurrentUserDetails();

		Long customerId = cbiller.getCustomerId();

		SprintEntity sprintEntity = WebUtils.convertBean(model, SprintEntity.class);
		customerService.fetch(customerId);

		super.save(sprintEntity, model);

		return sprintEntity;
	}

	/**
	 * Update.
	 *
	 * @param model
	 *            the model
	 * @return the sprint entity
	 */
	public SprintEntity update(SprintModel model)
	{
		if(model == null)
		{
			throw new NullValueException("Sprint Object is null");
		}

		try(ITransaction transaction = repository.newOrExistingTransaction())
		{
			// updating campaign
			SprintEntity sprintEntity = super.update(model);
			transaction.commit();
			return sprintEntity;
		} catch(RuntimeException ex)
		{
			throw ex;
		} catch(Exception ex)
		{
			throw new InvalidStateException(ex, "An error occurred while updating model - {}", model);
		}
	}

	/**
	 * Update sprint.
	 *
	 * @param model the model
	 */
	public void updateSprint(SprintModel model)
	{
		try(ITransaction transaction = repository.newOrExistingTransaction())
		{
			super.repository.findById(model.getId());

			super.update(model);
			transaction.commit();
		} catch(Exception ex)
		{
			throw new IllegalStateException("An error occurred while updating sprintmodel - " + model, ex);
		}
	}

	/**
	 * Fetch sprint.
	 *
	 * @param sprintName
	 *            the sprint name
	 * @return the list
	 */
	public List<SprintModel> fetchAllSprint(String sprintName)
	{
		List<SprintModel> sprintModels = null;

		ISprintRepository sprintRepository = repositoryFactory.getRepository(ISprintRepository.class);
		List<SprintEntity> sprintEntity = sprintRepository.fetchAllSprint(sprintName);

		if(sprintEntity != null)
		{
			sprintModels = new ArrayList<SprintModel>(sprintEntity.size());

			for(SprintEntity entity : sprintEntity)
			{
				sprintModels.add(super.toModel(entity, SprintModel.class));
			}
		}

		return sprintModels;
	}

	/**
	 * Fetch sprint By project id.
	 *
	 * @param projectId the project id
	 * @return the list
	 */
	public List<SprintModel> fetchSprintByProjectId(Long projectId)
	{
		List<SprintModel> sprintModels = null;

		ISprintRepository sprintRepository = repositoryFactory.getRepository(ISprintRepository.class);
		List<SprintEntity> sprintEntity = sprintRepository.fetchsprintByProjId(projectId);

		if(sprintEntity != null)
		{
			sprintModels = new ArrayList<SprintModel>(sprintEntity.size());

			for(SprintEntity entity : sprintEntity)
			{
				sprintModels.add(super.toModel(entity, SprintModel.class));
			}
		}

		return sprintModels;
	}

	/**
	 * Deletes all entities.
	 */
	public void deleteAll()
	{

		repository.deleteAll();
	}
}
