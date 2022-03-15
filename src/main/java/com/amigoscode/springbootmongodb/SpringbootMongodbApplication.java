package com.amigoscode.springbootmongodb;

import com.amigoscode.springbootmongodb.student.Address;
import com.amigoscode.springbootmongodb.student.Gender;
import com.amigoscode.springbootmongodb.student.Student;
import com.amigoscode.springbootmongodb.student.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbApplication.class, args);
    }

    // mongoTemplate, mongo query'lerini kullanmamızı sağlar.
    @Bean
    CommandLineRunner runner(StudentRepo repo, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address(
                    "England",
                    "London",
                    "NE9"
            );
            String email = "jahmed@gmail.com";
            Student student = new Student(
                    "Jamila",
                    "Ahmed",
                    email,
                    Gender.FEMALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
                    );

           // usingMongoTemplateAndQuery(repo, mongoTemplate, email, student);

            repo.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
                        System.out.println(s + " is already exists!");
                    }, () ->{
                        System.out.println("Inserting student: "+ student);
                        repo.insert(student);
                    });

        };
    }

    private void usingMongoTemplateAndQuery(StudentRepo repo, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1){
            throw new IllegalStateException(
                    "found many students with email" + email
            );
        }

        if (students.isEmpty()){
            System.out.println("Inserting student: "+ student);
            repo.insert(student);
        }else {
            System.out.println(student + " is already exists!");
        }
    }

}
