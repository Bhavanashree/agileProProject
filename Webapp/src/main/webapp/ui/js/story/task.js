$.application.controller('taskController', ["$scope", "crudController","utils","modelDefService", "validator","$state","actionHelper",
                                            function($scope, crudController,utils, modelDefService, validator,$state,actionHelper) {
	crudController.extend($scope, {
		"name": "Task",
		
		"nameColumn" : "title",
		
		"modelDailogId": "taskDialog",
		
		"saveAction": "task.save",
		"readAction": "task.read",
		"updateAction": "task.update",
		"deleteAction": "task.delete",
	});
	
	 $scope.handleKeyPress = function(e) {
		 
		 var element = $(e.target);
		 console.log("handlekey press 2:   from save substories->  " +  element.val());
	
		 e = e || window.event;
		 var key = e.keyCode ? e.keyCode : e.which;
			  
		 //enter key   
			   if (key == 13) {   
			         $scope.saveTask(element.val());
			      }
	};
	
		$scope.title = "";
		$scope.isDisplayElement = false;
		$scope.newModelMode = true;

		var projectId;
		//set title to model
		$scope.saveTask= function(title){	
			console.log("title model ");
			
			$scope.model = {
					"title" : title,
					"story" : $scope.selectedId		
				};
			
				$scope.newModelMode= 'Save';
				$scope.saveChanges();
			
			document.getElementById('tasktextarea').value=null;
		 }


		  $scope.getActiveIndex = function(){
			  return $scope.selectedIndex;
		  };
		  
		  
			var readStoryCallBack = function(read, response){
				$scope.story = read.model;	
				var index;				
					
				for(index in $scope.story)
				{
					$scope.story[index].dragValue = false;
				}
									
				$scope.$apply();					
			};
				
			
			$scope.listOfStories =function(event){
				 console.log("liststories" );
				 actionHelper.invokeAction("story.readAll",null,null,readStoryCallBack);
			};
				
}]);