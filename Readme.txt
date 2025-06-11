jpackage --type exe --name SisAdminPro --app-version 1.4 --input target --dest instalador --main-jar jean-0.0.1-SNAPSHOT.jar  --main-class org.springframework.boot.loader.launch.JarLauncher --icon app-icon.ico --win-shortcut --win-menu --win-dir-chooser --runtime-image jre-windows


/correr un jar de java

java -jar target/tu-archivo.jar

mvn clean package && java -jar target/tu-archivo.jar

mvn clean package 

SisAdmin_V
SisAdmin_V.exe > log.txt 2>&1
