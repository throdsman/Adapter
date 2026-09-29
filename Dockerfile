FROM tomcat:10-jdk17-corretto

RUN rm -rf /usr/local/tomcat/webapps/*

COPY dist/Adapter.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
