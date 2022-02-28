# apiSpring
Preaceleracion Alkemy  
Practica Api restful con SpringBoot
Api Mundo Disney
---
**Documentacion**

<https://documenter.getpostman.com/view/19787440/UVkqraGa>

---

## 1. Base de Datos
**Realizado**  
Personaje
+ Imagen.
+ Nombre.
+ Edad.
+ Peso.
+ Historia.
+ Películas o series asociadas.  
Pelicula
+ Imagen.
+ Título.
+ Fecha de creación.
+ Calificación (del 1 al 5).
+ Personajes asociados.  
Genero
Nombre.
+ Imagen.
+ Películas o series asociadas.

## 2. Autenticación de Usuarios
**Realizado**  
Para realizar peticiones a los endpoints subsiguientes el usuario deberá contar con un token que
obtendrá al autenticarse. Para ello, deberán desarrollarse los endpoints de registro y login, que
permitan obtener el token.
Los endpoints encargados de la autenticación deberán ser:
+ /auth/login
+ /auth/register


## 3. Listado de Personajes
**Realizado**  
El listado deberá mostrar:
+ Imagen.
+ Nombre.
El endpoint deberá ser:
+ /characters

## 4. Creación, Edición y Eliminación de Personajes (CRUD)
**Realizado**  
Deberán existir las operaciones básicas de creación, edición y eliminación de personajes.

## 5. Detalle de Personaje
**Realizado**  
En el detalle deberán listarse todos los atributos del personaje, como así también sus películas o
series relacionadas.

## 6. Búsqueda de Personajes
**Realizado**  
Deberá permitir buscar por nombre, y filtrar por edad, peso o películas/series en las que participó.
Para especificar el término de búsqueda o filtros se deberán enviar como parámetros de query:
+ GET /characters?name=nombre
+ GET /characters?age=edad
+ GET /characters?movies=idMovie

## 7. Listado de Películas
**Realizado**  
Deberá mostrar solamente los campos imagen, título y fecha de creación.
El endpoint deberá ser:
+ GET /movies

## 8. Detalle de Película / Serie con sus personajes
**Realizado**  
Devolverá todos los campos de la película o serie junto a los personajes asociados a la misma
**Nota:** Por el momento no se agregan correctamente las peliculas con sus personajes asociados, pero si los personajes con sus peliculas asociadas

## 9. Creación, Edición y Eliminación de Película / Serie
**Realizado**  
Operaciones básicas de creación, edición y eliminación de películas o series.

## 10. Búsqueda de Películas o Series 
**Realizado**  
Deberá permitir buscar por título, y filtrar por género. Además, permitir ordenar los resultados por
fecha de creación de forma ascendiente o descendiente.
El término de búsqueda, filtro u ordenación se deberán especificar como parámetros de query:
+ /movies?name=nombre
+ /movies?genre=idGenero
+ /movies?order=ASC | DESC

## 11. Envío de emails
**No implementado todavia**  
Al registrarse en el sitio, el usuario deberá recibir un email de bienvenida. Es recomendable, la
utilización de algún servicio de terceros como SendGrid.

## Documentación
**Realizado**  
Es deseable documentar los endpoints utilizando alguna herramienta como Postman o Swagger.
## Tests
De forma opcional, se podrán agregar tests de los diferentes endpoints de la APP, verificando
posibles escenarios de error:
+ Campos faltantes o con un formato inválido en BODY de las peticiones
+ Acceso a recursos inexistentes en endpoints de detalle
Los tests pueden realizarse utilizando JUnit y Mockito.

