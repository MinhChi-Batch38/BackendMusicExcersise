package minhchi.com;

//import minhchi.com.models.User;
////import minhchi.com.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//import minhchi.com.models.UserMongo;
//import minhchi.com.repository.MongoUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
////import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@EnableMongoRepositories
//@EnableJpaRepositories
public class Loader implements CommandLineRunner{
    public static void main(String[] args) {
            SpringApplication.run(Loader.class, args);
    }
    @Override
            public void run(String... args) throws Exception {
        System.out.println("Start");
    }

//    @Bean
//    CommandLineRunner initDatabase(MongoUserRepository userRepository) {
//        return new CommandLineRunner() {
//
//            }
//        };
//    }


}
