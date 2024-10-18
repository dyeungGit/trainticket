package train.backend.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import train.backend.exception.BusinessException;
import train.backend.exception.ParamException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String SYSTEM_ERROR_PLEASE_TRY_AGAIN = "System error, please try again";
  private static final String SYSTEM_ERROR_PLEASE_CONTACT_CUSTOMER_SERVICE =
      "System error, please contact customer service";

  @ExceptionHandler(value = RuntimeException.class)
  public JsonData exceptionHandler(RuntimeException ex) {
    if (ex instanceof ParamException || ex instanceof BusinessException) {
      return JsonData.fail(ex.getMessage());
    }
    log.error("unknown exception", ex);
    return JsonData.fail(SYSTEM_ERROR_PLEASE_TRY_AGAIN);
  }

  @ExceptionHandler(value = Error.class)
  public JsonData errorHandler(Error error) {
    log.error("unknown exception", error);
    return JsonData.fail(SYSTEM_ERROR_PLEASE_CONTACT_CUSTOMER_SERVICE);
  }
}
