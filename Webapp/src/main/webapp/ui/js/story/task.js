$.application.controller('taskController', ["$scope", "crudController","utils","modelDefService", "validator","$state","actionHelper",
                                            function($scope, crudController,utils, modelDefService, validator,$state,actionHelper) {
	crudController.extend($scope, {
		"name": "Task",
		"modelName": "TaskModel",
		
		"nameColumn" : "title",
		
		"modelDailogId": "taskDialog",
		
		"saveAction": "task.save",
		"readAction": "task.read",
		"updateAction": "task.update",
		"deleteAction": "task.delete",
	});
	
	 $scope.tskNmAtulTmMap = {};
	
	 $scope.handleKeyPress = function(e) {
		 
		 var element = $(e.target);
		 
		 console.log("handlekey press 2:   from save task->  " +  element.attr('name'));
	
		 e = e || window.event;
		 var key = e.keyCode ? e.keyCode : e.which;
		
		 $scope.tskNmAtulTmMap[element.attr('name')] = element.val();
		//enter key   
		   if (key == 13) 
		   {	 
			   if($scope.tskNmAtulTmMap["taskName"] && $scope.tskNmAtulTmMap["actualTime"])
				{
				  $scope.saveTask($scope.tskNmAtulTmMap["taskName"], $scope.tskNmAtulTmMap["actualTime"]);
				}
			   else
				{
				   // show alert for both
				   return;
				}
		   }
	};
	
	 $scope.handleKeyPressForEdit = function(e) {
		 
		 var element = $(e.target);
		 
		 console.log("handleKeyPress is invoked " +  element.val());
		 
		 e = e || window.event;
		 var key = e.keyCode ? e.keyCode : e.which;
		 //enter key   
		   if (key == 13) 
		   {  
			   var object = JSON.parse(element.attr('id')); 
			   
			   object[element.attr('name')] = element.val();
			   
			   console.log( object[element.attr('name')] , "check1" ,   object[element.attr('name')] = element.val() );
		       $scope.taskEdit(object);
		   }
	};
	
		$scope.title = "";
		$scope.isDisplayElement = false;
		$scope.newModelMode = true;
		$scope.tskIdTskVrsn ={};
		$scope.strIdTskListMap = {};
		var selectedStoryId;
		var selectedStatusId;
		$scope.taskObj = {};
		var taskSelectedId;
		
		$scope.plusImage = "/ui/images/toggle-small-expand.png";
		$scope.minusImage = "/ui/images/toggle-small.png";

		var projectId;
		//set title to model
		$scope.saveTask= function(title, actualTime){	
			console.log("aprjtid " , projectId);
			$scope.model = {
					"title" : title,
					"storyId" : selectedStoryId,	
					"status" : "NOT_STARTED",
					"actualTime": actualTime,
					"projectId" : projectId
				};
			
			
			$scope.newModelMode= 'Save';
			$scope.saveChanges();
			$("#tasktextarea").val(null);
			$("#actualTime").val(null);

		 }

		//selected index
		  $scope.getActiveIndex = function(){
			  
			  selectedStoryId = $scope.selectedIndex;
			  console.log("getIndexOfstory from dis" , selectedStoryId);
			  console.log("slectedindex-----" , $scope.selectedIndex );
			  
			  return $scope.selectedIndex;
		  };
		  
	  
		  
		var readStoryCallBack = function(read, response){
			
			$scope.strIdTskListMap[selectedStoryId] = read.model; 
			console.log("map------------>>>>>>", $scope.strIdTskListMap[selectedStoryId] );
			$scope.task = read.model;
			
			var index;				
			
			for(index in $scope.task)
			{
					 $scope.task[index].story;
			}
								
			$scope.$apply();					
		};
				
			
		$scope.listOfStories =function(obj){
			
			 console.log("liststories" );
			 obj.booleanVal = !obj.booleanVal ;
			 
			 selectedStoryId = obj.id;
			 actionHelper.invokeAction("task.readStoryId",null,{"storyId" : selectedStoryId},readStoryCallBack);
		};
		
		
		var taskIdTemp;
		
		
		//part 3	
		var updateTasktitle = function(readResponse, respConfig)
		{	
			$scope.tskIdTskVrsn[taskIdTemp] = readResponse.version;
			
			if($scope.taskObj.extraTime == null)
			{
				return null;
			}
			
			if( $scope.taskObj.timeTaken == null )
			{
				$scope.taskObj.timeTaken = Number($scope.taskObj.extraTime);
				console.log("aa " + $scope.taskObj.timeTaken);
			}
			else
			{
			$scope.taskObj.timeTaken = Number($scope.taskObj.timeTaken)+ Number($scope.taskObj.extraTime);
			}
			$("#extraTime").val(null);
			try
			{
	    		$scope.$apply();
			}catch(ex)
			{}		
		}
		
		$scope.taskEdit = function(obj){
			
			console.log(obj);
			$scope.taskObj = obj;
			console.log($scope.taskObj);
			if(obj.title.length == 0 )
			{
				// show alert
				return;
			}
		
			taskIdTemp = obj.id;
			
			if($scope.tskIdTskVrsn[taskIdTemp])
			{
				obj.version = $scope.tskIdTskVrsn[taskIdTemp];
			}

			actionHelper.invokeAction("task.update", obj, null,updateTasktitle);
		};
		
		$scope.fetchTasksByProjectId = function(){
			
			projectId = $scope.getActiveProject();

			console.log("PROJECT ID = " + projectId);
			if(!projectId) return;
						
		};
		
		$scope.$on("activeProjectSelectionChanged", function(event, args) {
			$scope.fetchTasksByProjectId();
		   
		});
		
		$scope.deleteTask = function(object){
			$scope.taskObj1= object;
			taskSelectedId = $scope.taskObj1;
			$scope.selectedId = taskSelectedId;
			$scope.deleteEntry(taskSelectedId);	
		}
}]);