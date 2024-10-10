package curso_springboot.springboot.controller;

import curso_springboot.springboot.model.Pessoa;
import curso_springboot.springboot.model.Telefone;
import curso_springboot.springboot.repository.PessoaRepository;
import curso_springboot.springboot.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping("/telefones/{idPessoa}")
    public ModelAndView telefones(@PathVariable("idPessoa") Long idPessoa) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);

        ModelAndView mv = new ModelAndView("cadastro/telefones");
        mv.addObject("pessoaObj", pessoa.get());
        mv.addObject("foneObj", telefoneRepository.getTelefones(idPessoa));

        return mv;
    }

    @PostMapping("/adicionarFone/{pessoaId}")
    public ModelAndView adicionarFone(Telefone telefone, @PathVariable("pessoaId") Long pessoaId){

        Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
        telefone.setPessoa(pessoa);

        telefoneRepository.save(telefone);

        ModelAndView mv = new ModelAndView("cadastro/telefones");
        mv.addObject("pessoaObj", pessoa);
        mv.addObject("telefones", telefoneRepository.getTelefones(pessoaId));
        return mv;

    }

    @GetMapping("/removerFone/{idTelefone}")
    public ModelAndView removerFone(@PathVariable("idTelefone") Long idTelefone) {

        Pessoa pessoa = telefoneRepository.findById(idTelefone).get().getPessoa();

        telefoneRepository.deleteById(idTelefone);

        ModelAndView mv = new ModelAndView("cadastro/telefones");
        mv.addObject("pessoaObj", pessoa);
        mv.addObject("telefones", telefoneRepository.getTelefones(pessoa.getId()));
        return mv;

    }

}
