package curso_springboot.springboot.controller;

import curso_springboot.springboot.model.Pessoa;
import curso_springboot.springboot.model.Telefone;
import curso_springboot.springboot.repository.PessoaRepository;
import curso_springboot.springboot.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/cadastroPessoa")
    public ModelAndView cadastroPessoa() {

        ModelAndView mv = new ModelAndView("cadastro/cadastroPessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        mv.addObject("pessoaObj", new Pessoa());
        mv.addObject("pessoas", pessoasIt);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvarPessoa")
    public ModelAndView salvar(Pessoa pessoa) {

        pessoaRepository.save(pessoa);

        ModelAndView mv = new ModelAndView("cadastro/cadastroPessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        mv.addObject("pessoas", pessoasIt);
        mv.addObject("pessoaObj", new Pessoa());

        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listaPessoas")
    public ModelAndView listaPessoas() {

        ModelAndView mv = new ModelAndView("cadastro/cadastroPessoa");
        Iterable<Pessoa> pessoas = pessoaRepository.findAll();
        mv.addObject("pessoas", pessoas);
        mv.addObject("pessoaObj", new Pessoa());

        return mv;

    }

    @GetMapping("/editarPessoa/{idPessoa}")
    public ModelAndView editarPessoa(@PathVariable("idPessoa") Long idPessoa) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);

        ModelAndView mv = new ModelAndView("cadastro/cadastroPessoa");
        mv.addObject("pessoaObj", pessoa.get());
        return mv;

    }

    @GetMapping("/removerPessoa/{idPessoa}")
    public ModelAndView removerPessoa(@PathVariable("idPessoa") Long idPessoa) {

        pessoaRepository.deleteById(idPessoa);

        ModelAndView mv = new ModelAndView("cadastro/cadastroPessoa");
        mv.addObject("pessoas", pessoaRepository.findAll());
        mv.addObject("pessoaObj", new Pessoa());
        return mv;

    }

    @PostMapping("/pesquisaPessoa")
    public ModelAndView pesquisaPessoa(@RequestParam("nomePesquisa") String nomePequisa) {

        ModelAndView mv = new ModelAndView("cadastro/cadastroPessoa");
        mv.addObject("pessoas", pessoaRepository.findByNomeContaining(nomePequisa));
        mv.addObject("pessoaObj", new Pessoa());
        return mv;

    }


    
}
