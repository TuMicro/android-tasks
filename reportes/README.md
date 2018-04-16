# Reportes

* Maquetar (xml's) la pantalla de reportes y las 10 subpantallas. (pedir acceso a los mockups para mayor referencia)

* Maquetado y webview de la pantalla "tutorial".

* Escribir las reglas del backend en Firestore y Firebase Storage (para las imágenes y videos).

* Desarrollar el código en java ó kotlin usando la arquitectura MVVM. El código debe incluir:

  * Escritura y almacenamiento a firebase (firestore y storage).
  * Pedido de ubicación del usuario (para los permisos puede usar PermissionUtil adjunto).
  * Enviar la ubicación desde donde se hizo cada reporte (almacenarla en el documento en Firestore).

* Para los autocompletados de línea o ruta (empresa de bus) asumir que ya existe una función que, dado como input el String que escribe el usuario, devuelve una lista de Pair<idDeRuta, nombreDeRuta> ordenada de mejor a peor coincidencia (nosotros ya tenemos esta función hecha).

Notas:
  
  * Algunos colores ya están definidos en el archivo [colors.xml](colors.xml)
  * Puede usar como referencia la implementación adjunta de LiveData para Firebase
  * Para el maquetado puede usar como referencia [esto](schedule_main.xml)
