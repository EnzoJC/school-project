package edu.colegiosprisma.school;

import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IStudentRepository;
import edu.colegiosprisma.school.service.IEmailService;
import edu.colegiosprisma.school.service.IParentService;
import edu.colegiosprisma.school.service.IStudentService;
import edu.colegiosprisma.school.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    IParentService parentService;

    @Test
    public void duplicateParentPrentTest(){

        Parent parent = new Parent();
        parent.setUsername("P020220001");
        Boolean exist;

        if(parent.getUsername().equals(parentService.findByUsername("123456789").getUsername())){
            exist= true;
        }
        else {
            exist = false;
        }

        Assertions.assertTrue(exist);

    }

    @Autowired
    IUserService userService;

    @Test
    public void ExistEMailUserTest(){

        List <Parent> pa = new ArrayList<>();
        List <Integer> l = new ArrayList<>();
        Parent parent = new Parent();
        parent.setEmail("zus_1997@outlook.com");
        /*parent.setPhone("123456789");
        parent.setDocumentNumber("27539208");*/

        l = parentService.verifyDuplicate(parent);
        boolean b= false;

        if(l.isEmpty()==true){

            b=true;
            Assert.isTrue(b,"no hay duplicados");
        } else{
            Assert.isTrue(b,"hay duplicados");
        }
    }

    @Test
    public void ExistPhoneUserTest(){

        List <Parent> pa = new ArrayList<>();
        List <Integer> l = new ArrayList<>();
        Parent parent = new Parent();
        parent.setPhone("955537401");

        l = parentService.verifyDuplicate(parent);
        boolean b= false;

        if(l.isEmpty()==true){

            b=true;
            Assert.isTrue(b,"no hay duplicados");
        } else{
            Assert.isTrue(b,"hay duplicados");
        }
    }

    @Test
    public void ExistDocumentTest(){

        List <Parent> pa = new ArrayList<>();
        List <Integer> l = new ArrayList<>();
        Parent parent = new Parent();
        parent.setDocumentNumber("72402301");

        l = parentService.verifyDuplicate(parent);
        boolean b= false;

        if(l.isEmpty()==true){

            b=true;
            Assert.isTrue(b,"no hay duplicados");
        } else{
            Assert.isTrue(b,"hay duplicados");
        }
    }

}