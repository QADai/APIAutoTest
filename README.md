##Brief：
Using Rest-Assured+TestNG+Excel Data driver mode，for Api automation testing.


##Code module：
* log：lead in logback lib  for recording info, debug and error log；
* report：monitoring test report with extent report listener；
* config：defined static variables , e.g. the test file path, test server, common share data；
* common： Common method；
	* annotation:define API method, Description, path, parameters, etc;
	* enums：will collect most for variables;
	* utils：basic function, excel operations, Http method, design pattern or database connector;
* testObject：Encapsulation api method；
* testData：separated test data and test script, reduce test cast, improve code transplantation, ceated one test class for each excel file while testing；
* testSuite：manage test component and save to testng xml file，test fail retry mechanism, test listener；

###MORE, base one specific project can do more 
* mysql do statistics for history test result;
* Exception;
* more detail assertion;
* Others;
