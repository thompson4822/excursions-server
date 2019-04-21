## Excursions Server
This is the server component of the Excursions web site. It is designed to 
access a backend database, and so I wanted to quickly write down how that
should be done.

### MongoDB
In order to get going with Mongo, make sure there is a data directory in the
root of the project, that docker is running on the machine, and then issue
the following command:

```$xslt
docker run --name excursions-db -p 27018:27017 -v /home/steve/IdeaProjects/excursions-server/data:/data/db -d mongo
```

The first time the application starts it will look for Mongo to contain
default Excursion data, including users. If this information does not
exist, it will be created by the application.