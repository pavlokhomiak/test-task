Based on : Spring Boot Spring Data + Hibernate H2 database (file mode) 

1. Spring Security -> Three roles (USER/ADMIN/OWNER) 

	1.1. Login/Registration as a user (encode password in db) 
	
	1.2. Add/remove ADMIN rights to/from users can only OWNER (we need to hardcode one OWNER in db) 
	
	1.3. Owner can block users. So they will not have a possibility to call APIs 

2. Data Model User->OneToOne->Account->OneToMany->Orders (simple data, few fields -> productName,amount etc) 
	
	2.1. User can create an order and get/update/delete own. (Can not update/delete orders of other users) 
	2.2. Admin/Owner can get/update/delete all orders 

3. Add Hibernate 2 level cache + Query cache 

4. Create own annotation to log method calls "@LogMethod" using AOP "Method: '{methodName}', user id: '{userId}' - start" "Method: '{methodName}', user id: '{userId}' - end" 
	
	4.1. Example of usage @LogMethod public void create...(...) {} Should be printed: (If user has id 123) "Method: 'create', user id: '123' - start" "Method: 'create', user id: '123' - end"
