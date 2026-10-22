FROM tomcat:10.1.61-jdk21-temurin-noble

RUN rm -rf /usr/local/tomcat/webapps/*

COPY dist/Adapter.war /usr/local/tomcat/webapps/Adapter.war

EXPOSE 8080
