package com.archiadmin.exception.business;

import com.archiadmin.exception.BusinessException;
import com.archiadmin.exception.ErrorCode;

public class DuplicateResourceException extends BusinessException {
  public DuplicateResourceException() {
    super(ErrorCode.RESOURCE_ALREADY_EXISTS);
  }

  public DuplicateResourceException(String message) {
    super(ErrorCode.RESOURCE_ALREADY_EXISTS, message);
  }
}