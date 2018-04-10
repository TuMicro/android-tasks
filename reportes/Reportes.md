# Reportes

* Maquetar (xml's) la pantalla de reportes y las 9 subpantallas: [mockups](https://marvelapp.com/3h02je0/screen/37357132)

* Escribir las reglas del backend en Firestore y Firebase Storage (para las imágenes y videos).

* Desarrollar el código en java ó kotlin usando la arquitectura MVVM. El código debe incluir:

  * Escritura y almacenamiento a firebase (firestore y storage).
  * Pedido de ubicación del usuario (para los permisos puede usar PermissionUtil adjunto).
  * Enviar la ubicación desde donde se hizo cada reporte (almacenarla en el documento en Firestore).

* Para los autocompletados de línea o ruta (empresa de bus) asumir que ya existe una función que, dado como input el String que escribe el usuario, devuelve una lista de Pair<idDeRuta, nombreDeRuta> ordenada de mejor a peor coincidencia (nosotros ya tenemos esta función hecha).

Notas:
  
  * Algunos colores ya están definidos en el archivo colors.xml
  * Puede usar como referencia la implementación adjunta de LiveData para Firebase.