#!/bin/bash

docker-compose down -v
docker rmi mysql_app:latest
docker rmi mysql-app:latest
chmod +x ./gradlew
./gradlew clean bootjar
rm -rf ./db/master/data/*
rm -rf ./db/slave/data/*
docker-compose build
docker-compose up -d

until docker exec mysql-master sh -c 'mysql -u root -e ";"'
do
    echo "Waiting for mysql-master database connection..."
    sleep 4
done

echo "1--------------------------"
priv_stmt='CREATE USER "slave_user"@"%" IDENTIFIED BY "slave_user"; GRANT REPLICATION SLAVE ON *.* TO "slave_user"@"%"; FLUSH PRIVILEGES;'
docker exec mysql-master sh -c "mysql -u root -e '$priv_stmt'"
echo "--------------------------"

until docker-compose exec mysql-slave sh -c 'mysql -u root -e ";"'
do
    echo "Waiting for mysql-slave database connection..."
    sleep 4
done

echo "2--------------------------"
MS_STATUS=`docker exec mysql-master sh -c 'mysql -u root -e "show master status;"'`
CURRENT_LOG=`echo $MS_STATUS | awk '{print $6}'`
CURRENT_POS=`echo $MS_STATUS | awk '{print $7}'`
echo "--------------------------"


echo "3--------------------------"
start_slave_stmt="CHANGE MASTER TO MASTER_HOST='mysql-master', MASTER_USER='slave_user', MASTER_PASSWORD='slave_user', MASTER_LOG_FILE='$CURRENT_LOG',MASTER_LOG_POS=$CURRENT_POS; START SLAVE;"
start_slave_cmd='mysql -u root -e "'
start_slave_cmd+="$start_slave_stmt"
start_slave_cmd+='"'
docker exec mysql-slave sh -c "$start_slave_cmd"
#docker exec mysql-slave sh -c "mysql -u root -e 'flush privileges;'"
echo "--------------------------"

echo "4--------------------------"
docker exec mysql-slave sh -c "mysql -u root -e 'stop slave;'"
docker exec mysql-slave sh -c "mysql -u root -e 'reset slave;'"
docker exec mysql-slave sh -c "mysql -u root -e 'start slave;'"
echo "--------------------------"

echo "5--------------------------"
docker exec mysql-slave sh -c "mysql -u root -e 'SHOW SLAVE STATUS\G'"
echo "--------------------------"

#docker cp ./db/init/create.sql mysql-master:/root
#docker cp ./db/init/create.sql mysql-slave:/root
#
#docker exec mysql-master sh -c "mysql -u root -e 'source /root/create.sql'"
#docker exec mysql-slave sh -c "mysql -u root -e 'source /root/create.sql'"
