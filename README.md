# MyFirstProjectOnGit

This project is based on use of microservices with different rest api. SpringBoot, Spring batch, CrudRepository and H2 database has been used. This project takes the currency of
different country compare that value of currency with each other and it update the value in database at 12 am everday as Cron expression has been set to this api. 
It will generate the csv file everyday in target folder of conversion package which contain the information about the conversion rate. If dataset is already present in 
then it will update the existing value otherwise if any new currency exchange is seen by some user then it will also inserted in database and in csv from after next day of 12 Am.


caching branch to be merged
