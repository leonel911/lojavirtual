package com.loja.virtual.services.validation.utils;

import com.loja.virtual.domain.dto.ClienteNewDto;
import com.loja.virtual.repositories.ClienteRepository;
import com.loja.virtual.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteNewDto value, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        for (FieldMessage fieldMessage: list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
