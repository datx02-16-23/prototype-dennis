ECHO moving source
REM move annotation source
xcopy C:\Users\dennis\Documents\NetBeansProjects\annotation\src\* C:\dev\kandidat\public\annotation\src /s /i
xcopy C:\Users\dennis\Documents\NetBeansProjects\annotation\pom.xml C:\dev\kandidat\public\annotation /Y
REM move annotation test source
xcopy C:\Users\dennis\Documents\NetBeansProjects\annotation-test\src\* C:\dev\kandidat\public\annotation-test\src /s /i
xcopy C:\Users\dennis\Documents\NetBeansProjects\annotation-test\pom.xml C:\dev\kandidat\public\annotation-test /Y