package com.archiadmin.exception.business;

import com.archiadmin.exception.BusinessException;
import com.archiadmin.exception.ErrorCode;

public class DataNotFoundException extends BusinessException {
  public DataNotFoundException() {
    super(ErrorCode.RESOURCE_NOT_FOUND);
  }

  public DataNotFoundException(String message) {
    super(ErrorCode.RESOURCE_NOT_FOUND, message);
  }
}