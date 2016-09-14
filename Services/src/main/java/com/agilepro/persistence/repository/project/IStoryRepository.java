package com.agilepro.persistence.repository.project;

import java.util.List;

import com.agilepro.commons.models.project.StoryAndTaskResult;
import com.agilepro.commons.models.project.StorySearchQuery;
import com.agilepro.commons.models.project.StorySearchResult;
import com.agilepro.persistence.entity.project.StoryEntity;
import com.agilepro.services.common.StorySearchCustomizer;
import com.yukthi.persistence.repository.annotations.Condition;
import com.yukthi.persistence.repository.annotations.OrderBy;
import com.yukthi.persistence.repository.search.SearchQuery;
import com.yukthi.webutils.annotations.LovQuery;
import com.yukthi.webutils.annotations.RestrictBySpace;
import com.yukthi.webutils.annotations.SearchQueryMethod;
import com.yukthi.webutils.common.models.ValueLabel;
import com.yukthi.webutils.repository.IWebutilsRepository;

/**
 * The Interface IBackLogRepository for story table.
 */
public interface IStoryRepository extends IWebutilsRepository<StoryEntity>
{
	/**
	 * Find Story.
	 *
	 * @param searchQuery
	 *            the search query
	 * @return the list
	 */
	@RestrictBySpace
	@SearchQueryMethod(name = "storySearch", queryModel = StorySearchQuery.class, customizer = StorySearchCustomizer.class)
	@OrderBy("title")
	public List<StorySearchResult> findStories(SearchQuery searchQuery);

	@RestrictBySpace
	@SearchQueryMethod(name = "storyTaskSearch", queryModel = StorySearchQuery.class)
	public List<StoryAndTaskResult> findByStories(SearchQuery searchQuery);
	
	@RestrictBySpace
	public List<StoryEntity> findByTitle(@Condition(value = "title") String title);
	
	@LovQuery(name = "parentStoryId", valueField = "id", labelField = "title")
	@RestrictBySpace
	public List<ValueLabel> findParentStoryIdLov();

	@RestrictBySpace
	public List<StoryEntity> fetchAllStory(@Condition(value = "title") String storyTitle);
	
	@RestrictBySpace
	public List<StoryEntity> fetchStoryBySprintId(@Condition(value = "sprint.id") Long sprintId);
	
	@RestrictBySpace
	public List<StoryEntity> fetchstoryByProjId(@Condition(value = "projectId.id") Long projectId);

	/**
	 * Delete all.
	 */
	public void deleteAll();
}
