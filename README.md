# Evaluación Api Rest de Usurios

- Esta API utiliza JWT Bearer Token
- Permite: 
- Ingresar un usuario nuevo, no requiere token
- Logearse mediante un correo y clave, no requiere token
- Modificar los datos de un usuario existente, requiere token
- Eliminar un usuario existente, requiere token
- Listar todos los usuarios de la base, requiere token
- Obtener los datos de un usuario, requiere token  

## Entorno y cambios de configuración

- **La aplicación se lanza en el puerto 8090**
- **La base de datos H2 se puede revisar en:** `http://localhost:8090/api/h2-ui`

![api-rest-1](base.png)

- **Todas las variables de configuración se pueden encontrar en el archivo**
- `src/main/resources/application.properties`

###### Server Config
- server.port = 8090
- server.servlet.context-path = /api

###### Data Source Config
- spring.datasource.url=jdbc:h2:mem:dbusuarios
- spring.datasource.driverClassName=org.h2.Driver
- spring.datasource.username=sa
- spring.datasource.password=

###### JPA & H2 Config  
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
- spring.jpa.hibernate.ddl-auto= update
- spring.jpa.defer-datasource-initialization=true
- spring.jpa.open-in-view = false 
- spring.h2.console.enabled=true
- spring.h2.console.path=/h2-ui

###### JWT Config
- api.jwt.secret=api-evaluacion
- api.jwt.cookieName=api-token

> HH x MM x ss x ms =>
> 5 x 60 x 60 x 1000 => 18000000 (5 horas)s

- api.jwt.expire = 18000000


## Descargar fuentes y ejecutar la aplicación

- **git clone https://github.com/ecoke/prueba-api-rest-user.git**<br>
- **cd prueba-api-rest-user**
- **gradlew bootRun (o sh gradlew bootRun /*desde git batsh*/)**
 
![api-rest-2](gradle.png)



## Proyecto Postman

- El json para importar a Postman se enecuentra en la raíz de este repositorio `postman-api-rest-user.json`


## Autenticar los servicios seguritizados


- Para poder ejecutar los servicios seguritizados se debe primero obtener un token ya sea con el servicio de registro o de login
- Ese token se debe asignar a la autenticación del resto de servicios que requieren enviar el token, como se muestra en la figura


> Obtener el token con registro o login




![api-rest-2](postman-1.png)





> Asignar el token al servicio seguritizado





![api-rest-2](postman-2.png)



 
## Servicios

###### POST - Crear nuevo usuario (No requiere token)

 `http://localhost:8090/api/user/registro`

	Input: 
	{
		"name": "Eduardo Coke",
		"email": "ejemplo@google.cl",
		"password": "123456",
		"phones": [
			{
				"number": "1234567",
				"citycode": "1",
				"contrycode": "57"
			},
			{
			"number": "1234567",
			"citycode": "1",
			"contrycode": "57"
			}
		]
	}


	Output:
	{
		"id": "c0a83801-7f3f-11b4-817f-3f725a200000",
		"name": "Eduardo Coke",
		"email": "ejemplo@google.cl",
		"token": null,
		"created": "2022-02-28T08:28:53.134+00:00",
		"modified": "2022-02-28T08:28:53.134+00:00",
		"last_login": null,
		"isactive": true,
		"phones": [
			{
				"phone_id": 1,
				"number": 1234567,
				"citycode": 1,
				"contrycode": 57
			},
			{
				"phone_id": 2,
				"number": 1234567,
				"citycode": 1,
				"contrycode": 57
			}
		]
	}

###### POST - Login por email y pass (no requiere token)

`http://localhost:8090/api/user/login`

	Input:
	{
	"email": "ejemplo@google.cl",
	"password": "123456"
	}

	Output:
	{
    "id": "c0a83801-7f3f-15e8-817f-3fcba8ee0000",
    "name": "Eduardo Coke",
    "email": "ejemplo@google.cl",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhcGktdG9rZW4iLCJzdWIiOiJlamVtcGxvQGdvb2dsZS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NDYwNDI3OTAsImV4cCI6MTY0NjA2MDc5MH0.TW0E7hsLRpu-GQYbvCRivvz1jHtDV7yr1pDOux0SoYxlRzlDggFk4XNmGw1y5BY3maZGhEjYTVDn2iay0yH6Iw",
    "created": "2022-02-28T10:06:25.960+00:00",
    "modified": "2022-02-28T10:06:25.960+00:00",
    "last_login": "2022-02-28T10:06:30.107+00:00",
    "isactive": true,
    "phones": [
        {
            "phone_id": 1,
            "number": 1234567,
            "citycode": 1,
            "contrycode": 57
        },
        {
            "phone_id": 2,
            "number": 1234567,
            "citycode": 1,
            "contrycode": 57
        }
    ]
	}
	
	
###### GET - listar todos los usuarios (requiere token)

`http://localhost:8090/api/user/list`

	Input:
	N/A	

	Output:
	[
	    {
	        "id": "c0a83801-7f3f-15e8-817f-3fcba8ee0000",
	        "name": "Eduardo Coke",
	        "email": "ejemplo@google.cl",
	        "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhcGktdG9rZW4iLCJzdWIiOiJlamVtcGxvQGdvb2dsZS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NDYwNDMxMDMsImV4cCI6MTY0NjA2MTEwM30.Dc3lTUX61QkPoPBhH39sU6atDb9_8vgUU32zLjppVLlUNWQgaX1j6brt6ze4faYb99Imjw9ip-Sr6xaGjpwWkw",
	        "created": "2022-02-28T10:06:25.960+00:00",
	        "modified": "2022-02-28T10:06:25.960+00:00",
	        "last_login": "2022-02-28T10:11:43.158+00:00",
	        "isactive": true
	    }
	]
	

###### POST - Obtener datos usuario existente (requiere token)

`http://localhost:8090/api/user/get?id=UUID`

	Input:
	?id=UUID

	Output:
	{
	    "id": "c0a83801-7f3f-15e8-817f-3fcba8ee0000",
	    "name": "Eduardo Coke",
	    "email": "ejemplo@google.cl",
	    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhcGktdG9rZW4iLCJzdWIiOiJlamVtcGxvQGdvb2dsZS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NDYwNDMxMDMsImV4cCI6MTY0NjA2MTEwM30.Dc3lTUX61QkPoPBhH39sU6atDb9_8vgUU32zLjppVLlUNWQgaX1j6brt6ze4faYb99Imjw9ip-Sr6xaGjpwWkw",
	    "created": "2022-02-28T10:06:25.960+00:00",
	    "modified": "2022-02-28T10:06:25.960+00:00",
	    "last_login": "2022-02-28T10:11:43.158+00:00",
	    "isactive": true,
	    "phones": [
	        {
	            "phone_id": 1,
	            "number": 1234567,
	            "citycode": 1,
	            "contrycode": 57
	        },
	        {
	            "phone_id": 2,
	            "number": 1234567,
	            "citycode": 1,
	            "contrycode": 57
	        }
	    ]
	}
	


###### PATCH - Actualizar datos usuario existente (requiere token)

`http://localhost:8090/api/user/change`

	Input:
	{   
	    "id":"c0a83801-7f3f-19d1-817f-3f1a0ab30000",
	    "name": "Eduard Cok",
	    "email": "ejempl@google.cl",
	    "password": "123456",
	    "isactive": false
	}

	Output:
	{
	    "id": "c0a83801-7f3f-15e8-817f-3fcba8ee0000",
	    "name": "Eduard Cok",
	    "email": "ejempl@google.cl",
	    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhcGktdG9rZW4iLCJzdWIiOiJlamVtcGxvQGdvb2dsZS5jbCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NDYwNDMxMDMsImV4cCI6MTY0NjA2MTEwM30.Dc3lTUX61QkPoPBhH39sU6atDb9_8vgUU32zLjppVLlUNWQgaX1j6brt6ze4faYb99Imjw9ip-Sr6xaGjpwWkw",
	    "created": "2022-02-28T10:06:25.960+00:00",
	    "modified": "2022-02-28T10:18:40.622+00:00",
	    "last_login": "2022-02-28T10:11:43.158+00:00",
	    "isactive": false,
	    "phones": [
	        {
	            "phone_id": 1,
	            "number": 1234567,
	            "citycode": 1,
	            "contrycode": 57
	        },
	        {
	            "phone_id": 2,
	            "number": 1234567,
	            "citycode": 1,
	            "contrycode": 57
	        }
	    ]
	}


###### DEL - Eliminar un usuario existente (requiere token)

`http://localhost:8090/api/user/delete?id=UUID`

	Input:
	?id=UUID

	Output:
	{"message": "Usuario eliminado exitosamente"}



###### Mensajes de salida de los servicios (excepciones)

	
	{"message": "Permiso denegado"}
	{"message": "Formato de correo no válido x@y.z"}
	{"message": "Nombre de suario ya registrado"}
	{"message": "Email ya registrado"}
	{"message": "Usuario o clave incorrectos"}
	{"message": "Usuario no encontrado"}
	{"message": "UUID no válido"}










  








