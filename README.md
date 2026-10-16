# Prueba-calculador-celu

Aplicación básica para calcular el total de un producto y generar un comprobante en imagen o PDF.

## Requisitos

- Java 17+
- Gradle 8.7+

## Cómo ejecutar

```bash
./gradlew run
```

Luego ingresa:
- nombre del producto
- cantidad
- precio unitario
- formato deseado: `png` o `pdf`

El programa calculará el total y guardará el comprobante en la carpeta actual del proyecto.

## Validación

```bash
./gradlew test
```
