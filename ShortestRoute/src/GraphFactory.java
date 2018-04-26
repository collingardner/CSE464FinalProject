
public class GraphFactory {

	public static Graph make_graph(int n, boolean list_graph) {
		if (list_graph)
			return new ListGraph(n);
		return new MatrixGraph(n);
	}

}
