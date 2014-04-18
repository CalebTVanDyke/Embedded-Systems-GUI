package GUI;


public class SpaceObject  {
	
	protected int size;
	protected int coords[];

	public SpaceObject(int x, int y, int size) {
				
		coords = new int[2];
		coords[0] = x;
		coords[1] = y;
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public int getX(){
		return coords[0];
	}
	public int getY(){
		return coords[1];
	}
}
