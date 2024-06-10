package com.lekhraj.java.spring.SB_99_RESTful_API.Runner;

import com.lekhraj.java.spring.SB_99_RESTful_API.custom.repository.StudentRepository;
import com.lekhraj.java.spring.util.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DB_01_Runner implements CommandLineRunner {
    @Autowired
    StudentRepository StudentRepo;
    @Override
    public void run(String... args) throws Exception
    {
        Print.print("DB_01_Runner" , "01-StudentRepo.assStudent()",
                StudentRepo.addStudent("Lekhraj-Dinkar"),
                StudentRepo.addStudent("Manisha-Prasad"));

        Print.print("DB_01_Runner" , "02-StudentRepo.getStudent()", StudentRepo.getStudent("Lekhraj-Dinkar") );
    }
}
