package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.Endereco;

public record DadosAtualizadosPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco) {
}
