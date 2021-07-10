public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		in.readDouble();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int N = in.readInt();
		Planet[] bodies= new Planet[N];
		in.readDouble();
		for(int i = 0; i < N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			bodies[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return bodies;	
	}



	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.enableDoubleBuffering();
		String background = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,background);
		for(Planet body: planets){
			body.draw();
		}
		double count = 0;
		int N = planets.length;
		while(count <= T){
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			for(int i = 0 ; i < N; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.clear();
		    StdDraw.picture(0,0,background);
		    for(int i = 0 ; i < N; i++){
		    	planets[i].update(dt,xForces[i],yForces[i]);
			    planets[i].draw();
		    }
		    StdDraw.show();
		    StdDraw.pause(10);
			count += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
	}
}