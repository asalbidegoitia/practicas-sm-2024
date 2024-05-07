
# Herramientas utilizadas

<p>eclipse-jee-2022-09</p>
<p>ojdk-1.8.0.332</p>
<p>apache-maven-3.8.6</p>
<p>Tomcat v9.0.62</p>
<p>Spring MVC </p>

# REST API

<p>http://universities.hipolabs.com/search?country=United+States</p>
<p>http://universities.hipolabs.com/search?country=india</p>
<p>http://universities.hipolabs.com/search?country=india&name=kamaraj</p>

## Ruta local

http://localhost:8080/Practicas2024/index.jsp

## Lista de tareas 

<p>Para crear los nombres de las ramas: deben empezar por el código de la tarea (P2024-XXX) y no contener espacios.</p>
	<p>Por ejemplo: P2024-001-Modificar_buscador, P2024-002-Introducir_mensajes_error etc </p>
<p>Los mensajes de los commit que se hagan deben empezar por el código de la tarea (P2024-XXX) seguidos de lo que se haya hecho en ese commit. </p>
	<p>Por ejemplo: P2024-002 Mensajes de error introducidos </p>

Una lista de tareas para realizar y subir a Git:

* Crear una BD para guardar datos de las universidades que se busquen. 
	Guardar en la tabla para cada universidad un id unico (uid), nombre de la universidad, su página web, país, provincia/estado en el que está y la fecha en la que se guarda en BD.

* **P2024-001:** El buscador solo funciona introduciendo Nombre y País. Modificar el buscador para que funcione cuando se introduce solo 1 parametro.
	Si se introduce solo el país que saque todas las universidades de ese pais.

* **P2024-002:** La aplicación no tiene mensajes de error ni excepciones parametrizadas. Introducir mensajes de error y/o excepciones.
	Por ejemplo si no hay resultados en una busqueda sacar un mensaje diciendolo.
	Si sucede un error en la ejecución poner bien las excepciones.
	País es un campo obligatorio, si no lo introduce debe salir un mensaje indicandolo.

* **P2024-003:** Guardar en BD datos de las universidades que se busquen.
	Crear tambien un botón para guardar en la jsp.
	Crear un controller para la operación de guardar.

* **P2024-004:** Insertar en BD nuevas universidades.
	Crear en una nueva jsp un formulario para introducir datos.
	Proponed en común que campos deben ser obligatorios y cuales no.
	Crear un controller nuevo para la operación de insertar.
	
* **P2024-005:** Consultar y borrar la lista de universidades que se han introducido.
	Crear en una nueva jsp una tabla para consultar las universidades introducidas en BD.
	En esta nueva jsp tambien crear un botón de borrar para borrar registros concretos de la tabla.
	Crear un controller nuevo para la operación de consultar y otro para la operación de borrar.

* **P2024-006:** Introducir una barra de navegación en la parte superior de la pantalla.
	Para poder navegar entre la index.jsp, la jsp del formulario de insertar, la de consultar y cualquier otra que vaya surgiendo.
	Que se pueda navegar por la aplicación utilizando la barra de navegación.
	Podeis copiar una barra de navegación ya hecha de bootstrap.

* **P2024-007:** Mejorar los estilos de la aplicación.
	En vez de el JSON que se muestra directamente como resultado de la busqueda mostrar los datos de forma mas elegante.
	Como sugerencia se puede usar bootstrap.
	
	
# Tareas avanzadas
* **P2024-008:** Cargar archivos.
	En la jsp principal crear un botón para adjuntar archivos.
	Guardar los archivos en base de datos como BLOB. Crear una nueva tabla para guardar el BLOB y que tenga tambien un id.

* **P2024-009:** Descargar archivos.
	En la jsp principal crear un botón para descargar archivos adjuntados.
	Recoger el BLOB de BD y descargarlo.

* **P2024-010:** Meter un login con usuarios.
	Crear una jsp de login a la que la aplicación vaya por defecto al arrancar.
	Crear una tabla en BD para usuarios y meteros a vosotros como usuarios en BD.
	Crear tambien un logout. 

* **P2024-011:** Recuperación de contraseña.
	Crear una función de recuperar contraseña que te mande al email un correo para hacer un cambio de password.
	Buscar como mediante la api de chatgpt generar contraseñas seguras.

* **P2024-012:** Expresiones Lambda.
	En el código de la aplicación donde se pueda sustituir código antiguo por expresiones Lambda sin que se pierdan funcionalidades en la aplicación.








