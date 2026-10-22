FROM tomcat:10-jdk21

RUN rm -rf /usr/local/tomcat/webapps/*

COPY dist/Adapter.war /usr/local/tomcat/webapps/Adapter.war

EXPOSE 8080
