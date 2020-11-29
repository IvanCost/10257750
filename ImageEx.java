// Esqueleto da classe na qual devem ser implementadas as novas funcionalidades de desenho

public class ImageEx extends Image {

	public ImageEx(int w, int h, int r, int g, int b) {

		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h) {

		super(w, h);
	}

	public void kochCurve(Image image, int l, double px, double py, double qx, double qy) {

		double cos60, sen60;
		long c = getComprimento(px, py, qx, qy);

		cos60 = Math.cos(3.1415927 / 3.);
		sen60 = Math.sin(3.1415927 / 3.);

		if (l < c)
			image.drawLine((int) px, (int) py, (int) qx, (int) qy);
		else {
			l = l - 1;
			double Ax = px + (qx - px) / 3.;
			double Ay = py + (qy - py) / 3.;
			double Cx = qx - (qx - px) / 3.;
			double Cy = qy - (qy - py) / 3.;
			double Bx = Ax + cos60 * (Cx - Ax) + sen60 * (Cy - Ay);
			double By = Ay - sen60 * (Cx - Ax) + cos60 * (Cy - Ay);

			kochCurve(image, l, px, py, Ax, Ay);
			kochCurve(image, l, Ax, Ay, Bx, By);
			kochCurve(image, l, Bx, By, Cx, Cy);
			kochCurve(image, l, Cx, Cy, qx, qy);
		}

	}

	public void regionFill(int x, int y, int reference_rgb) {

		if (!(reference_rgb == getPixel(x, y))) {
			return;
		}

		setPixel(x, y);
		if (x + 1 < getWidth())
			regionFill(x + 1, y, reference_rgb);
		if (y + 1 < getHeight())
			regionFill(x, y + 1, reference_rgb);
		if (x - 1 >= 0)
			regionFill(x - 1, y, reference_rgb);
		if (y - 1 >= 0)
			regionFill(x, y - 1, reference_rgb);

	}

	private static long getComprimento(double px, double py, double qx, double qy) {
		double c = Math.sqrt(squared(qx - px) + squared(qy - py));
		return Math.round(c);
	}

	private static double squared(double coefficient) {
		return Math.pow(coefficient, 2);
	}

}
