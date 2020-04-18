College Management System
=========================

Prerequisites
--------------

* Java 8
* Maven 3.x
* Spring Boot 2.x.x
* Mongo DB 4.x.x
* Swagger 2.x.x

Running the Application
-----------------------

Embedded Tomcat
---------------
**Step 1**: Compile the project with jar :

    mvn clean install

**Step 2**: Run following command

	  mvn spring-boot:run

**Step 3**: Open browser and verify Swagger

      http://localhost:8080/swagger-ui.html
      
Run Mongo On Mac
----------------
Install and Run MongoDB by Downloading it Manually

    Go to the MongoDB website’s download section and download the correct version of MongoDB.

    After downloading Mongo move the gzipped tar file (the file with the extension .tgz that you downloaded) to the folder where you want Mongo installed. In this case, we’ll say that we want Mongo to live in our home folder, and so the commands might look something like this:

    > cd Downloads
    > mv mongodb-osx-x86_64-3.0.7.tgz ~/

    Extract MongoDB from the the downloaded archive, and change the name of the directory to something more palatable: > cd ~/ > tar -zxvf mongodb-osx-x86_64-3.0.7.tgz > mv mongodb-osx-x86_64-3.0.7 mongodb
    Create the directory where Mongo will store data, create the “db” directory. ou can create the directory in the default location by running mkdir -p /data/db

    Make sure that the /data/db directory has the right permissions by running

    > sudo chown -R `id -un` /data/db
    > # Enter your password

    Run the Mongo daemon, in one terminal window run ~/mongodb/bin/mongod. This will start the Mongo server.
    Run the Mongo shell, with the Mongo daemon running in one terminal, type ~/mongodb/bin/mongo in another terminal window. This will run the Mongo shell which is an application to access data in MongoDB.
    To exit the Mongo shell run quit()
    To stop the Mongo daemon hit ctrl-c

https://treehouse.github.io/installation-guides/mac/mongo-mac.html

Mongo Download
--------------
https://www.mongodb.com/download-center/community

Mongo Client  
------------
https://robomongo.org/download

Banner Changes
--------------
https://devops.datenkollektiv.de/banner.txt/index.html



