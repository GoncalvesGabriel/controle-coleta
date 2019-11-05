package br.com.fiap.controlecoleta.validator;

public interface Validator<T> {

  default void validateInsert(T entity){
    // não faz nada
  }

  default void validateDelete(T entity) {
    // não faz nada
  }

}
