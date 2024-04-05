package step.api;

public class ApiTest {
    public static void main(String[] args) {

        String result = OpenApi.get(1, 20);
        System.out.println(result);
    }
}
