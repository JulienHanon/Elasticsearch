# Elasticsearch

Etape d'installation Elasticsearch avec Docker : 
# téléchargement de l'image docker :

 - docker pull docker.elastic.co/elasticsearch/elasticsearch:8.7.1

# Création d'un docker network pour elasticsearch et kibana :

    - docker network create elastic 

# Copie du certificat sur la machine locale : 

    - docker cp es01:/usr/share/elasticsearch/config/certs/http_ca.crt .


# Démarrer Elasticsearch : 

    - docker run --name elasticsearch --net elastic -p 9100:9100 -p 9300:9300 -e "discovery.type=single-node" -t docker.elastic.co/elasticsearch/elasticsearch:8.7.1

    - password : BlvbjZIyhwHYFE7vFq5O
    - certificat : dbb3f4eadf1b014b333eb1f97f520eb3b613700f19d83ded1e6f9e2813804df1
    - token kibana : eyJ2ZXIiOiI4LjcuMSIsImFkciI6WyIxNzIuMTguMC4yOjkyMDAiXSwiZmdyIjoiZGJiM2Y0ZWFkZjFiMDE0YjMzM2ViMWY5N2Y1MjBlYjNiNjEzNzAwZjE5ZDgzZGVkMWU2ZjllMjgxMzgwNGRmMSIsImtleSI6IjBuYW9RNGdCdlRldWxWNmNmQXRXOkk2YjE3S1R5UXNxeEszVUF2VWxMTkEifQ==
    - token elasticsearch : eyJ2ZXIiOiI4LjcuMSIsImFkciI6WyIxNzIuMTguMC4yOjkyMDAiXSwiZmdyIjoiZGJiM2Y0ZWFkZjFiMDE0YjMzM2ViMWY5N2Y1MjBlYjNiNjEzNzAwZjE5ZDgzZGVkMWU2ZjllMjgxMzgwNGRmMSIsImtleSI6IjBYYW9RNGdCdlRldWxWNmNmQXRXOnB6Y1kwUlVOUTdLdFZySEF5S0xqMXcifQ==
  
# Installer l'image de Kibana et Démarrer le conteneur : 

    - docker pull docker.elastic.co/kibana/kibana:8.7.1
    - docker run --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.7.1

