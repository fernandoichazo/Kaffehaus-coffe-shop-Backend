package com.ucb.kaffehaus.personal.cliente.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucb.kaffehaus.personal.cliente.application.CreateClienteUseCase;
import com.ucb.kaffehaus.personal.cliente.application.DeleteClienteUseCase;
import com.ucb.kaffehaus.personal.cliente.application.GetAllClienteUseCase;
import com.ucb.kaffehaus.personal.cliente.application.GetOneClienteUseCase;
import com.ucb.kaffehaus.personal.cliente.application.UpdateClienteUseCase;
import com.ucb.kaffehaus.personal.cliente.application.dto.ClienteResponse;
import com.ucb.kaffehaus.personal.cliente.application.dto.CreateClienteRequest;
import com.ucb.kaffehaus.personal.cliente.application.dto.UpdateClienteRequest;
import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.shared.application.dto.PaginatedResponse;
import com.ucb.kaffehaus.shared.application.dto.PaginationDto;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

	private final CreateClienteUseCase createClienteUseCase;
	private final UpdateClienteUseCase updateClienteUseCase;
	private final GetAllClienteUseCase getAllClienteUseCase;
	private final GetOneClienteUseCase getOneClienteUseCase;
	private final DeleteClienteUseCase deleteClienteUseCase;

	public ClienteController(
			CreateClienteUseCase createClienteUseCase,
			UpdateClienteUseCase updateClienteUseCase,
			GetAllClienteUseCase getAllClienteUseCase,
			GetOneClienteUseCase getOneClienteUseCase,
			DeleteClienteUseCase deleteClienteUseCase) {
		this.createClienteUseCase = createClienteUseCase;
		this.updateClienteUseCase = updateClienteUseCase;
		this.getAllClienteUseCase = getAllClienteUseCase;
		this.getOneClienteUseCase = getOneClienteUseCase;
		this.deleteClienteUseCase = deleteClienteUseCase;
	}

	@PostMapping
	public ResponseEntity<ClienteResponse> createCliente(@RequestBody CreateClienteRequest request) {
		request.validate();
		Cliente cliente = this.createClienteUseCase.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.from(cliente));
	}

	@GetMapping
	public ResponseEntity<PaginatedResponse<List<ClienteResponse>>> getAllClientes(
			@RequestParam(required = false) String search,
			@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) Integer skip) {
		PaginationDto pagination = new PaginationDto(search, limit, skip);
		PaginatedResponse<java.util.List<Cliente>> paged = this.getAllClienteUseCase.execute(pagination);
		java.util.List<ClienteResponse> clientes = paged.getData().stream()
				.map(ClienteResponse::from)
				.toList();
		return ResponseEntity.ok(PaginatedResponse.of(clientes, paged.getTotal()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> getOneCliente(@PathVariable UUID id) {
		Cliente cliente = this.getOneClienteUseCase.execute(id).get();
		return ResponseEntity.ok(ClienteResponse.from(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponse> updateCliente(
			@PathVariable UUID id,
			@RequestBody UpdateClienteRequest request) {
		Cliente cliente = this.updateClienteUseCase.execute(id, request).get();
		return ResponseEntity.ok(ClienteResponse.from(cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable UUID id) {
		boolean deleted = this.deleteClienteUseCase.execute(id);
		return ResponseEntity.ok(deleted);
	}
}
