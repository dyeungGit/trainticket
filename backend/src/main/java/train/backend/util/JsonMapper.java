package train.backend.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper {

  private static final ObjectMapper objectMapper = createObjectMapper();

  public static ObjectMapper getInstance() {
    return objectMapper;
  }

  private static ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();

    // Deserialization features
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    // Serialization features
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    // Inclusion strategy
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // Modules
    mapper.registerModule(new JavaTimeModule());
    mapper.registerModule(new ParameterNamesModule());

    // Filter provider (if needed)
    mapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));

    return mapper;
  }

  public static <T> String obj2String(T src) {
    if (src == null) {
      return null;
    }
    try {
      return src instanceof String text ? text : objectMapper.writeValueAsString(src);
    } catch (Exception e) {
      log.warn("parse object to String exception, error:{}", e);
      return null;
    }
  }

  public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
    if (src == null || typeReference == null) {
      return null;
    }
    try {
      return (T)
          (typeReference.getType().equals(String.class)
              ? src
              : objectMapper.readValue(src, typeReference));
    } catch (Exception e) {
      log.warn(
          "parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}",
          src,
          typeReference.getType(),
          e);
      return null;
    }
  }
}
