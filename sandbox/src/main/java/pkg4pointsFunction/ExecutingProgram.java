package pkg4pointsFunction;

public class ExecutingProgram {

    public static void main(String[] args){
        Point p1 = new Point();
        Point p2 = new Point();

        p1.x = 1;
        p1.y = 1;
        p2.x = 3;
        p2.y = 3;

        System.out.println("Расстрояние между точками (" + p1.x + ";" + p1.y + ") и (" + p2.x + ";" + p2.y + ") равно " + distance(p1,p2));

     }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }
}
