#!/usr/bin/env bash

#ATUALIZAÇÃO PACOTES MAQUINA VIRTUAL & CONFIGURAÇÕES GERAIS DA MAQUINA
sudo apt-get update

sudo apt-get install -y nmap

#INSTALAÇÃO DOCKER
sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D

sudo apt-add-repository 'deb https://apt.dockerproject.org/repo ubuntu-xenial main'

sudo apt-get update

apt-cache policy docker-engine

sudo apt-get install -y docker-engine

sudo systemctl status docker

sudo usermod -aG docker vagrant

#INSTALAÇÃO DOCKER COMPOSE

sudo curl -L https://github.com/docker/compose/releases/download/1.18.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

docker-compose --version

docker-compose -f docker-compose.yml -f docker-compose-local.yml up -d

#INSTALAÇÃO JAVA
sudo apt-get install -y python-software-properties debconf-utils

sudo add-apt-repository -y ppa:webupd8team/java

sudo apt-get update

echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections

sudo apt-get install -y oracle-java8-installer

echo -en "JAVA_HOME='/usr/lib/jvm/java-8-oracle'" | sudo tee -a /etc/environment > /dev/null

source /etc/environment

#INSTALAÇÃO MAVEN

sudo apt-get install -y maven


