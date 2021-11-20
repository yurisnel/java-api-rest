package bz.nimitz.ybr.demo;

public class ObjectNotFoundException extends RuntimeException {

  public ObjectNotFoundException(Long id) {
    super("Object not find " + id);
  }
}