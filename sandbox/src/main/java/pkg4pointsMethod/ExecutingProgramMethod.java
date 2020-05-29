package pkg4pointsMethod;

public class ExecutingProgramMethod {

    public static void main(String[] args){
        Point p1 = new Point(-3,1);
        Point p2 = new Point(0,-2);

        System.out.println("Расстрояние между точками (" + p1.x + ";" + p1.y + ") и (" + p2.x + ";" + p2.y + ") равно " + p1.distance(p2));

     }

}
