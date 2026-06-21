# Proyecto Grupal - Algoritmos y Estructuras de Datos (Grupo 02)

Este repositorio contiene la solución unificada del proyecto (Backend y Frontend). El sistema está completamente automatizado para configurarse y arrancar en cualquier computadora con un solo clic.

---

## 🛠️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de cumplir con lo siguiente en tu computadora:
1. **Java 21** (SDK/JDK) instalado y configurado.
2. **Node.js** instalado (requerido para el entorno de Astro).
3. **PostgreSQL** corriendo localmente con las credenciales estándar de laboratorio:
   * **Usuario:** `postgres`
   * **Contraseña:** `postgres`

---

## 🚀 Guía de Uso (Paso a Paso)

No es necesario crear la base de datos manualmente ni instalar las dependencias desde la terminal. El sistema hace todo por ti de forma transparente.

1. **Descargar el proyecto:** Descarga este repositorio en formato **ZIP** y descomprímelo en tu computadora.
2. **Abrir el Backend:** Abre tu IDE (**IntelliJ IDEA**) y selecciona únicamente la carpeta interna llamada `backend`.
3. **Ejecutar el proyecto:** Dale clic al botón verde de **Run** en la clase principal `Grupo02Application`.

---

## ⚙️ ¿Qué ocurre automáticamente tras darle "Run"?

Al iniciar el backend en Java, el sistema ejecutará el siguiente flujo en segundo plano:
* **Base de Datos Inteligente:** Java se conectará a tu PostgreSQL local. Si la base de datos física `almacen_db` no existe, la creará e Hibernate inyectará todas las tablas y registros iniciales (`data.sql`) de forma automática.
* **Auto-Instalación del Frontend:** El programa detectará si falta la carpeta `node_modules` en el frontend. De ser así, ejecutará un `npm install` silencioso para descargar las librerías de Astro.
* **Arranque del Ecosistema:** Se abrirá una ventana de comandos ejecutando el servidor de Astro (`npm run dev`) y **abrirá automáticamente tu navegador web predeterminado** mostrando la interfaz del sistema conectada al backend.

---

## 📁 Estructura del Repositorio
* **/backend**: Proyecto en Java desarrollado con Spring Boot y Maven.
* **/frontend**: Aplicación web SPA desarrollada con Astro.
