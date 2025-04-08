@echo off
setlocal

:: Configuration des variables
set project=ETU003331
set src_dir=src\main\java
set webapp_dir=src\main\webapp
set build_dir=build
set lib_dir=lib
set Tomcat_webapps=C:\Users\Randie\Documents\Install\TOMCAT\apache-tomcat-10.1.28\webapps
set libjar=%lib_dir%\servlet-api.jar

:: Étape 1 : Création du répertoire build
if exist %build_dir% rmdir /s /q %build_dir%
mkdir %build_dir%\WEB-INF\classes
mkdir %build_dir%\WEB-INF\lib

:: Étape 2 : Compilation des fichiers .java dans build\WEB-INF\classes
dir /b /s %src_dir%\*.java > sources.txt
javac -cp %libjar% -d %build_dir%\WEB-INF\classes @sources.txt
del sources.txt

:: Étape 3 : Copier les fichiers webapp dans build
xcopy /e /i /q %webapp_dir%\* %build_dir%\
xcopy /e /i /q %lib_dir%\* %build_dir%\WEB-INF\lib\

:: Étape 4 : Création du fichier .war
pushd %build_dir%
jar -cvf %project%.war *
popd

:: Étape 5 : Déploiement
copy /y %build_dir%\%project%.war %Tomcat_webapps%\

echo Deploiement termine
endlocal
