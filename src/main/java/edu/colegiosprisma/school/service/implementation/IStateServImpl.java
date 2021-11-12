package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.repository.IStateRepository;
import edu.colegiosprisma.school.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IStateServImpl implements IStateService {
    private final IStateRepository stateRepository;

    public IStateServImpl(IStateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State buscarEstadoPorId(Integer id) {
        return stateRepository.findById(id).get();
    }
}
