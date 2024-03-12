package com.example.demo.controllers;

import com.example.demo.modelo.ClienteModel;
import com.example.demo.repositorio.Repositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    @Autowired
    private Repositorio db;

    //Rota de mostrar Todos os cadastros
   @GetMapping("/cliente")
    public List<ClienteModel> selecionar(){
        return db.findAll();
    }

    //Filtros
    @GetMapping("/cliente/{nome}")
    public List<ClienteModel> filterByName(@PathVariable String nome){
       return db.findByNomeContaining(nome);
    }

    @GetMapping("/cliente/cpf")
    public ClienteModel filterByCpfOrCnpj(@RequestBody String cpfCnpj){

       return db.findByCpfCnpj(cpfCnpj);

    }

    //Rota de CREATE
    @PostMapping("/cliente")
    public ClienteModel cadastrar(@RequestBody ClienteModel pessoa){
        return db.save(pessoa);
    }

    //Rota de DELETE
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<String> remove (@PathVariable String id){
       if(db.existsById(id)){
           db.deleteById(id);
           return ResponseEntity.ok("Cliente excluido");
       }else{
           return ResponseEntity.status(404).body("Não encontrado");
       }

    }

    //Rota de PUT
    @PutMapping("/cliente/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable String id, @RequestBody ClienteModel clienteNovo){
        if(db.existsById(id)){
            ClienteModel clienteExistente = db.findById(id).get();

            clienteExistente.setNome(clienteNovo.getNome());
            clienteExistente.setTipoPessoa(clienteNovo.getTipoPessoa());
            clienteExistente.setCpfCnpj(clienteNovo.getCpfCnpj());
            clienteExistente.setCep(clienteNovo.getCep());
            clienteExistente.setEndereco(clienteNovo.getEndereco());
            clienteExistente.setBairro(clienteNovo.getBairro());
            clienteExistente.setNumero(clienteNovo.getNumero());
            clienteExistente.setEstado(clienteNovo.getEstado());

            db.save(clienteExistente);

            return ResponseEntity.ok("Cliente atualizado com sucesso! ");
        }else{
            return ResponseEntity.status(404).body("Cliente não encontrado");
        }
    }
}



