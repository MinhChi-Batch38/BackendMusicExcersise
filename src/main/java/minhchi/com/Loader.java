package minhchi.com;

import minhchi.com.models.User;
import minhchi.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class Loader implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    //@Autowired private PasswordEncoder passwordEncoder;


    @Autowired
    public Loader(UserRepository repository){
        this.repository = repository;
    }
    public static void main(String[] args) {
            SpringApplication.run(Loader.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        for (User emp : repository.findAll()) {
            System.out. println(emp.toString());
        }

//        String password = passwordEncoder.encode("admin");
//        User user = new User(3, "admin", password);
//        repository.save(user);
    }
}
