package edu.colegiosprisma.school;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.IStudentRepository;
import edu.colegiosprisma.school.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class SchoolProjectApplicationTests {

    @Autowired
    IStudentService studentService;

    @Test
    public void duplicateDocumentStudentTest() {
        Student student1= new Student();
        Student student2= new Student();

        student1.setDocumentNumber("78017132");
        student2.setDocumentNumber("78057812");

        Boolean aBoolean = studentService.isDuplicateDocumentNumber(student1.getDocumentNumber());

        Assert.isTrue(aBoolean,"No se encontro duplicado");

        aBoolean = studentService.isDuplicateDocumentNumber(student2.getDocumentNumber());
        Assert.isTrue(aBoolean,"Se encontro duplicado"); //error
    }
    @Test
    public void duplicateEmailStudentTest(){
        Student student = new Student();
        student.setStudentEmail("pedro@gmail.com");
        //Boolean b = studentService.is(student);
       // Assert.isTrue(b, "Email duplicado");
    }

    @Autowired
    IParentService parentService;

    @Test
    public void duplicateParentTest(){

        Parent parent = new Parent();
        parent.setUsername("P020220001");
        Boolean exist;

        if(parent.getUsername().equals(parentService.findByUsername("P020220001").getUsername())){
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

        boolean b = parentService.isDuplicateEmail("zus_1997@gmail.com");
        Assert.isTrue(b, "el correo no existe");

    }

    @Test
    public void ExistPhoneUserTest(){

        boolean b = parentService.isDuplicatePhone("955537403");
        Assert.isTrue(b,"el numero no existe");

    }

    @Test
    public void ExistDocumentTest(){

        boolean b = parentService.isDuplicateDocumentNumber("72402304");
        Assert.isTrue(b,"el documento no existe");

    }

    @Test
    public void existAccountTest(){

        Parent parent = parentService.findByUsername("P020220001");
        boolean b;
        if(parent == null){
            b = false;
        }
        else
        {
            b = true;
        }

        Assert.isTrue(b, "el usuario no existe");

    }

    @Test
    public void LogAccountTest(){
        User user = new User();
        user.setId("P020220001");
        user.setPassword("78017132");

        User user2 = userService.findByUsername(user.getId());
        boolean b = false;

        if(user2!=null){
            if(BCrypt.checkpw(user.getPassword(), user2.getPassword())){
                b = true;
            }
        }
        Assert.isTrue(b,"cuenta no encontrada");
    }

    @Test
    public void verificateUpdateParentNameTest(){

        Parent parent = parentService.findByUsername("P020220001");
        parent.setGivenNames("Andre");
        Parent aux = parentService.update(parent, parent.getId());

        Assert.isTrue(parent.getGivenNames().equals(aux.getGivenNames()),"actualizacación correcta");

    }

    @Test
    public void verificateUpdateParentLastNameTest(){

        Parent parent = parentService.findByUsername("P020220001");
        parent.setFirstLastName("Arias");
        Parent aux = parentService.update(parent, parent.getId());

        Assert.isTrue(parent.getGivenNames().equals(aux.getGivenNames()),"actualizacación correcta");

    }

    @Test
    public void verificateUpdateParentSecondNameTest(){

        Parent parent = parentService.findByUsername("P020220001");
        parent.setSecondLastName("Muñoz");
        Parent aux = parentService.update(parent, parent.getId());

        Assert.isTrue(parent.getGivenNames().equals(aux.getGivenNames()),"actualizacación correcta");

    }

    @Test
    public void verificateUpdateParentEmailTest(){

        Parent parent = parentService.findByUsername("P020220001");
        parent.setEmail("zus_1997@gmail.com");
        Parent aux = parentService.update(parent, parent.getId());

        Assert.isTrue(parent.getGivenNames().equals(aux.getGivenNames()),"actualizacación correcta");

    }

    @Test
    public void verificateUpdateParentPhoneTest(){

        Parent parent = parentService.findByUsername("P020220001");
        parent.setEmail("zus_1997@gmail.com");
        Parent aux = parentService.update(parent, parent.getId());

        Assert.isTrue(parent.getGivenNames().equals(aux.getGivenNames()),"actualizacación correcta");

    }
    @Test
    public void verificateUpdateParentAdressTest(){

        Parent parent = parentService.findByUsername("P020220001");
        parent.setAddress("Los Pinos");
        Parent aux = parentService.update(parent, parent.getId());

        Assert.isTrue(parent.getGivenNames().equals(aux.getGivenNames()),"actualizacación correcta");

    }
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IStateService stateService;
    @Autowired
    IEnrollmentService enrollmentService;

    @Test
    void verificarActualizacionDePago(){
        Student student= studentService.findByUsername("S020220017");
        State state = stateService.findById(5); // 5: Pendiente de pago
        String description="pagado con Tarjeta";
        transactionService.pay(student,state,description);
        Transaction transaction=transactionService.getAll().stream().collect(Collectors.toList()).get(
                transactionService.getAll().stream().collect(Collectors.toList()).size()-1
        );
        enrollmentService.updateStatusForNewStudent(student, 2); // 2: Pre-inscrito

        Assert.isTrue(transaction.getState().getId()==7,"No se Actualizo Estado de Transaccion de Pago");

    }


}
