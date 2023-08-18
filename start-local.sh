echo 'Start running local env'

#build eureka service
cd eureka
mvn clean package -Dmaven.test.skip=true
cd ..

#build resource service
cd resourceservice
mvn clean package -Dmaven.test.skip=true
cd ..

#build song service
cd songservice
mvn clean package -Dmaven.test.skip=true
cd ..

#build resource processor
cd resourceprocessor
mvn clean package -Dmaven.test.skip=true
cd ..

#build minimal project with application
docker-compose up --build -d