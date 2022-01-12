package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Parent;

public interface IParentRepository extends IUserRepository {
    Parent findByDocumentNumberOrEmailOrPhone(String documentNumber, String email, String phone);

    //    // findByDocumentNumber: permite buscar un parent por su documento
//    Parent findByDocumentNumber(String documentNumber);
    // findByStudent: permite buscar un parent por un correo
    Parent findByEmail(String email);

    // findByStudent: permite buscar un parent por un teléfono
    Parent findByPhone(String phone);
//    Parent findByEmailOrPhoneOr(String email);
}
