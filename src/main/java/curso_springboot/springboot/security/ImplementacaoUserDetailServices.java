package curso_springboot.springboot.security;

import curso_springboot.springboot.model.Usuario;
import curso_springboot.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImplementacaoUserDetailServices  implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Usuario usuario = usuarioRepository.findUserByLogin(username);

          if (usuario == null) {
              throw new UsernameNotFoundException("Usuário não foi encontrado");
          }
        return new User(username, usuario.getPassword(),usuario.isEnabled(),
                true, true,
                true, usuario.getAuthorities());
    }
}
