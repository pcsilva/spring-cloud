FROM registry.access.redhat.com/jboss-eap-7/eap71-openshift:latest

ARG WAR_FILE
ADD ${WAR_FILE} /opt/eap/standalone/deployments/ROOT.war

USER jboss 
RUN /opt/eap/bin/add-user.sh admin @dmin123 --silent  

ENTRYPOINT ["/opt/eap/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug", "8787", "-Duser.timezone=America/Sao_Paulo"]
