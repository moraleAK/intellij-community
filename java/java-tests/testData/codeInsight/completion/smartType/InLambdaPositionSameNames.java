interface I<T> {
  void m(T t, String s);
}

class Test {
  public static void main(String[] args) {
    String s = "";
    I<String> i = <caret>
  }
}