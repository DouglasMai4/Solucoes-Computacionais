## add dependencies on intellij
### Path: File > Project Structure > Dependencies > Add button

## create database with docker
1. run: docker run --name sistema-automotivo -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql
2. run: docker exec -it sistema-automotivo mysql -u root -p 
3. insert password "root"
4. run: create database automotive;