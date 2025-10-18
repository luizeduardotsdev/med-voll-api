package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizadoMedico;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping("/cadastrar")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente paciente) {
        pacienteRepository.save(new Paciente(paciente));
    }

    @GetMapping("/listar")
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return pacienteRepository.findByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping("/atualizar")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizadoMedico dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualuzarInformacoes(dados);
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void deletar(@RequestParam @PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }
}
