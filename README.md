# Elasticsearch

Etape d'installation Elasticsearch avec Docker : 
# téléchargement de l'image docker :

 - docker pull docker.elastic.co/elasticsearch/elasticsearch:8.7.1

# Création d'un docker network pour elasticsearch et kibana :

    - docker network create elastic 

# Démarrer Elasticsearch : 

    - docker run --name elasticsearch --net elastic -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -t docker.elastic.co/elasticsearch/elasticsearch:8.7.1
  
# Installer l'image de Kibana et Démarrer le conteneur : 

    - docker pull docker.elastic.co/kibana/kibana:8.7.1
    - docker run --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.7.1

# Copie du certificat sur la machine locale (pas nécéssaire) : 

    - docker cp <nomconteneur>:/usr/share/elasticsearch/config/certs/http_ca.crt .

# Vérifier que la connection a elasticsearch est possible (pas nécéssaire) :

    - curl --cacert http_ca.crt -u elastic https://localhost:9200

Une fois que tout les conteneurs sont en place on peut se rendre a l'adresse https://localhost:5061
ou il va falloir insérer le token kibana a trouver dans les logs du conteneur elasticsearch de meme pour le mot de passe utilisateur.


Comment Elasticsearch procède-t-il au mapping ? 
    

Peut-on modifier le mapping sans recréer l’index ?

    Oui c'est possible exemple : 

    PUT /test-index/_mapping
    {
    "properties": {
        "field2": { "type": "text" }
        }
    }

Tokenisation : 
    découper une phrase en "tokens" chaque token représente un mot de la phrase.
Normalisation : 
    passage d'un filtre sur les tokens par exemple : enlever les majuscules.