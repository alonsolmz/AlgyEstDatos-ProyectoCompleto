package pe.edu.pe.Grupo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication
public class Grupo02Application {

    public static void main(String[] args) {
        if (System.getProperty("astro.launcher.active") == null) {
            System.setProperty("astro.launcher.active", "true");

            // 1. Forzamos la creación automática de la BD física en Postgres
            crearBaseDeDatosSiNoExiste();

            // 2. Automatizamos la instalación de dependencias de Node y el arranque de Astro
            automatizarEntorno();
        }

        // 3. Arrancamos el backend (JPA creará las tablas automáticamente dentro de almacen_db)
        SpringApplication.run(Grupo02Application.class, args);
    }

    private static void crearBaseDeDatosSiNoExiste() {
        // Conectamos a 'template1' (la BD comodín que siempre existe en cualquier Postgres)
        String urlBase = "jdbc:postgresql://localhost:5432/template1";
        String usuario = "postgres";
        String contrasena = "postgres"; // Credencial estándar universitaria

        try (Connection conn = DriverManager.getConnection(urlBase, usuario, contrasena);
             Statement stmt = conn.createStatement()) {

            // Validamos si la base de datos 'almacen_db' existe en el sistema
            var rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = 'almacen_db'");

            if (!rs.next()) {
                System.out.println("[INFO] La base de datos 'almacen_db' no existe. Creándola automáticamente...");
                stmt.executeUpdate("CREATE DATABASE almacen_db");
                System.out.println("[INFO] Base de datos 'almacen_db' creada con éxito.");
            } else {
                System.out.println("[INFO] Base de datos 'almacen_db' detectada y lista para operar.");
            }
        } catch (Exception e) {
            System.err.println("[ADVERTENCIA] No se pudo verificar/crear la BD automáticamente: " + e.getMessage());
            System.err.println("[ADVERTENCIA] Asegúrate de que PostgreSQL esté activo y use el usuario/password 'postgres'.");
        }
    }

    private static void automatizarEntorno() {
        try {
            File directorioActual = new File(".").getCanonicalFile();
            File carpetaFrontend = new File(directorioActual.getParentFile(), "frontend").getCanonicalFile();

            System.out.println("[INFO] Ruta base del backend detectada: " + directorioActual.getAbsolutePath());
            System.out.println("[INFO] Ruta calculada para el frontend: " + carpetaFrontend.getAbsolutePath());

            if (!carpetaFrontend.exists()) {
                System.err.println("[ERROR] No se encontró la carpeta 'frontend' al lado de 'backend'. Asegúrate de mantener la estructura del repositorio.");
                return;
            }

            File nodeModules = new File(carpetaFrontend, "node_modules");
            if (!nodeModules.exists()) {
                System.out.println("[INFO] No se detectó la carpeta node_modules. Instalando dependencias de Astro automáticamente...");
                ProcessBuilder pbInstall = new ProcessBuilder("cmd.exe", "/c", "npm install");
                pbInstall.directory(carpetaFrontend);
                pbInstall.inheritIO();
                Process procesoInstall = pbInstall.start();
                procesoInstall.waitFor();
                System.out.println("[INFO] Dependencias instaladas con éxito.");
            }

            System.out.println("[INFO] Levantando servidor Astro (Frontend) y abriendo navegador...");
            ProcessBuilder pbFront = new ProcessBuilder(
                    "cmd.exe", "/c", "start cmd /k \"npm run dev -- --open\""
            );
            pbFront.directory(carpetaFrontend);
            pbFront.start();

            System.out.println("[INFO] Todo listo. Iniciando sistema Backend...");
            Thread.sleep(1500);

        } catch (IOException | InterruptedException e) {
            System.err.println("[ERROR] Error al automatizar el arranque: " + e.getMessage());
        }
    }
}
