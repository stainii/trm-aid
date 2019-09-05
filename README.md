# TRM Aid
A script that will fill in a default 8 hours for every day + complete that day.

This avoids angry calls about forgotten TRM completions.

## How to configure this script?
You need to provide 2 files. Check out `src/main/resources` for an **example** of these files.
Make a copy of the example files, put them wherever you like, and **mount them** when starting the Docker container as:

* **/config/parameters.properties**: contains parameters like your project number
* **/config/logback.xml**: contains logging configuration. If you provide email details, a report will be sent to you after the script has run.

## How to run this script?
You need to have **Docker** installed and the engine running.
Then, in a terminal, launch the script with 

``docker run -v /your/local/parameters.properties:/config/parameters.properties -v /your/local/logback.xml:/config/logback.xml stainii/trm-aid:1.0.0-SNAPSHOT``

If you want this script to run daily, configure this in your crontab.
