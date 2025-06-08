package com.archiadmin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C500", "서버 내부 오류가 발생했습니다"),

  USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "U409", "이미 존재하는 회원입니다."),

  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U404", "사용자를 찾을 수 없습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;


  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}