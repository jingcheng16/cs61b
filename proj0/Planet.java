
public class Planet {
	/* Its current x position. */
	public double xxPos;
	/* Its current y position. */
	public double yyPos;
	/* Its current velocity in the x direction. */
	public double xxVel;
	/* Its current velocity in the y direction. */
	public double yyVel;
	/* Its mass. */
	public double mass;
	/* The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif) */
	public String imgFileName;
	/* The graviational constant */
	private static final double G = 6.67e-11;


	/* Constructor */
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/* Copy constructor */
	public Planet(Planet p){
        xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculate the distance between two Planets
	 *  @Param body the supplied planet
	 *  @Return double the distance between the supplied planet and the planet that is doing the calculation
	 */

	public double calcDistance(Planet body){
		double dx = body.xxPos - this.xxPos;
		double dy = body.yyPos - this.yyPos;
		return Math.sqrt(dx*dx+dy*dy);
	}

	/** Calculate the force exerted on this body by the given body
	 *  @Param body 
	 *  @Return double describing the force exerted on this body by the given body
	 */

	public double calcForceExertedBy(Planet body){
		double r = this.calcDistance(body);
		return (G * this.mass * body.mass)/(r*r);
	}

	public double calcForceExertedByX(Planet body){
		double F = this.calcForceExertedBy(body);
		double dx = body.xxPos - this.xxPos;
		return (F * dx)/this.calcDistance(body);
	}

	public double calcForceExertedByY(Planet body){
		double F = this.calcForceExertedBy(body);
		double dy = body.yyPos - this.yyPos;
		return (F * dy)/this.calcDistance(body);
	}

	public double calcNetForceExertedByX(Planet[] allBodys){
		double result = 0;
		for(Planet body: allBodys){
			if(this.equals(body)){
				continue;
			}else
			{
				result += this.calcForceExertedByX(body);
			}
		}
		return result;
	}

	public double calcNetForceExertedByY(Planet[] allBodys){
		double result = 0;
		for(Planet body: allBodys){
			if(this.equals(body)){
				continue;
			}else
			{
				result += this.calcForceExertedByY(body);
			}
		}
		return result;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	/** draw the picture of the planet */
	public void draw(){
		String path = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,path);
	}


}