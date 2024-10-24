package train.backend.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.collections.MapUtils;
import train.backend.exception.ParamException;

public class BeanValidator {

  private static final Validator validator;
  private static final ValidatorFactory factory;

  static {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    // Ensure the factory is closed when the JVM shuts down
    Runtime.getRuntime().addShutdownHook(new Thread(factory::close));
  }

  /**
   * Validates an object and returns a map of validation errors.
   *
   * @param <T> The type of the object to validate
   * @param object The object to validate
   * @param groups The validation groups to apply (optional)
   * @return A map of field paths to error messages, or an empty map if validation passes
   * @throws IllegalArgumentException if the object is null
   */
  public static <T> Map<String, String> validate(T object, Class<?>... groups) {
    if (object == null) {
      throw new IllegalArgumentException("Object to validate cannot be null");
    }

    Set<ConstraintViolation<T>> violations = validator.validate(object, groups);

    return violations.isEmpty()
        ? Collections.emptyMap()
        : violations.stream()
            .collect(
                Collectors.toMap(
                    violation -> violation.getPropertyPath().toString(),
                    ConstraintViolation::getMessage,
                    (msg1, msg2) -> msg1 + "; " + msg2, // In case of duplicate keys
                    LinkedHashMap::new));
  }

  // Prevent instantiation
  private BeanValidator() {
    throw new AssertionError("Utility class should not be instantiated");
  }

  public static Map<String, String> validateList(Collection<?> collection) {
    Preconditions.checkNotNull(collection);
    Iterator iterator = collection.iterator();
    Map errors;

    do {
      if (!iterator.hasNext()) {
        return Collections.emptyMap();
      }
      Object object = iterator.next();
      errors = validate(object);
    } while (errors.isEmpty());

    return errors;
  }

  public static Map<String, String> validateObject(Object first, Object... objects) {
    if (objects != null && objects.length > 0) {
      return validateList(Lists.asList(first, objects));
    } else {
      return validate(first);
    }
  }

  public static void check(Object param) throws ParamException {
    Map<String, String> map = BeanValidator.validateObject(param);
    if (MapUtils.isNotEmpty(map)) {
      throw new ParamException(map.toString());
    }
  }
}
