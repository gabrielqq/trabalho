package br.unicesumar.restserver.Disciplina;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
@Transactional
public class DisciplinaController {
    @Autowired
    private EntityManager em;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Disciplina> getDisciplinas(){
        Query query = em.createQuery("from Disciplina");
        return query.getResultList();
    }
   
  // @RequestMapping(method = RequestMethod.POST)
  // public void criarDisciplina(@RequestBody Disciplina disciplina) {
  //   em.persist(disciplina);
  // }
    
   @RequestMapping(method = RequestMethod.POST)
   public void criarDisciplina(@RequestParam Long id,@RequestParam String nome, @RequestParam Integer cargaHoraria,@RequestParam Double peso) {
       Disciplina disciplina = new Disciplina(id,nome,cargaHoraria,peso);
       em.persist(disciplina);
   }
    
   @RequestMapping(method = RequestMethod.PUT)
   public void alterarDisciplina(@RequestBody Disciplina disciplina) {
       em.merge(disciplina);
   }
    
   @RequestMapping(method = RequestMethod.DELETE)
   public void excluirVeiculo(@PathVariable Long id) {
       em.createQuery("delete Disciplina where id = :id").setParameter("id", id);
   } 
}
