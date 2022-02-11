# Spring Backend Ecommerce
Desarrollado para el curso de Programación Backend en Java de CoderHouse. Backend de una aplicación de e-commerce para poder vender productos.

### APIs:
|          	| GET                                                         	| POST                  	| PUT                      	| DELETE                   	|
|----------	|-------------------------------------------------------------	|-----------------------	|--------------------------	|--------------------------	|
| Category 	| getAllCategories<br>getCategoryByCode                       	| createCategory        	| updateCategory           	| deleteCategory           	|
| Product  	| getProductByCode<br>getAllProducts<br>getProductsByCategory 	| createProduct         	| updateProduct            	| deleteProduct            	|
| User     	|                                                             	| register<br>login     	|                          	|                          	|
| Cart     	| getCartByEmail<br>getAllCarts<br>getAllItems                	| createCart<br>addItem 	| updateCart<br>updateItem 	| deleteCart<br>deleteItem 	|
| Order    	| getAllOrders<br>getOrderByOrderNumber                       	| createOrder           	|                          	|                          	|

### Piezas utilizadas
- Spring Boot
- MongoDB
- JWT
- Spring Data con MongoDB
- Redis
- AOP
- Spring Boot mail


### Características
- Implementación de una API RESTful con los verbos GET, POST, PUT y DELETE para el manejo de "productos", "usuarios", "carrito de compra" y "ordenes".
- Uso de ingreso autorizado al sistema basado en JWT (Json Web Token).
- Persistencia de los datos en una base de datos MongoDB.
- La arquitectura del servidor estará basada en capas (MVC).
- El cliente puede registar sus credenciales (email y contraseña) para el acceso a una cuenta, que le permitira enviar solicitudes al servidor. La contraseña es encriptada y almacenada en la base de datos.
- Se utiliza un [config-server](https://github.com/ayrton30/config-server) para obtener las configuraciones desde un [archivo externo](https://github.com/ayrton30/config-prop).
- Mediante el uso de Spring Boot mail se envia un mail por cada nuevo registro de usuario y por cada orden de compra confirmada. Como CCO se establece una casilla configurable.
- Se emplea el manejo de excepciones propio de Spring @ControllerAdvice. En caso de detectar algún error, el servidor envía un mensaje de error particular a la excepción manejada.
- Con cada Request se registra un log al finalizar el mismo con AOP.
- Implementación de una caché gracias al uso de Redis.
- Documentación de la API implementada haciendo uso de la especificación OpenAPI.
