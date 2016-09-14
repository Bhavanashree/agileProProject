$.application.controller('storyController', ["$scope", "crudController", "utils","modelDefService", "validator","$state","actionHelper",
                                               function($scope, crudController,utils, modelDefService, validator,$state,actionHelper) {
	
	 crudController.extend($scope, {
		"name": "Story",
		"modelName": "StoryModel",
		
		"nameColumn" : "title",
		
		"modelDailogId": "storyDialog",
		
		"saveAction": "story.save",
		"readAction": "story.read",
		"updateAction": "story.update",
		"deleteAction": "story.delete",

		});


		 $scope.handleKeyPress = function(e) {
			 console.log("handleKeyPress is invoked " + $scope.inlineTitle);
			 e = e || window.event;
			 var key = e.keyCode ? e.keyCode : e.which;
				  
			 //enter key   
				   if (key == 13) {   
				         $scope.saveBacklog();
				      }
		}

		 $scope.handleKeyForSubtitle = function(e) {
			 
			 var element = $(e.target);
			 console.log("handlekey press 2:   from save substories->  " +  element.val());
			 
			 e = e || window.event;
			 var key = e.keyCode ? e.keyCode : e.which;
			   //enter key   
			   if (key == 13) {  
				   $scope.saveSubstory(element.val());
			   } 
		}
			
		 
		$scope.isDisplayElement = false;
		$scope.newModelMode = true;
		$scope.storyModel = {};
		$scope.model = {};

		 var projectId;
		//set title to model
		$scope.saveBacklog= function(e){
			
			console.log("inlinetext:     " + $scope.inlineTitle );
			
			$scope.model = {"title" : $scope.inlineTitle.trim(),"projectId" : projectId	};
			
		    console.log("model is invoked===model====" + projectId); 

		    console.log("model is invoked===model====" + $scope.model); 
			$scope.initErrors("model", true);
			$scope.newModelMode= 'Save';
			$scope.saveChanges();
			$("#parentStorytextarea").val(null);
		
			$scope.refreshSearch();
		 }

		//autorefresh
		$scope.refreshSearch = function(){
			$scope.$broadcast("invokeSearch", {});
		};
		
		
		$scope.saveSubstory= function(title){
			$scope.model = {
				"title" : title, 
				"parentStoryId": $scope.selectedId,
				"projectId" : projectId	
			};

			$scope.newModelMode= 'Save';
			$scope.saveChanges();
			$scope.refreshSearch();
		};
	
		    	
		  $scope.getActiveIndex = function(){
			  return $scope.selectedIndex;
		  };
		  
		
			var updatefetchStoriesByProjectId = function(readResponse, respConfig){
				console.log("updateStoryByProjectId");
				var model = readResponse.model;
				
				console.log("$scope.searchResults = " + model.title);
				
				try
				{
		    		$scope.$apply();
				}catch(ex)
				{}				
			}
			//
			$scope.fetchStoriesByProjectId = function(){
				
				projectId = $scope.getActiveProject();

				console.log("PROJECT ID = " + projectId);
				if(!projectId) return;
				
				console.log("storyById");

			//	actionHelper.invokeAction("story.readAll", null, null, updatefetchStoriesByProjectId);
				actionHelper.invokeAction("story.storyProjectId", null, {"projectId" : projectId}, updatefetchStoriesByProjectId);				
			};
			
			// Listener for broadcast
			$scope.$on("activeProjectSelectionChanged", function(event, args) {
				projectId = $scope.getActiveProject();
				
				$scope.fetchStoriesByProjectId();
			   
			});
}]);


