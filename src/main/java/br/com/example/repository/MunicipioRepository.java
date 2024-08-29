/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.model.Municipio;
import br.com.example.model.MunicipioView;

/**
 *
 * @author caian
 */
public interface MunicipioRepository extends JpaRepository<Municipio, Integer>{
    
    @Query(value = "select new br.com.example.model.MunicipioView(m2.codigo, m2.nome, m2.sigla, m2.geometria) "
            + "from Municipio m1, Municipio m2 where touches(m2.geometria, m1.geometria) = true and m1.nome = :nome ORDER BY m2.nome")
    List<MunicipioView> listarMunicipiosVizinhos(String nome);
    
    
    @Query(value ="select distance(geography(ma.geometria), geography(mb.geometria)) from Municipio ma, Municipio mb where ma.nome = :municipioA  and   mb.nome = :municipioB")
    public Double distanciaEntreMunicipios(String municipioA, String municipioB);
    
    
    @Query(value = "SELECT new br.com.example.model.MunicipioView(m.codigo, m.nome, m.sigla, m.geometria) FROM Municipio m, Estado e\n"+
                    "WHERE e.sigla = :uf_estado AND within(m.geometria, e.geometria) = true ORDER BY m.nome")
    List<MunicipioView> municipiosEstado(String uf_estado);
    
    
    @Query(value = "SELECT count(m.geometria) FROM Municipio m\n" +
                    "INNER JOIN Estado uf ON st_within(m.geometria, uf.geometria) = true\n" +
                    "WHERE uf.sigla = :uf")
    public Integer quantidadeMunicipiosEstado(String uf);
    
    
    
    @Query(value = "SELECT new br.com.example.model.MunicipioView(m2.codigo, m2.nome, m2.sigla, m2.geometria) \n" +
                    "FROM Municipio m1 INNER JOIN Municipio m2\n" +
                    "ON DWithin(geography(ST_Centroid(m1.geometria)), geography(ST_Centroid(m2.geometria)), :distancia) = true\n" +
                    "WHERE m1.nome = :nome AND m2.nome != :nome")
    List<MunicipioView> municipiosRaioKm(String nome, int distancia);
    
    
    @Query(value = "SELECT new br.com.example.model.MunicipioView(m.codigo, m.nome, m.sigla, m.geometria) FROM Municipio m\n" +
                    "INNER JOIN Estado e ON st_touches(m.geometria, e.geometria) = true\n" +
                    "WHERE e.sigla = :uf ORDER BY m.nome")
    List<MunicipioView> municipiosFronteiraEstado(String uf);
    
    
    @Query(value = "SELECT nome FROM Municipio WHERE sigla = :uf ORDER BY nome")
    List<String> nomeMunicipiosEstudo(String uf);
}
