package com.api.protecaoanimal.enuns;

public enum StatusEnum {

  OK(200, "OK"),
  CREATED(201, "Criado(a)"),
  ACCEPTED(202, "Aceito"),
  NO_CONTENT(204, "Sem Conteúdo"),
  RESET_CONTENT(205, "Redefinir Conteúdo"),
  PARTIAL_CONTENT(206, "Conteúdo Parcial"),
  MOVED_PERMANENTLY(301, "Movido Permanentemente"),
  FOUND(302, "Encontrado(a)"),
  SEE_OTHER(303, "Ver Outro(a)"),
  NOT_MODIFIED(304, "Não Modificado(a)"),
  USE_PROXY(305, "Use Proxy"),
  TEMPORARY_REDIRECT(307, "Redirecionamento Temporário"),
  BAD_REQUEST(400, "Redido Ruim"),
  UNAUTHORIZED(401, "Não Autorizado(a)"),
  PAYMENT_REQUIRED(402, "Pagamento Requerido"),
  FORBIDDEN(403, "Proibido"),
  NOT_FOUND(404, "Não Encontrado(a)"),
  METHOD_NOT_ALLOWED(405, "Método Não Permitido"),
  NOT_ACCEPTABLE(406, "Não Aceitável"),
  PROXY_AUTHENTICATION_REQUIRED(407, "Autenticação de Proxy Necessária"),
  REQUEST_TIMEOUT(408, "Solicitar Tempo Limite"),
  CONFLICT(409, "Conflito"),
  GONE(410, "Indo"),
  LENGTH_REQUIRED(411, "Comprimento Necessário"),
  PRECONDITION_FAILED(412, "Falha na Pré-condição"),
  REQUEST_ENTITY_TOO_LARGE(413, "Solicitar Entidade Muito Grande"),
  REQUEST_URI_TOO_LONG(414, "URI de Solicitação muito Longo"),
  UNSUPPORTED_MEDIA_TYPE(415, "Tipo de Mídia não Suportado"),
  REQUESTED_RANGE_NOT_SATISFIABLE(416, "Intervalo Solicitado não Satisfatório"),
  EXPECTATION_FAILED(417, "Falha na Expectativa"),
  PRECONDITION_REQUIRED(428, "Pré-condição Necessária"),
  TOO_MANY_REQUESTS(429, "Muitos Pedidos"),
  REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Campos de cabeçalho de solicitação muito grandes"),
  INTERNAL_SERVER_ERROR(500, "Erro interno do servidor"),
  NOT_IMPLEMENTED(501, "Não implementado"),
  BAD_GATEWAY(502, "Gateway inválido"),
  SERVICE_UNAVAILABLE(503, "Serviço indisponível"),
  GATEWAY_TIMEOUT(504, "Tempo limite do gateway"),
  HTTP_VERSION_NOT_SUPPORTED(505, "Versão HTTP não suportada"),
  NETWORK_AUTHENTICATION_REQUIRED(511, "Autenticação de rede necessária");

  private int code;
  private String status;

  StatusEnum(int code, String status) {
    this.code = code;
    this.status = status;
  }
  
  public int getCode() {
    return code;
  }

  public String getStatus() {
    return status;
  }

  public static StatusEnum fromString(String value) {
    for (StatusEnum tipo : StatusEnum.values()) {
      if (tipo.toString().equalsIgnoreCase(value)) {
        return tipo;
      }
    }
    return null;
  }
    
}
