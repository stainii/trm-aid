FROM openjdk:11.0.1-jdk-sid

# install Google Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list
RUN apt-get update -qqy
RUN apt-get -qqy -o APT::Immediate-Configure=0 install google-chrome-stable
RUN rm /etc/apt/sources.list.d/google-chrome.list
RUN rm -rf /var/lib/apt/lists/* /var/cache/apt/*
RUN sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

# install ChromeDriver for Selenium
ARG CHROME_DRIVER_VERSION=86.0.4240.22
RUN wget --no-verbose -O /tmp/chromedriver_linux64.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
	&& rm -rf /opt/chromedriver \
	&& unzip /tmp/chromedriver_linux64.zip -d /opt \
	&& rm /tmp/chromedriver_linux64.zip \
	&& mv /opt/chromedriver /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& chmod 755 /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& ln -fs /opt/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver

# add Java project
ARG JAR_FILE
ADD ${JAR_FILE} app.jar

# launch!
WORKDIR /
ENTRYPOINT exec java -Dlogback.configurationFile=/config/logback.xml -jar /app.jar /config/parameters.properties
