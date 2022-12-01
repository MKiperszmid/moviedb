## Preguntas

#### 1. ¿En qué consiste el principio de responsabilidad única? ¿Cuál es su propósito?:
Es el principio S de SOLID, el cual dice que una clase tiene que encargarse de hacer una única cosa. Por ej:
El Repository se encarga de manejar los datos. No debería de encargarse de hacer el Mapeo de Objetos DTO a Domain. Para eso se crea una clase o extension Mapper

#### 2. ¿Qué características tiene, según su opinión, un “buen” código o código limpio?
2 cosas:  
1- Tests. Ya que sin tests, es más dificil refactorear a futuro  
2- Respetar SOLID (Tampoco hay que volverse locos y hacerlo al 100%. Pero con aplicar algunas de las leyes, es más que suficiente)

#### 3. Detalla cómo harías todo aquello que no hayas llegado a completar. 
- Investigar más a fondo el tema de Drags para el detalle de la película (https://blog.canopas.com/explore-compose-motionlayout-773a3462d787)
- Arreglar la SplashScreen. No hay que usar la api de splashscreen de Android 12. Hay que usar la forma vieja ya que la nueva no acepta gradients + icono
- Ver trailer usando ExoPlayer
- Unit Test de Repository, y testear estados del viewmodel en caso de que la api o db devuelvan vacio

## Correr la App
1. Clonar el repo
2. Crear una API KEY en TMDB: https://www.themoviedb.org/settings/api
3. Agregar la API KEY en local.properties como: API_KEY=1234567890
4. Correr la app
