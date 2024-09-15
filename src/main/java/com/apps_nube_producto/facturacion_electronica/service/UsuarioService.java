package com.apps_nube_producto.facturacion_electronica.service;

import com.apps_nube_producto.facturacion_electronica.Repository.UsuarioRepository;
import com.apps_nube_producto.facturacion_electronica.model.TipoDocumento;
import com.apps_nube_producto.facturacion_electronica.model.Usuario;
import com.apps_nube_producto.facturacion_electronica.model.dto.DniResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final RestTemplate restTemplate;
    private final UsuarioRepository usuarioRepository;
    private final static String API_URL = "https://dniruc.apisperu.com/api/v1/dni/";
    private final static String API_URL_RUC = "https://dniruc.apisperu.com/api/v1/ruc/";

//    public Usuario crearUsuarioSiDniValido(String dni) {
//        String apiUrl = API_URL + dni;
//
//        try {
//            ResponseEntity<DniResponse> response = restTemplate.getForEntity(apiUrl, DniResponse.class);
//
//            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//                Usuario usuario = new Usuario();
//                usuario.getTipoDocumento().setTipo("DNI");
//                usuario.getTipoDocumento().setValor(dni);
//
//                return usuarioRepository.save(usuario);
//            } else {
//                throw new IllegalArgumentException("DNI no encontrado o inválido.");
//            }
//        } catch (RestClientResponseException ex) {
//            throw new RuntimeException("Error al validar el DNI en la API externa: " + ex.getMessage());
//        }
//    }
//
//    public Usuario crearUsuarioSiRucValido(String ruc) {
//        String apiUrl = API_URL_RUC + ruc;
//
//        try {
//
//            ResponseEntity<DniResponse> response = restTemplate.getForEntity(apiUrl, DniResponse.class);
//
//            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//                Usuario usuario = new Usuario();
//                usuario.getTipoDocumento().setTipo("RUC");
//                usuario.getTipoDocumento().setValor(ruc);
//
//                return usuarioRepository.save(usuario);
//            } else {
//                throw new IllegalArgumentException("RUC no encontrado o inválido.");
//            }
//        } catch (RestClientResponseException ex) {
//            throw new RuntimeException("Error al validar el RUC en la API externa: " + ex.getMessage());
//        }
//    }

    private final String BEARER_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaGFubG9wZXpzcmVAZ21haWwuY29tIn0.q5v6g2drntuPO-ZGF30CSrsWZzZvt8TyC7AoBRgbDpE"; // Reemplaza esto con tu token

    public Usuario crearUsuarioSiDniValido(String dni) {
        String apiUrl = API_URL + dni;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<DniResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, DniResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Usuario usuario = Usuario.builder()
                        .tipoDocumento(new TipoDocumento()) // Asegúrate de inicializar TipoDocumento
                        .build();
                usuario.getTipoDocumento().setTipo("DNI");
                usuario.getTipoDocumento().setValor(dni);

                return usuarioRepository.save(usuario);
            } else {
                throw new IllegalArgumentException("DNI no encontrado o inválido.");
            }
        } catch (RestClientResponseException ex) {
            throw new RuntimeException("Error al validar el DNI en la API externa: " + ex.getMessage());
        }
    }

    public Usuario crearUsuarioSiRucValido(String ruc) {
        String apiUrl = API_URL_RUC + ruc;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<DniResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, DniResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Usuario usuario = new Usuario();
                usuario.getTipoDocumento().setTipo("RUC");
                usuario.getTipoDocumento().setValor(ruc);

                return usuarioRepository.save(usuario);
            } else {
                throw new IllegalArgumentException("RUC no encontrado o inválido.");
            }
        } catch (RestClientResponseException ex) {
            throw new RuntimeException("Error al validar el RUC en la API externa: " + ex.getMessage());
        }
    }

}
