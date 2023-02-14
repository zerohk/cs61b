/**
 * TestBody
 */
public class TestBody {

    public static void main(String[] args) {
        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "img.jpg");
        Planet p2 = new Planet(3.0, -5.0, 2.0, 3.0, 10.0, "img2.jpg");
        System.out.println(p1.calcForceExertedBy(p2));
    }
}