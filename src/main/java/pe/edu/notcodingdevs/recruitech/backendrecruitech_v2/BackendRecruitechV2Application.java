package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableJpaAuditing
public class BackendRecruitechV2Application {

    public static void main(String[] args) {
        SpringApplication.run(BackendRecruitechV2Application.class, args);
    }

}
