package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Hello!");

		hello("Anja");
		hello("username");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		r.a = 4;
		r.b = 6;
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}
}