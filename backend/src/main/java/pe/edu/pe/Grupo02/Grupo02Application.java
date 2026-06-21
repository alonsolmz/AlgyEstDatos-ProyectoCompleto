package pe.edu.pe.Grupo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Grupo02Application {

    //Aqui se ejecuta el main xddd
	public static void main(String[] args) {
		SpringApplication.run(Grupo02Application.class, args);

        automatizarEntorno();

        // 2. Luego arrancamos tu aplicación real (Grupo02Application)
        SpringApplication.run(Grupo02Application.class, args);
	}


    private static void automatizarEntorno() {
        try {
            String rutaFrontend = "C:\\Users\\PC\\Downloads\\frontend_algyestdatosgrupo02";

            System.out.println("[INFO] Tu base de datos almacen_db ya está corriendo en segundo plano localmente...");
            Thread.sleep(1000);

            System.out.println("[INFO] Levantando servidor Astro (Frontend) y abriendo navegador...");
            // Abre la ventana de comandos de Windows, entra a la carpeta del front y ejecuta Astro
            ProcessBuilder pbFront = new ProcessBuilder(
                    "cmd.exe", "/c", "start cmd /k \"npm run dev -- --open\""
            );
            pbFront.directory(new File(rutaFrontend));
            pbFront.start();

            System.out.println("[INFO] Todo listo. Iniciando sistema Backend...");
            Thread.sleep(1000);

        } catch (IOException | InterruptedException e) {
            System.err.println("[ERROR] Error al automatizar el arranque: " + e.getMessage());
        }
    }
}
