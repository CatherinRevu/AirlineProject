import java.sql.Connection;
import java.sql.DriverManager;
public class FlowerTest {
	public static void main(String[] args) {
		//hint will decide which implementation object would be produced
		Flower f1 = Garden.getFlower("decoration"); //coupling and cohesion- 
		Flower f2 = Garden.getFlower("diwali"); //coupling and cohesion-
		Flower f3 = Garden.getFlower("valentine"); //coupling and cohesion-
			
		Fragrance frag1 = f1.flowering(); //RoseFragrance is hidden
		Fragrance frag2 = f2.flowering(); //LotusFragrance is hidden
		Fragrance frag3 = f3.flowering(); //LilyFragrance is hidden
		
		frag1.pleasantSmell();
		frag2.pleasantSmell();
		frag3.pleasantSmell();
	}
}
// the question is -> from where do we get the flower |  either from the flower shop or from the garden
class Garden {
	static Flower getFlower(String hint) {
		Flower f = null; // make a generic handle
		if(hint.equalsIgnoreCase("valentine"))			f = new Rose("Red");
		else if(hint.equalsIgnoreCase("diwali"))		f = new Lotus("Pink");
		else if(hint.equalsIgnoreCase("decoration"))	f = new Lily("Magenta");
		return f;
	}
	static Flower getFlower() {
		Rose   r = new Rose("Red");
		return r;
	}
}
interface Flower {
	//below method would be a mandate on each class implementation
	Fragrance flowering(); // by default abstract and public
			//flowering() is producting Fragrance
}

interface Fragrance
{
	void pleasantSmell(); //public and abstract
}
class RoseFragrance implements Fragrance
{
	public void pleasantSmell() {
		System.out.println("Rose Fragrance smell of musk or cloves...breath it deeply and slowly...");
	}
}
class LotusFragrance implements Fragrance
{
	public void pleasantSmell() {
		System.out.println("Lotus Fragrance smell is fruity or sweet......");
	}
}
class LilyFragrance implements Fragrance
{
	public void pleasantSmell() {
		System.out.println("Lily Fragrance smell is subtle ......");
	}
}

class Rose implements Flower { // isA
	String color;  Rose(String color) {	super();	this.color = color;	}
	//Cannot reduce the visibility of the inherited method from Flower
	//must make if public 
	public Fragrance flowering() {// this is mandatory implementation
		System.out.println(color+" color Rose is blooming...");
		Fragrance fr = new RoseFragrance();
		return fr;
	}
}

class Lotus implements Flower { // isA
	String color;  Lotus(String color) {	super();	this.color = color;	}
	//Cannot reduce the visibility of the inherited method from Flower
	//must make if public 
	public Fragrance flowering() {// this is mandatory implementation
		System.out.println(color+" color Lotus is blooming...");
		Fragrance fr = new LotusFragrance();
		return fr;
	}
}
class Lily implements Flower { // isA
	String color;  Lily(String color) {	super();	this.color = color;	}
	//Cannot reduce the visibility of the inherited method from Flower
	//must make if public 
	public Fragrance flowering() {// this is mandatory implementation
		System.out.println(color+" color Lily is blooming...");
		Fragrance fr = new LilyFragrance();
		return fr;
	}
}