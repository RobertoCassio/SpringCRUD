package com.api.produto.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Not blank e Not Null aqui para validar que o valor recebido não seja vazio/ nulo;
public record ProductRecordDTO(@NotBlank String name, @NotNull BigDecimal Value) { //Record já traz vários métodos prontos | São imutáveis.

}
