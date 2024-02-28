package com.picpay_challenge.picpay_challenge.domain.exceptions;

public class UserAlredyExistException extends RuntimeException {
  public UserAlredyExistException() {
    super("User alreay exist");
  }

}
