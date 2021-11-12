package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStateService;
import edu.colegiosprisma.school.service.IStudentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {
    private final ITransactionService transactionService;
    private final IEnrollmentService enrollmentService;
    private final IStudentService studentService;
    private final IStateService stateService;

    public TransactionController(ITransactionService transactionService, IEnrollmentService enrollmentService,
                                 IStudentService studentService, IStateService stateService) {
        this.transactionService = transactionService;
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.stateService = stateService;
    }

    @PostMapping("parent/admision/pay")
    public String pagarMatriculaNuevoEstudiante(@RequestParam("idStudent") String idStudent) {
        // El método getStudentById() permite obtener el estudiante a partir del id
        // que se le pasa como parámetro.
        // getStudentById() retorna un tipo de dato Optional<Student>
        // Si el estudiante existe, se obtiene el objeto Student.
        // Si el estudiante no existe, se obtiene un objeto Optional<Student> vacío.
        // El método get() del objeto Optional<Student> permite obtener el objeto
        // Student del Optional<Student>
        // Este método permite verificar que el pago a realizar corresponde a un estudiante
        Student student = (Student) studentService.getStudentById(idStudent).get();
        State state = stateService.buscarEstadoPorId(5); // 5: Pendiente de pago
        if (transactionService.payTransaction(student, state))
            enrollmentService.updateStatusForNewStudent(student, 2); // 2: Pre-inscrito
        return "redirect:/parent/admision";
    }
}
