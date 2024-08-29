package br.com.example.controller;

import br.com.example.model.MunicipioDTO;
import java.util.List;
import java.util.ArrayList;
import br.com.example.repository.MunicipioRepository;
import br.com.example.model.MunicipioView;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@OpenAPIDefinition(info = @Info(title = "Geográfico API", version = "1.0", description = "Dados de geograficos do Brasil"))
public class MunicipioController {
    
    @Autowired
    private MunicipioRepository municipioRepository;

     
    private MunicipioDTO municipioViewSemGeometry(MunicipioView municipioView) {
        return new MunicipioDTO(
                        municipioView.getCodigo(), 
                        municipioView.getNome(), 
                        municipioView.getUf());
    }
    private MunicipioDTO municipioViewComGeometry(MunicipioView municipioView) {
        return new MunicipioDTO(
                            municipioView.getCodigo(), 
                            municipioView.getNome(), 
                            municipioView.getUf(), 
                            municipioView.getGeometria());
    }
    
     
    @GetMapping("/municipios/fronteira/{nome}")
    public List<MunicipioDTO> municipiosVizinhos(
            @PathVariable String nome,
            @RequestParam(value = "geometry", defaultValue = "false") boolean geometry){
        
        List<MunicipioDTO> response = new ArrayList<MunicipioDTO>();
        if(geometry) {
            municipioRepository.listarMunicipiosVizinhos(nome)
                    .forEach(municipio -> response.add(municipioViewComGeometry(municipio)));
            
        } else {
            municipioRepository.listarMunicipiosVizinhos(nome)
                    .forEach(municipio -> response.add(municipioViewSemGeometry(municipio)));
        }
      
        return response;
    };
   
    
    @GetMapping("/distancia_entre_municipios/{municipioA}/{municipioB}")
    public Double distanciaEntreMunicipios(@PathVariable String municipioA, @PathVariable String municipioB){
        double result = municipioRepository.distanciaEntreMunicipios(municipioA, municipioB);
        return result;
    }
    
    
    @GetMapping("/municipios/estado/{uf}")
    public List<MunicipioDTO> municipiosEstado(
            @PathVariable String uf,
            @RequestParam(value = "geometry", defaultValue = "false") boolean geometry){
        List<MunicipioDTO> response = new ArrayList<MunicipioDTO>();
        if(geometry) {
            municipioRepository.municipiosEstado(uf)
                    .forEach(municipio -> response.add(municipioViewComGeometry(municipio)));
        } else {
            municipioRepository.municipiosEstado(uf)
                    .forEach(municipio -> response.add(municipioViewSemGeometry(municipio)));
        }
        
        return response;
    }
    
    
    @GetMapping("/quantidade_municipios_estado/{uf}")
    public Integer quantidadeMunicipiosEstado(@PathVariable String uf) {
        return municipioRepository.quantidadeMunicipiosEstado(uf);
    }
    
    
  
    
    @GetMapping("/municipios/raio_km/{distancia}/{nomeMunicipio}")
    public List<MunicipioDTO> municipiosRaioKm(
            @PathVariable String nomeMunicipio, 
            @PathVariable int distancia,
            @RequestParam(value = "geometry", defaultValue = "false") boolean geometry) {
        List<MunicipioDTO> response = new ArrayList<MunicipioDTO>();
        if(geometry) {
            municipioRepository.municipiosRaioKm(nomeMunicipio, distancia*1000)
                    .forEach(municipio -> response.add(municipioViewComGeometry(municipio)));
        } else {
            municipioRepository.municipiosRaioKm(nomeMunicipio, distancia*1000)
                    .forEach(municipio -> response.add(municipioViewSemGeometry(municipio)));
        }
        return response;
    }
    
    
    @GetMapping("/municipios/fronteira/estado/{uf}")
    public List<MunicipioDTO> municipiosFronteiraEstado(
            @PathVariable String uf,
            @RequestParam(value = "geometry", defaultValue = "false") boolean geometry) {
        List<MunicipioDTO> response = new ArrayList<MunicipioDTO>();
        if(geometry) {
            municipioRepository.municipiosFronteiraEstado(uf)
                    .forEach(municipio -> response.add(municipioViewComGeometry(municipio)));
        } else {
            municipioRepository.municipiosFronteiraEstado(uf)
                    .forEach(municipio -> response.add(municipioViewSemGeometry(municipio)));
        }
        return response;
    }
    
    
    @GetMapping("/municipios/nome/estado/{uf}")
    public List<String> nomeMunicipiosEstudo(@PathVariable String uf) {
        return municipioRepository.nomeMunicipiosEstudo(uf);
    }
}