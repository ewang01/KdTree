package changsi.test;

import java.util.ArrayList;
import java.util.Arrays;

public class KdTree {

	public static double xmedian(ArrayList<KdNode> arl) {
		double[] arr;
		int l;
		int size = arl.size();
		arr = new double[size];
		for (l = 0; l < size; l++)
			arr[l] = arl.get(l).x;

		Arrays.sort(arr);

		if (size % 2 == 1)
			return arr[(size - 1) / 2];
		else
			return arr[size / 2];
	}

	public static double ymedian(ArrayList<KdNode> arl) {
		double[] arr;
		int l;
		int size = arl.size();
		arr = new double[size];
		for (l = 0; l < size; l++)
			arr[l] = arl.get(l).y;

		Arrays.sort(arr);

		if (size % 2 == 1)
			return arr[(size - 1) / 2];
		else
			return arr[size / 2];
	}

	private KdNode root;

	public KdTree() {
		root = null;
	}

	public synchronized void build(ArrayList<KdNode> al) {
		root = build(al, root, 0);
	}

	private KdNode build(ArrayList<KdNode> al, KdNode t, int depth) {
		ArrayList<KdNode> al1 = new ArrayList<KdNode>();
		ArrayList<KdNode> al2 = new ArrayList<KdNode>();

		if (al.size() == 1)
			t = al.get(0);
		else {
			if (depth % 2 == 0) {
				for (int k = 0; k < al.size(); k++) {
					double xm = xmedian(al);
					if (al.get(k).x < xm)
						al1.add(al.get(k));
					else if (al.get(k).x > xm)
						al2.add(al.get(k));
					else
						t = al.get(k);
				}
			} else {
				for (int k = 0; k < al.size(); k++) {
					double ym = ymedian(al);
					if (al.get(k).y < ym)
						al1.add(al.get(k));
					else if (al.get(k).y > ym)
						al2.add(al.get(k));
					else
						t = al.get(k);
				}
				// System.out.println(al1);
				// System.out.println(al2);
			}
			if (al1.size() != 0)
				t.left = build(al1, t.left, depth + 1);
			if (al2.size() != 0)
				t.right = build(al2, t.right, depth + 1);
		}
		return t;
	}

	public synchronized void insert(KdNode node) {
		root = insert(node, root, 0);
	}

	private KdNode insert(KdNode node, KdNode root, int level) {
		if (root == null) {
			return node;
		}

		if (level == 0) { // x level
			if (node.x < root.x) {
				root.left = insert(node, root.left, 1 - level);
			} else if (node.x > root.x) {
				root.right = insert(node, root.right, 1 - level);
			} else {
				return root;
			}
		} else { // y level
			if (node.y < root.y) {
				root.left = insert(node, root.left, 1 - level);
			} else if (node.y > root.y) {
				root.right = insert(node, root.right, 1 - level);
			} else {
				return root;
			}
		}
		return root;

	}

	public synchronized ArrayList<KdNode> rangeQuery(Double[] low, Double[] high) {
		ArrayList<KdNode> output = new ArrayList<KdNode>();
		rangeQuery(low, high, root, 0, output);
		return output;
	}

	private void rangeQuery(Double[] low, Double[] high, KdNode t, int level,
			ArrayList<KdNode> output) {
		if (t != null) {
			if (low[0].compareTo(t.x) <= 0 && low[1].compareTo(t.y) <= 0
					&& high[0].compareTo(t.x) >= 0
					&& high[1].compareTo(t.y) >= 0)
				output.add(t);

			if ((level == 0 && low[level].compareTo(t.x) <= 0)
					|| (level == 1 && low[level].compareTo(t.y) <= 0))
				rangeQuery(low, high, t.left, 1 - level, output);
			// else
			if ((level == 0 && high[level].compareTo(t.x) >= 0)
					|| (level == 1 && high[level].compareTo(t.y) >= 0)) {// System.out.println(t.left.id);
				rangeQuery(low, high, t.right, 1 - level, output);
			}

		}

	}

	public static void main(String[] args) {

	}
}
