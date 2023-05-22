# Elasticsearch

Etape d'installation Elasticsearch avec Docker : 
# téléchargement de l'image docker :

 - docker pull docker.elastic.co/elasticsearch/elasticsearch:8.7.1

# Création d'un docker network pour elasticsearch et kibana :

    - docker network create elastic 

# Copie du certificat sur la machine locale : 

    - docker cp <nomconteneur>:/usr/share/elasticsearch/config/certs/http_ca.crt .

# Démarrer Elasticsearch : 

    - docker run --name elasticsearch --net elastic -p 9100:9100 -p 9300:9300 -e "discovery.type=single-node" -t docker.elastic.co/elasticsearch/elasticsearch:8.7.1
  
# Installer l'image de Kibana et Démarrer le conteneur : 

    - docker pull docker.elastic.co/kibana/kibana:8.7.1
    - docker run --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.7.1

