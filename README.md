# ResourceManager
solution that would allow the resources to be stored centrally and requested on a "as-needed" basis by the production systems


Requirements:
jre 1.8
files have to by present in the application root foleder (it is used to set initial test data)
PN0001.jpg
PN0001.PDF
PN0002.jpg
PN0002.PDF

to run the app, execute in comand line
java -jar resorceManager-0.0.1-SNAPSHOT.jar
(assuming that JAVA is defined via system enviroment)

the application is running on http://localhost:8080/ url.

application login page url:
/login
test user credentials:
userName: user
password: user


resource downloading service url example:
/getResource?key=PN0001&class=ARTWORK
where key=PN0001 - resource name (in test data set it is equals to file name)
class=ARTWORK - resource class (in test data set ARTWORK mathces PDF files, LOGO matches JPG files)


there is two imlementations of file storage:
on filesystem as file 
in DB as Blob data
and two ResourceGetter implementations:
FileResourceGetter
DBResourceGetter

By test data configuration via data.sql file .PDF files are stored on fileSystem and .jpg files are stored in DB which is defined via "resource_types" table

General application description:
Application is based on SpringBoot framework
By adding com.h2database dependency to pom.xml file spring automaticaly configures in-memory datasource and creates DB schema according to schema.sql file in the project resource folder. Also it loads initial data to tables from data.sql file.
Application jar file contains embeded Tomcat app server so thre is no beed to deploy application anywhere
Also Spring framework provides basic security features for authentication.

