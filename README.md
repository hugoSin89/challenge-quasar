# challenge-quasar
#---------------------------------------------------------
# Deploy
#---------------------------------------------------------
# Clone or download https://github.com/hugoSin89/challenge-quasar.git 
# Create DB firequasar.sql (File annexed)
# Run Aplication from IDE
# At Postman configured rets service type (POST) endpoint localhost:8050/satellite/location/topsecret
# Send topsecret message
# Look the service response
#---------------------------------------------------------
# Explicación
#---------------------------------------------------------
# Se utiliza una arquitectura basada en SpringBoot donde el Controller es el canal de acceso (EndPoint) por el cual
# se recibe la información de los satélites, este controller se comunica con el service quien se encarga de la lògica de
# negocio. Con el ánimo de mantener la buenas practicas de desarrollo se implementa una capa de Componentes encargados
# de ejecutar tareas únicas (SOLID principles) los cuales son invocados por el server para lograr la funcionalidad 
# requerida.
# Luego de la capa de componentes se requiere la capa de Entities y Repository donde se mapea la estructura de la base
# de datos y se realiza el manejo de la conexió y operaciones (DML) requeridas por medio de JPA. En esta ocaciòn solo 
# se deja en base de datos la información básica de los satélites.
#
# Adicionalmente se agregan DTOs para manejar la información y no trabajar directamente sobre las Entity.
#
# Para la operación de triangulación de la señal se usó la explicación de la siguiente URL:
# https://www.101computing.net/cell-phone-trilateration-algorithm/
