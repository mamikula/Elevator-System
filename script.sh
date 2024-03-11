cd backend
./gradlew clean build
cd ..
docker-compose up --force-recreate -d
