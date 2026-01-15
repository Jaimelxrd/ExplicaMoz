package mz.dev.lxrd.ExplicaMoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ExplicaMozApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExplicaMozApplication.class, args);
	}

}
