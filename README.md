# Api-Concert-Spring
Le but de ce tp est de travailler en groupe sur une api crud pour gestion de concert afin de s’habituer au fonctionnement de spring.

Ayoub QUAMAR Maxence LARMET Bertille MENGUY 

Vout trouverez ici le diagramme qu'on a utilisé pour l'api concert
<img width="887" alt="concertApi" src="https://user-images.githubusercontent.com/57022671/222091245-4215dc7b-662f-489a-b7d6-bae7d6cc93c7.PNG">


Exemple de requete POSTMAN:

Sur l'URL http://localhost:8080/Concert : POST:

{
  "date": "2023-03-01",
  "duree": 120,
  "prix": 30,
  "salle": {
    "idSalle": 3
  },
  "soiree": {
    "idSoiree": 1
  }
}
