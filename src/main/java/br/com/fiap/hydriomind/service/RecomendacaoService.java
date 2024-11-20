package br.com.fiap.hydriomind.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hydriomind.dto.RecomendacaoDTO;
import br.com.fiap.hydriomind.entity.Recomendacao;
import br.com.fiap.hydriomind.repository.RecomendacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Listar todas as recomendações
    public List<RecomendacaoDTO> listarTodasRecomendacoes() {
        return recomendacaoRepository.findAll().stream()
                .map(recomendacao -> modelMapper.map(recomendacao, RecomendacaoDTO.class))
                .collect(Collectors.toList());
    }

    // Encontrar recomendação por ID (alterado para Long)
    public Optional<RecomendacaoDTO> encontrarRecomendacaoPorId(Long id) {
        return recomendacaoRepository.findById(id).map(recomendacao -> modelMapper.map(recomendacao, RecomendacaoDTO.class));
    }

    // Salvar recomendação
    public RecomendacaoDTO salvarRecomendacao(RecomendacaoDTO recomendacaoDTO) {
        Recomendacao recomendacao = modelMapper.map(recomendacaoDTO, Recomendacao.class);
        return modelMapper.map(recomendacaoRepository.save(recomendacao), RecomendacaoDTO.class);
    }

    // Deletar recomendação (alterado para Long)
    public void deletarRecomendacao(Long id) {
        recomendacaoRepository.deleteById(id);
    }
}
