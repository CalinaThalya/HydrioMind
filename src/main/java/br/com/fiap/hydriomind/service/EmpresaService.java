package br.com.fiap.hydriomind.service;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hydriomind.dto.EmpresaDTO;
import br.com.fiap.hydriomind.entity.Empresa;
import br.com.fiap.hydriomind.repository.EmpresaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        // Configurações do ModelMapper
        modelMapper.typeMap(EmpresaDTO.class, Empresa.class).addMappings(mapper -> {
            mapper.map(EmpresaDTO::getNomeEmpresa, Empresa::setNomeEmpresa);
            mapper.map(EmpresaDTO::getNomeFantasia, Empresa::setNomeFantasia);
            mapper.map(EmpresaDTO::getCnpj, Empresa::setCnpj);
        });
        modelMapper.typeMap(Empresa.class, EmpresaDTO.class).addMappings(mapper -> {
            mapper.map(Empresa::getNomeEmpresa, EmpresaDTO::setNomeEmpresa);
            mapper.map(Empresa::getNomeFantasia, EmpresaDTO::setNomeFantasia);
            mapper.map(Empresa::getCnpj, EmpresaDTO::setCnpj);
        });
    }

    public List<EmpresaDTO> listarTodasEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<EmpresaDTO> encontrarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).map(empresa -> modelMapper.map(empresa, EmpresaDTO.class));
    }

    public EmpresaDTO salvarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);
        return modelMapper.map(empresaRepository.save(empresa), EmpresaDTO.class);
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}
