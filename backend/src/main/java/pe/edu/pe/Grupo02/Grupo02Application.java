package pe.edu.pe.Grupo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Grupo02Application {

    //Aqui se ejecuta el main xddd
    public static void main(String[] args) {
        // Evitamos que Spring DevTools duplique la ejecución al reiniciar el contexto
        if (System.getProperty("astro.launcher.active") == null) {
            System.setProperty("astro.launcher.active", "true");
            // 1. Primero automatizamos el entorno para levantar Astro (Solo la primera vez)
            automatizarEntorno();
        }

        // 2. Luego arrancamos tu aplicación real (Grupo02Application)
        SpringApplication.run(Grupo02Application.class, args);
    }

    private static void automatizarEntorno() {
        try {
            // RUTA UNIVERSAL: Calcula las carpetas relativas en base a la ubicación real de ejecución
            File directorioActual = new File(".").getCanonicalFile();
            File carpetaFrontend = new File(directorioActual.getParentFile(), "frontend").getCanonicalFile();

            System.out.println("[INFO] Ruta base del backend detectada: " + directorioActual.getAbsolutePath());
            System.out.println("[INFO] Ruta calculada para el frontend: " + carpetaFrontend.getAbsolutePath());

            // Validación de seguridad para dar soporte al usuario o profesor
            if (!carpetaFrontend.exists()) {
                System.err.println("[ERROR] No se encontró la carpeta 'frontend' al lado de 'backend'. Asegúrate de mantener la estructura del repositorio.");
                return;
            }

            System.out.println("[INFO] Tu base de datos almacen_db ya está corriendo en segundo plano localmente...");
            Thread.sleep(1000);

            System.out.println("[INFO] Levantando servidor Astro (Frontend) y abriendo navegador...");

            // Abre la ventana de comandos de Windows, entra a la carpeta calculada de Astro y lo ejecuta
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
