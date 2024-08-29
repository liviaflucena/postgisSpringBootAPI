/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.example.model;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author caian
 */

@Data
@AllArgsConstructor
@Entity
@Table(name = "br_uf_2020")
public class Estado implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    @Column(name = "cd_uf")
    private String codigo;
    @Column(name = "nm_uf")
    private String nome;
    @Column(name = "sigla_uf")
    private String sigla;
    @Column(name = "nm_regiao")
    private String nomeRegiao;
    @Column(name = "geom")
    private Geometry geometria;
    
}
