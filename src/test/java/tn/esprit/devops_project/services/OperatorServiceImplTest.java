package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();
        // Add some test operators to the list
        operators.add(new Operator());
        when(operatorRepository.findAll()).thenReturn(operators);
        List<Operator> retrievedOperators = operatorService.retrieveAllOperators();
        assertEquals(1, retrievedOperators.size()); // Check if the list contains one operator
    }

    @Test
    void testAddOperator() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);
        Operator addedOperator = operatorService.addOperator(operator);
        assertEquals(operator, addedOperator);
    }

    @Test
    void testDeleteOperator() {
        Long operatorId = 1L;
        doNothing().when(operatorRepository).deleteById(operatorId);
        operatorService.deleteOperator(operatorId);
        verify(operatorRepository, times(1)).deleteById(operatorId);
    }

    @Test
    void testUpdateOperator() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);
        Operator updatedOperator = operatorService.updateOperator(operator);
        assertEquals(operator, updatedOperator);
    }

    @Test
    void testRetrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        when(operatorRepository.findById(operatorId)).thenReturn(java.util.Optional.of(operator));
        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);
        assertEquals(operator, retrievedOperator);
    }
}
