package at.htl.runner;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * Source:
 * https://stackoverflow.com/a/50721326
 */
public class InOutFakerTest {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;

  @BeforeEach
  public void setupOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String getOutput() {
    return testOut.toString();
  }

  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void testInAndOutInMainMethod() {
    final String testString = "Hello!";
    provideInput(testString);

    InOutFaker.main(null);
    assertThat(testString).isEqualTo(getOutput());
  }

  @Test
  public void testSystemIn() {
    final String keyboardString = "Susi";
    provideInput(keyboardString);

    InOutFaker instance = new InOutFaker();
    String result = instance.in();
    assertThat(result).isEqualTo(keyboardString);
  }

  @Test
  public void testSystemOut() {
    InOutFaker instance = new InOutFaker();

    instance.out();
    String printOnConsoleResult = getOutput();
    assertThat(printOnConsoleResult).isEqualTo("Hello World!\n");  // \n wegen println
  }

}
