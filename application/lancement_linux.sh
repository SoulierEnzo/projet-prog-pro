#!/bin/bash
cd ..
echo "=== Lancement du Docker ==="
docker compose up -d

echo "=== Lancement de l'application Java ==="
java -jar mon_application.jar

pause
