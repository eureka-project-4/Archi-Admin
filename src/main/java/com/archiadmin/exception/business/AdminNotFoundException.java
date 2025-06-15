package com.archiadmin.exception.business;

import com.archiadmin.exception.BusinessException;
import com.archiadmin.exception.ErrorCode;

public class AdminNotFoundException extends BusinessException {
  public AdminNotFoundException() {
    super(ErrorCode.ADMIN_NOT_FOUND);
  }

  public AdminNotFoundException(String message) {
    super(ErrorCode.ADMIN_NOT_FOUND, message);
  }
}