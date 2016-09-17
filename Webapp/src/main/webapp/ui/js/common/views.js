$.application.config(function($stateProvider, $urlRouterProvider)
{
	//
	// For any unmatched url, redirect to /state1
	$urlRouterProvider.otherwise("default");
	
	var addState = function(name, menuUrl, contentUrl, tabName) {
		var url = "/" + name;
		
		var stateConfig = {
		    "url" : url,
		    "views" :
		    {
		        "menuView" :
		        {
		        	"templateUrl" : "/ui/html/" + menuUrl
		        },
		        "mainContent":
		        {
		        	"templateUrl" : "/ui/html/" + contentUrl
		        }
		    },
		    "tab": tabName
		};
		
		$stateProvider.state(name, stateConfig);
	};
	
	//Home related states
	addState("default", "common/common-menu.html", "common/dashboard.html", "homeTab");
	
	addState("homeCalDayView", "common/common-menu.html", "common/cal-day-view.html", "homeTab");
	addState("homeCalWeekView", "common/common-menu.html", "common/cal-week-view.html", "homeTab");
	addState("homeCalMonthView", "common/common-menu.html", "common/cal-month-view.html", "homeTab");
	
	addState("homeMailInbox", "common/common-menu.html", "common/mails.html", "homeTab");
	//addState("homeMailInbox", "common/common-menu.html", "common/mail-inbox.html", "homeTab");
	addState("homeMailSent", "common/common-menu.html", "common/mail-sent.html", "homeTab");
	addState("homeMailTrash", "common/common-menu.html", "common/mail-trash.html", "homeTab");
	  
	addState("homeNotes", "common/common-menu.html", "common/notes.html", "homeTab");
	addState("homeContacts", "common/common-menu.html", "common/contacts.html", "homeTab");
	addState("homeAlerts", "common/common-menu.html", "common/alerts.html", "homeTab");
	addState("homeNotifications", "common/common-menu.html", "common/notifications.html", "homeTab");
	addState("homeWidgets", "common/common-menu.html", "common/widgets.html", "homeTab");
	
	
	//Customer related states
	addState("customers", "customer/customer-menu.html", "customer/customers.html", "customersTab");
	addState("customerPayments", "customer/customer-menu.html", "customer/payments.html", "customersTab");
	addState("invoice", "customer/customer-menu.html", "customer/invoice.html", "customersTab");

	//Settings related states
	addState("extensions", "settings/settings-menu.html", "settings/extensions.html", "settingsTab");
	addState("adminSettings", "settings/settings-menu.html", "settings/settings.html", "settingsTab");
	addState("pricePlans", "settings/settings-menu.html", "settings/price-plans.html", "settingsTab");
	addState("adminUser", "settings/settings-menu.html", "settings/adminuser.html", "settingsTab");
	
	//Resources related states
	addState("employee", "resources/resources-menu.html", "resources/employee.html", "resourcesTab");
	addState("designation","resources/resources-menu.html", "resources/designation.html", "resourcesTab");
	
	// Project related states
	addState("project","project/project-menu.html", "project/project.html", "projectsTab");
	addState("tag","project/project-menu.html", "project/tag.html", "projectsTab");
	
	//Members related states
	addState("member", "member/member-menu.html", "member/member.html", "membersTab");
	
	//backlog
	addState("sprint","story/sprints-menu.html", "story/sprint.html", "sprintTab");
	addState("story","story/story-menu.html", "story/story.html", "sprintTab");
	addState("priority","story/sprints-menu.html", "story/priority.html", "sprintTab");
	addState("task","story/story-menu.html", "story/task.html", "sprintTab");
	
});
