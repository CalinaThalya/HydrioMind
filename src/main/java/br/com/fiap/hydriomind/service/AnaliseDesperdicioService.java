package br.com.fiap.hydriomind.service;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hydriomind.dto.AnaliseDesperdicioDTO;
import br.com.fiap.hydriomind.entity.AnaliseDesperdicio;
import br.com.fiap.hydriomind.repository.AnaliseDesperdicioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AnaliseDesperdicioService {

    @Autowired
    private AnaliseDesperdicioRepository analiseDesperdicioRepository;

    @Autowired
    private ModelMapper modelMapper;

   @PostConstruct
public void init() {
    // Configurações do ModelMapper
    modelMapper.typeMap(AnaliseDesperdicioDTO.class, AnaliseDesperdicio.class).addMappings(mapper -> {
        mapper.map(AnaliseDesperdicioDTO::getNomeProduto, AnaliseDesperdicio::setNomeProduto);
        mapper.map(AnaliseDesperdicioDTO::getQuantidadeDesperdiciada, AnaliseDesperdicio::setQuantidadeDesperdiciada);
        mapper.map(AnaliseDesperdicioDTO::getUnidadeMedida, AnaliseDesperdicio::setUnidadeMedida);
        mapper.map(AnaliseDesperdicioDTO::getMotivoDesperdicio, AnaliseDesperdicio::setMotivoDesperdicio);
    });

    modelMapper.typeMap(AnaliseDesperdicio.class, AnaliseDesperdicioDTO.class).addMappings(mapper -> {
        mapper.map(AnaliseDesperdicio::getNomeProduto, AnaliseDesperdicioDTO::setNomeProduto);
        mapper.map(AnaliseDesperdicio::getQuantidadeDesperdiciada, AnaliseDesperdicioDTO::setQuantidadeDesperdiciada);
        mapper.map(AnaliseDesperdicio::getUnidadeMedida, AnaliseDesperdicioDTO::setUnidadeMedida);
        mapper.map(AnaliseDesperdicio::getMotivoDesperdicio, AnaliseDesperdicioDTO::setMotivoDesperdicio);
    });
}


    public List<AnaliseDesperdicioDTO> listarTodasAnalises() {
    return StreamSupport.stream(analiseDesperdicioRepository.findAll().spliterator(), false)
                .map(analise -> modelMapper.map(analise, AnaliseDesperdicioDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<AnaliseDesperdicioDTO> encontrarAnalisePorId(Long id) {
        return analiseDesperdicioRepository.findById(id).map(analise -> modelMapper.map(analise, AnaliseDesperdicioDTO.class));
    }

    public AnaliseDesperdicioDTO salvarAnalise(AnaliseDesperdicioDTO analiseDesperdicioDTO) {
        AnaliseDesperdicio analise = modelMapper.map(analiseDesperdicioDTO, AnaliseDesperdicio.class);
        return modelMapper.map(analiseDesperdicioRepository.save(analise), AnaliseDesperdicioDTO.class);
    }

    public void deletarAnalise(Long id) {
        analiseDesperdicioRepository.deleteById(id);
    }
}
