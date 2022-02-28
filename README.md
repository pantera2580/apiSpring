# apiSpring
Practica Api restful con SpringBoot
Api Mundo Disney, preaceleracion Alkemy

*** Documentacion ***
https://documenter.getpostman.com/view/19787440/UVkqraGa

1. Base de Datos
Personaje
Pelicula
Genero

2. Autenticación de Usuarios
Los endpoints encargados de autenticación:
● /auth/login
● /auth/register

3. Listado de Personajes
El listado muestra:
● Imagen.
● Nombre.
El endpoint:
● /characters

4. Creación, Edición y Eliminación de Personajes (CRUD)
Operaciones básicas de creación, edición y eliminación de personajes

5. Detalle de Personaje
En el detalle se listan todos los atributos del personaje, como así también sus películas o
series relacionadas.

6. Búsqueda de Personajes
Se permite buscar por nombre, y filtrar por edad, peso o películas/series en las que participó.
Para especificar el término de búsqueda o filtros se deberán enviar como parámetros de query:
● GET /characters?name=nombre
● GET /characters?age=edad
● GET /characters?movies=idMovie

7. Listado de Películas
Muestra solamente los campos imagen, título y fecha de creación.
El endpoint deberá ser:
● GET /movies

8. Detalle de Película / Serie con sus personajes
Devuelve todos los campos de la película o serie junto a los personajes asociados a la misma
- Por el momento no se agregan correctamente las peliculas con sus personajes asociados, pero si los personajes con sus peliculas asociadas - 

9. Creación, Edición y Eliminación de Película / Serie
Operaciones básicas de creación, edición y eliminación de películas o series.

10. Búsqueda de Películas o Series
Se permite buscar por título, y filtrar por género. Además, permitir ordenar los resultados por
fecha de creación de forma ascendiente o descendiente.
- En esta parte por el momento se tiene un error, se lista por titulo Ascendente o Descendente - 
El término de búsqueda, filtro u ordenación se deberán especificar como parámetros de query:
● /movies?name=nombre
● /movies?genre=idGenero
● /movies?order=ASC | DESC

11. Envío de emails
- No implementado todavia - 
