package com.agilepro.persistence.repository.project;

import java.util.List;

import com.agilepro.commons.models.project.StoryAndTaskResult;
import com.agilepro.commons.models.project.TaskSearchQuery;
import com.agilepro.persistence.entity.project.TaskEntity;
import com.agilepro.services.common.TaskSearchCustomizer;
import com.yukthi.persistence.repository.annotations.Condition;
import com.yukthi.persistence.repository.search.SearchQuery;
import com.yukthi.webutils.annotations.RestrictBySpace;
import com.yukthi.webutils.annotations.SearchQueryMethod;
import com.yukthi.webutils.repository.IWebutilsRepository;

/**
 * The Interface ITaskRepository.
 */
public interface ITaskRepository extends IWebutilsRepository<TaskEntity>
{
	/**
	 * Find task.
	 *
	 * @param searchQuery
	 *            the search query
	 * @return the list
	 */
	@RestrictBySpace
	@SearchQueryMethod(name = "taskSearch", queryModel = TaskSearchQuery.class, customizer = TaskSearchCustomizer.class)
	public List<StoryAndTaskResult> findTask(SearchQuery searchQuery);

	@RestrictBySpace
	public List<TaskEntity> fetchAllStories(@Condition(value = "story.id") Long storyId);

	@RestrictBySpace
	public List<TaskEntity> findByStoryId(@Condition(value = "story.id") Long storyId);

	/**
	 * Delete all.
	 */
	public void deleteAll();
}
