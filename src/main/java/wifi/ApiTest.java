package wifi;

public class ApiTest {
    public static void main(String[] args) {

        String result = OpenApi.get(1, 10);
        System.out.println(result);
    }
}
