ECHO start
d:
cd D:\eserver2\bin

ECHO Set the environment property.
CALL environment.bat

ECHO Change the directory to execute the tests
cd D:\AusPost\Test Suit Automation\auspost_test_suite
ant -f build.xml -DtestName=SmokeTest1 -DbrowserVersion=8 -DbrowserName=iexplorer executeTests
