package com.amigoscode.springbootmongodb.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StudentRepo extends MongoRepository<Student, String> {

    // Queries from method name usage, find<class-name>By<parameter>
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByGender(Gender gender);

    //@Query("SELECT student FROM")
    //Optional<Student> findStudentById(String id);
}
