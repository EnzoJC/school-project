package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.repository.IStateRepository;
import edu.colegiosprisma.school.service.IStateService;
import org.springframework.stereotype.Service;

@Service
public class StateServImpl implements IStateService {
    private final IStateRepository stateRepository;

    public StateServImpl(IStateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State findById(Integer id) {
        return stateRepository.findById(id).isPresent() ? stateRepository.findById(id).get() : new State();
    }
}
