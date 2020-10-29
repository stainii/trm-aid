# TRM Aid
A script that will fill in default hours for every day + complete that day.

This avoids angry calls about forgotten TRM completions.

## How to configure this script?
You need to provide 2 files. Check out `src/main/resources` for an **example** of these files.
Make a copy of the example files, put them wherever you like, and **mount them** when starting the Docker container as:

* **/config/parameters.properties**: contains parameters like your project number, email, password, the hours that need to be filled in, ...
* **/config/logback.xml**: contains logging configuration. If you provide email details, a report will be sent to you after the script has run.

## How to run this script?
You need to have **Docker** installed and the engine running.
Then, in a terminal, launch the script with 

``docker run -v /your/local/parameters.properties:/config/parameters.properties -v /your/local/logback.xml:/config/logback.xml stainii/trm-aid:1.0.0-SNAPSHOT``

### Run this script daily
If you want this script to run daily, configure this in your crontab.
Note that TRM is secured with 2-factor authentication. That means that you'll need to react to the notification on your phone in time!

For Ubuntu: `crontab -l`

### Release

#### Maven release
No Maven releases are made for this project.

#### Docker release
A Docker release is made, by running `mvn clean deploy` on the Maven release branch.