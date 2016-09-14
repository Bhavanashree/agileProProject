$.application.controller('priorityController', ["$scope", "crudController", function($scope, crudController) {
	crudController.extend($scope, {
		"name": "Priority",
		
		"nameColumn" : "name",
		
		"modelDailogId": "priorityDialog",
		"saveAction": "priority.save",
		"readAction": "priority.read",
		"updateAction": "priority.update",
		"deleteAction": "priority.delete",
	});
	
	
}]);