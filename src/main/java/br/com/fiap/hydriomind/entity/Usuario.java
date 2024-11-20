package br.com.fiap.hydriomind.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "T_HM_USUARIO") // Mapeia a coleção no MongoDB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id 
    private String id; 

    private String username; 

    private String password; 
}
