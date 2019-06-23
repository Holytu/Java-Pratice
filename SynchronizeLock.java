import java.applet.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;

class CubbyHole {
	private int contents;
	private boolean available = false;
	
	public synchronized int get()
	{
		while(available == false)
		{
			try
			{
				wait();
			} catch(Exception e) {}
		}
		available = false;
		notifyAll();
		
		return contents;
	}
	
	public synchronized void put(int value)
	{
		while(available == true)
		{
			try
			{
				wait();
			} catch(Exception e) {}
		}
		contents = value;
		available = true;
		
		notifyAll();
	}
}

class Producer extends Thread {
	private CubbyHole cubbyhole;
	private int number;
	
	public Producer(CubbyHole c, int number)
	{
		cubbyhole = c;
		this.number = number;
	}
	
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			cubbyhole.put(i);
			System.out.println("Producer #"+this.number+" got : "+i);
			
			try
			{
				sleep((int) Math.random() * 100 );
			}catch(Exception e) {}
		}
	}
}

class Consumer extends Thread {
	private CubbyHole cubbyhole;
	private int number;
	
	public Consumer(CubbyHole c, int number)
	{
		cubbyhole = c;
		this.number = number;
	}
	
	public void run()
	{
		int value = 0;
		for (int i = 0; i < 10; i++)
		{
			value = cubbyhole.get();
			System.out.println("Consumer #"+this.number+" got : "+value);
		}
	}
}
public class Hello  {
	public static void main(String[] args) {
		CubbyHole c = new CubbyHole();
		Producer p1 = new Producer(c, 1);
		Consumer c1 = new Consumer(c, 1);
		
		p1.start();
		c1.start();
	}
	
}

