Simple MVC realization writen in 2009

Requirements:
1. JDK 1.6
2. apache-ant-1.7.1
3. MySQL 5

Tune connect to database in src/config.properties.  
Run ant createDB, ant clean, ant dist. Copy file dist/test.war to APACHE_TOMCAT/webapp.  
Run tomcat. Application is available at http://host:port/test.
