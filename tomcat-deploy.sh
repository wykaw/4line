#!/bin/bash
mv ./build/libs/*.war ~/src/webapp/
cd ~/src/webapp/
rm -r META-INF/
rm -r WEB-INF/
jar -xf *.war
rm -r myShop.war
/usr/local/apache-tomcat-9.0.70/bin/shutdown.sh
/usr/local/apache-tomcat-9.0.70/bin/startup.sh
