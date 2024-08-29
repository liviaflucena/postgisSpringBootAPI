/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.example.model;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author caian
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "aeroportos_2014")
public class Aeroporto implements Serializable{
    // gid, objectid_1, municdv, uf, nome_uf, nome_regia, municipio, pax2010, codmun, internacio, geom
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    @Column(name = "objectid_1")
    private Integer objectid;
    @Column(name = "municdv")
    private String muniCdv;
    @Column(name = "uf")
    private String uf;
    @Column(name = "nome_uf")
    private String nomeUf;
    @Column(name = "nome_regia")
    private String regiao;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "pax2010")
    private Double pax2010;
    @Column(name = "internacio")
    private Double internacio;
    @Column(name = "geom")
    private Geometry geometria;
    
}
