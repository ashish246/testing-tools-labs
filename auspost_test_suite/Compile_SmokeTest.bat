ECHO start
d:
cd D:\eserver2\bin

ECHO Set the environment property.
CALL environment.bat

ECHO Change the directory to compile the code
cd D:\AusPost\Test Suit Automation\auspost_test_suite
ant -f compile.xml build