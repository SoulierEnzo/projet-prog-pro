@echo off
cd ..

echo === Lancement du Docker ===
docker-compose up -d

echo === Lancement de l'application Java ===
java -Dfile.encoding=UTF-8 -jar interview-simulator-0.0.1-SNAPSHOT.jar

pause
