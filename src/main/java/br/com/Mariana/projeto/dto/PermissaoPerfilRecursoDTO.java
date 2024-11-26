package br.com.Mariana.projeto.dto;

import org.springframework.beans.BeanUtils;

import br.com.Mariana.projeto.entity.PermissaoPerfilRecursoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PermissaoPerfilRecursoDTO {

    private Long id;
    private PerfilDTO perfil;
    private RecursoDTO recurso;

    public PermissaoPerfilRecursoDTO(PermissaoPerfilRecursoEntity permissaoPerfilRecurso) {
        if (permissaoPerfilRecurso != null) {
            BeanUtils.copyProperties(permissaoPerfilRecurso, this);
            if (permissaoPerfilRecurso.getRecurso() != null) {
                this.recurso = new RecursoDTO(permissaoPerfilRecurso.getRecurso()); 
            }
            if (permissaoPerfilRecurso.getPerfil() != null) {
                this.perfil = new PerfilDTO(permissaoPerfilRecurso.getPerfil());
            }
        }
    }
}
