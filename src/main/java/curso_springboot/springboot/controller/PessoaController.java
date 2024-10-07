package curso_springboot.springboot.controller;

import curso_springboot.springboot.model.Pessoa;
import curso_springboot.springboot.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public ModelAndView inicio() {

        ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        mv.addObject("pessoaobj", new Pessoa());
        mv.addObject("pessoas", pessoasIt);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "*/salvarpessoa")
    public ModelAndView salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);

        ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        mv.addObject("pessoas", pessoasIt);
        mv.addObject("pessoaobj", new Pessoa());

        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
    public ModelAndView pessoas() {
        ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoas = pessoaRepository.findAll();
        mv.addObject("pessoas", pessoas);
        mv.addObject("pessoaobj", new Pessoa());

        return mv;

    }

    @GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

        ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
        mv.addObject("pessoaobj", pessoa.get());
        return mv;

    }

    @GetMapping("/removerpessoa/{idpessoa}")
    public ModelAndView remover(@PathVariable("idpessoa") Long idpessoa) {

        pessoaRepository.deleteById(idpessoa);

        ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
        mv.addObject("pessoas", pessoaRepository.findAll());
        mv.addObject("pessoaobj", new Pessoa());
        return mv;

    }

    @PostMapping("/pesquisapessoa")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepequisa) {

        ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
        mv.addObject("pessoas", pessoaRepository.findByNomeContaining(nomepequisa));
        mv.addObject("pessoaobj", new Pessoa());
        return mv;

    }


}
