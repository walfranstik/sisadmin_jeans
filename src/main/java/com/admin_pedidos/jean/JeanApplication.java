package com.admin_pedidos.jean;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.Desktop;


@SpringBootApplication
public class JeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeanApplication.class, args);
	}

	//   @EventListener(ApplicationReadyEvent.class)
    // public void abrirNavegador() {
    //     try {
    //         String url = "http://localhost:9191";
    //         if (Desktop.isDesktopSupported()) {
    //             Desktop desktop = Desktop.getDesktop();
    //             desktop.browse(new URI(url));
    //         } else {
    //             Runtime runtime = Runtime.getRuntime();
    //             runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

}
