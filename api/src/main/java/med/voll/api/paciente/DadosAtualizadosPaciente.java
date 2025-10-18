package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.Endereco;

public record DadosAtualizadosPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco) {
}
