package com.apps_nube_producto.facturacion_electronica.service;

import com.apps_nube_producto.facturacion_electronica.Repository.DocumentoRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.UsuarioRepository;
import com.apps_nube_producto.facturacion_electronica.dto.DniResponse;
import com.apps_nube_producto.facturacion_electronica.exception.DocumentoInvalido;
import com.apps_nube_producto.facturacion_electronica.model.Documento;
import com.apps_nube_producto.facturacion_electronica.model.Usuario;
import com.apps_nube_producto.facturacion_electronica.model.enums.TipoDocumento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final RestTemplate restTemplate;
    private final UsuarioRepository usuarioRepository;

    @Value("${api.url.dni}")
    private String API_URL_DNI;

    @Value("${api.url.ruc}")
    private String API_URL_RUC;

    @Value("${api.bearer.token}")
    private String BEARER_TOKEN;

    private final DocumentoRepository documentoRepository;

    public Usuario crearUsuarioSiDocumentoValido(String numDoc) {
        String apiUrl = obtenerApiUrl(numDoc);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<DniResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, DniResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().getSuccess().equals("true"))    {
                Usuario usuario = crearUsuarioConDocumento(numDoc);
                return usuario;
            } else {
                throw new DocumentoInvalido();
            }
        } catch (RestClientResponseException ex) {
            throw new RuntimeException("Error al validar la API externa: " + ex.getMessage());
        }
    }


    private String obtenerApiUrl(String numDoc) {
        String apiUrlDni = API_URL_DNI + numDoc;
        String apiUrlRuc = API_URL_RUC + numDoc;
        String apiUrl = "";

        if (numDoc.length() == 8) {
            apiUrl = apiUrlDni;
        } else if (numDoc.length() == 11) {
            apiUrl = apiUrlRuc;
        }
        return apiUrl;
    }

    private Usuario crearUsuarioConDocumento(String numDoc) {
        Usuario usuario = usuarioRepository.findByTipoDocumento_Valor(numDoc);

        if (usuario == null) {
            TipoDocumento tipo = numDoc.length() == 8 ? TipoDocumento.DNI : TipoDocumento.RUC;
            Documento documento = documentoRepository.findByValor(numDoc)
                    .orElse(new Documento(tipo, numDoc));

            usuario = Usuario.builder()
                    .tipoDocumento(documento)
                    .build();

            documentoRepository.save(documento);
            return usuarioRepository.save(usuario);
        } else {
            return usuario;
        }
    }




}
