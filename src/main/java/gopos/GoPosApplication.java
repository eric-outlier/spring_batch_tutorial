package gopos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"gopos", "cmm", "cmmcloud"})
public class GoPosApplication {

  public static void main(String[] args) {
    SpringApplication.run(GoPosApplication.class, args);
  }

}
