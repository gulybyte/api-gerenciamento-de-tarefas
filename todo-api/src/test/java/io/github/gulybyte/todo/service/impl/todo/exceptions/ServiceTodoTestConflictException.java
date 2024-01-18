package io.github.gulybyte.todo.service.impl.todo.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.gulybyte.todo.exception.status.ConflictException;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.service.impl.ServiceTodoImpl;
import io.github.gulybyte.todo.util.TodoCreator;

import static io.github.gulybyte.todo.util.ArgumentsMatchersTodo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class ServiceTodoTestConflictException {

    @InjectMocks
    private ServiceTodoImpl service;

    @Mock
    private TodoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateDescription() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo());

        assertThrows(ConflictException.class,
            () -> service.updateDescription(anyTodoPutDescriptionFilter(TodoCreator.createTodoPutDescriptionFilterDefault())));

    }


    @Test
    void markAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(TodoCreator.createTodoWithDoneTrue()));

        assertThrows(ConflictException.class,
            () -> service.markAsDone(anyLong()));

    }


    @Test
    void undoneMarkAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(TodoCreator.createTodoWithDoneFalse()));

        assertThrows(ConflictException.class,
            () -> service.undoneMarkAsDone(anyLong()));

    }

}
