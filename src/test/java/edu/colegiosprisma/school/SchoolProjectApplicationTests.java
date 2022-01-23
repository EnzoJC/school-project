package edu.colegiosprisma.school;

import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.repository.IStudentRepository;
import edu.colegiosprisma.school.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class SchoolProjectApplicationTests {

    @Autowired
    IStudentService studentService;

    @Test
    public void duplicateDocumentTest() {
        Student student1= new Student();
        Student student2= new Student();

        student1.setDocumentNumber("78017132");
        student2.setDocumentNumber("78057812");

        Boolean aBoolean = studentService.verifyDuplicate(student1);

        Assert.isTrue(aBoolean,"No se encontro duplicado");

        aBoolean = studentService.verifyDuplicate(student2);
        Assert.isTrue(aBoolean,"Se encontro duplicado"); //error
    }

}
