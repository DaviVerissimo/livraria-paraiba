package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.FormaDePagamento;
import br.edu.ifpb.dac.livrariaParaiba.repository.FormaDePagamentoRepository;

@Service
public class FormaDePagamentoService {

	@Autowired
	FormaDePagamentoRepository formaDePagamentoRepository;

	public FormaDePagamento save(FormaDePagamento fdp) {
		return formaDePagamentoRepository.save(fdp);
	}

	public void deleteById(long id) {
		formaDePagamentoRepository.deleteById(id);
	}

	public void update(long id, FormaDePagamento fdp) {
		FormaDePagamento formaSalva = formaDePagamentoRepository.findById(id);
		if (formaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(fdp, formaSalva);
		formaDePagamentoRepository.save(fdp);
	}

	public FormaDePagamento findById(long id) {
		return formaDePagamentoRepository.findById(id);
	}

	public List<FormaDePagamento> findAll() {
		return formaDePagamentoRepository.findAll();
	}
}
