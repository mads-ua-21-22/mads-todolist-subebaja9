#Practica 3
Para esta practica se nos pide que desarrollemos usando TDD una nueva *feature* de la aplicación:
La posibilidad de definir equipos a los que pueden perteners los usuarios.
##Base de Datos
Para el desarrollo de esta practica he sudado una base de datos *posgres* la cual tiene la siguientes tablas:
- tareas. Esta tabla contiene las tareas de cada usuario y esta compuesta por: un *id*, un *titulo* y el *id de usuario* que lo relaciona con la tabla de usuarios.
- usuarios. Esta tabla contiene los usuarios registrados en nuestra aplicacion y esta compuesta por: un *id*, un *email*, una *fecha de nacimiento*, un *nombre* y un *password* para poder hacer loggin.
- equipos. Esta tabla contiene los datos del equipo y esta compuesta por: su *id* y el *nombre* del equipo.
- equipo_usuario. Esta tabla es una tabla comodin para hacer la relacion entre equipo y usuarios que se compone por: una *id* para cada una de las relaciones, la *id* del usuario y la *id* del equipo.

Para más informacion acceder a la ruta /doc/capturas donde estan todas las capturas de la base de datos

##Enpoints

Las rutas las podemos dividir en dos una que es accesible por todo tipo de usuarios y otra que solo podemos ver si estamos logeados.

Desde fuera podemos consultar todos los equipos y los miembros que la componenen.

Una vez logeado con nuetro usario podemos crear un equipo dándole un nombre, añadirnos a un equipo ya existente o quitarnos de un grupo al que petertenecemos.