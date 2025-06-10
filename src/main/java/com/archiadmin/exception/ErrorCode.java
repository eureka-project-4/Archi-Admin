package com.archiadmin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C500", "서버 내부 오류가 발생했습니다"),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "U401", "아이디 또는 비밀번호가 일치하지 않습니다"),

  ADMIN_ALREADY_EXISTS(HttpStatus.CONFLICT, "U409", "이미 존재하는 관리자입니다."),

  ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND, "U404", "관리자를 찾을 수 없습니다."),

  RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "D404", "해당 리소스를 찾을 수 없습니다"),

  RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "D409", "이미 존재하는 리소스입니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;


  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}