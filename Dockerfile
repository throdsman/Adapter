FROM tomcat:10-jdk17-corretto

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY dist/Adapter.war /usr/local/tomcat/webapps/ROOT

EXPOSE 8080
