$.application.controller('sprintController', ["$scope", "crudController", "utils","modelDefService","actionHelper",
                                               function($scope, crudController,utils, modelDefService, actionHelper) {
	 crudController.extend($scope, {
		"name": "Sprint",
		"modelName": "SprintModel",
		
		"nameColumn" : "name",
		
		"modelDailogId": "sprintDialog",
		
		"saveAction": "sprint.save",
		"readAction": "sprint.read",
		"updateAction": "sprint.update",
		"deleteAction": "sprint.delete",

		});

	
	 var selectedStory =[];
	 
	 var draggingBacklogObj;
	 
	 $scope.sprints = [];
	 
	 var selectedSprintObj;
	 
	 var projectId;
	 
	 
		$scope.newSprintMode = true;	
		$scope.sprintmodel;
		
		$scope.saveSprint = function(e) {
			
			$scope.model.projectId = projectId;
			
			$scope.saveChanges(e);
		};

		var readSprintCallBack = function(read, response){
			$scope.sprint = read.model;
			
			var index;
			
			var sprintId;
			
				for(index in $scope.sprint)
				{
					sprintName = $scope.sprint[index].name;
					sprintId = $scope.sprint[index].id;
				}
				
				$scope.$apply();
		};
		
		
		$scope.listOfSprint= function(sprintName){	
			console.log("listofsprint");
			actionHelper.invokeAction("sprint.readAll", null, null, readSprintCallBack);
		 };
			 	
		 //List of stories
		var readStoryCallBack = function(read, response){
			
			$scope.story = read.model;		
			
			var index;
			
			var fileId;
				
			for(index in $scope.story)
			{
				if($scope.story[index].photo)
				{
					fileId =  $scope.story[index].photo.fileId;
					
					$scope.story[index].photoUrl = actionHelper.actionUrl("files.fetch", {"id": fileId});
		
				//	$scope.story[index].dragValue = false;
				}
			}
			
			$scope.$apply();					
		};
			
		
		$scope.listOfStories =function(){
			
			 projectId = $scope.getActiveProject();
			 actionHelper.invokeAction("story.readAll",null,{"projectId" : projectId},readStoryCallBack);
		};
		
		// editSprint 
		$scope.editSprint = function(obj){		
			//obj  = JSON.parse($scope.selectedSprintObj);
			obj = $scope.selectedSprintObj;
			console.log(obj.id);
			$scope.selectedId = obj.id;
			$scope.editEntry(obj);
		};
					
		//drag and drop	1
		$scope.onSelectStories = function(backlog) {
			backlog.dragValue = true;
			selectedStory.push(backlog);
		};
		
		var saveCallBack = function(read, response){
			console.log("savecallback");
		 };
		
		 //saving sprint with  stories
		$scope.onSprintDrop = function(event){	
			event.preventDefault();
			console.log("invoked __________+++++++++onDropBacklog" , selectedStory[index].photo);

			for(index in selectedStory)
			{
				var model = {
						"id" : selectedStory[index].id,
						"title" : selectedStory[index].title,
						"version" : selectedStory[index].version,
						"description" : selectedStory[index].description,
						"estimate" : selectedStory[index].estimate,
						"parentStoryId" : selectedStory[index].parentStoryId, 
						"ownerId" : selectedStory[index].ownerId,
						"status" : "IN_PROGRESS",
						"priority" :selectedStory[index].priority,
						"sprint" : selectedSprintObj.id,
						"projectId" :selectedStory[index].projectId,
						"photo" : selectedStory[index].photo
				};

				console.log("invokedonSprintDrop prjtid " , projectId);
				// action helper update for stories
				actionHelper.invokeAction("story.update", model, null, saveCallBack);
			}
			// loop for update in ui
			for(index in selectedStory)
			{
				$scope.sprints.push(selectedStory[index]);
			}
			
			selectedStory = [];
			console.log("onSprintDrop invoked" + $scope.sprints.length);
			$scope.$apply();
			console.log(" test1", model );
			
		};
			
				
		var displaySprintCallBack  = function(read, response)
		{
			
			var backLogModels  = read.model;
			var backLogModel;
			var index;
			var i = -1;
			
			for(index in backLogModels)
			{
				
				backLogModel = backLogModels[index];
			//	$scope.inProgress =  backLogModels[index].photo.photoUrl;
				
				switch(backLogModel.status)
				{
					case 'IN_PROGRESS':
						$scope.inProgress.push(backLogModel);
						break;
					case 'NOT_STARTED':
						 $scope.notStarted.push(backLogModel);
						break;
					case 'COMPLETED':
						$scope.completed.push(backLogModel);
						
				}
				
				for(i = 0 ; i < $scope.story.length ; i++)
				{
					if($scope.story[i].id == backLogModel.id)
					{
						$scope.story.splice(i, 1);
					}
				}
			}	
			
			try
			{
				$scope.$apply()
			}catch(ex)
			{}
		};
		

		$scope.displaySprint = function(selectedObject){
			
			console.log(selectedObject);
			
			if(!selectedObject)
			{
				return;
			}
			
			$scope.selectedSprintObj = selectedObject;
			
			$scope.inProgress = [];
			 
			$scope.notStarted = [];
			 
			$scope.completed = [];
				
			selectedSprintObj = $scope.selectedSprintObj; 
			
			
			console.log(selectedSprintObj);
			
			 console.log("list2sprints" ,  $scope.selectedSprintObj);
			 // invoke backlog 
			actionHelper.invokeAction("story.readStoriesBySprint",null,{"sprintId" : selectedSprintObj.id},displaySprintCallBack);
					 
		};
		
		var updateEditCallBack = function(read, response){
			
			$scope.displaySprint();
		};	
		
		// on drag method for stories
		$scope.onDragStories = function(event){
			event.originalEvent.dataTransfer.setData('text/plain', 'text');
			if(!$scope.selectedSprintObj)
			{
				utils.alert("Please select sprint");
			}
			$scope.selectedStoryForDrag = JSON.parse(event.target.id);
			console.log($scope.selectedStoryForDrag);
			
			
			
		};
		//comman method for save status and sprintId to stories
		
		sprintUpdateAfterDrop = function(status)
		{
			$scope.selectedStoryForDrag.status = status;
			
			$scope.selectedStoryForDrag.sprint = $scope.selectedSprintObj.id;
			
			actionHelper.invokeAction("story.update", $scope.selectedStoryForDrag, null, updateEditCallBack);	
		}
		
		//functions of Drag to Not started
		$scope.allowDropOnNotStarted = function(event){
			console.log("area for allowDropOnNotStarted");
			event.preventDefault();
		};
		
		$scope.onDropofNotStarted = function(event){
			
			console.log("invoked onDropofNotStarted ", $scope.selectedSprintObj);
			event.preventDefault();
			
			sprintUpdateAfterDrop("NOT_STARTED");	
			
			$scope.$apply();
			
		};	
		

		//functions of Drag to IN progress
		$scope.dragToProgress = function(event){
			event.originalEvent.dataTransfer.setData('text/plain', 'text');
			
			console.log( "id", event.target.id);
			draggingBacklogObj = JSON.parse(event.target.id);
		};
	
		//dropping 2	
		$scope.allowDropOnProgress = function(event){
			console.log("area for allowDropOnProgress");
			event.preventDefault();
		};
		
		$scope.onDropofInProgress = function(event){
			event.preventDefault();
			
			sprintUpdateAfterDrop("IN_PROGRESS");	
				
			$scope.$apply();
			
		};
	
		// drop  to completed
		$scope.dragToCompleted= function(event){
	
			console.log("drag to dragToCompleted");
			event.originalEvent.dataTransfer.setData('text/plain', 'text');
			
			console.log(" id", event.target.id);
			draggingBacklogObj = JSON.parse(event.target.id);
		};
	
		//dropping 2	
		$scope.allowDropOnCompleted = function(event){
			console.log("area for allowDropOndragToCompleted");
			 event.preventDefault();
		};
		
		$scope.onDropofCompleted = function(event){
			console.log("invoked onDropofCompleted ");
			event.preventDefault();
			sprintUpdateAfterDrop("COMPLETED");	
	
			$scope.$apply();		
		};	
		
		$scope.hideLeftMenu = function(){
			$scope.common_hideLeftMenu();
		};
		
		// Listener for broadcast
		$scope.$on("activeProjectSelectionChanged", function(event, args) {	
			
			console.log("braod cast is working");
			$scope.listOfStories(); 
		});
		
		// sprint active
		
		 $scope.getActiveSprint = function(){
			 console.log(); 
		  };		  
}]);		