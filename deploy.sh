project="ETU003331"
src_dir="src/main/java"
webapp_dir="src/main/webapp"
build_dir="build"
lib_dir="lib"
Tomcat_webbapps="/home/randie/tomcat/webapps" 
libjar="$lib_dir/servlet-api.jar"

#1 création du repertoire build 
rm -rf $build_dir
mkdir -p $build_dir/WEB-INF/classes
mkdir -p $build_dir/WEB-INF/lib

#2 compilation *.java dans build/WEB-INF/classes
find $src_dir -name "*.java" > sources.txt
javac -cp $libjar -d $build_dir/WEB-INF/classes @sources.txt
rm sources.txt

#3 Copier webapp/* vers build
cp -r $webapp_dir/* $build_dir/
cp -r $lib_dir/* $build_dir/WEB-INF/lib

#4 création du fichier .war
cd $build_dir || exit
jar -cvf $project.war * 
cd ..

#5 déploiement
cp -f $build_dir/$project.war $Tomcat_webbapps/

echo "Déploiement terminé"