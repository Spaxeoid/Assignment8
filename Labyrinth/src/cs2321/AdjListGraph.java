package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author 		Timothy Leach
 * @class		CS2321
 * @assignment 7
 */
public class AdjListGraph<V, E> implements Graph<V, E> {

	private boolean isDirected;
	private PositionalList<Vertex<V>> vertices = new DoublyLinkedList<>();
	private PositionalList<Edge<E>> edges = new DoublyLinkedList<>();


	public AdjListGraph(boolean directed) {
		isDirected = directed;
	}

	public AdjListGraph() {
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	@TimeComplexity("O(m)")
	/*
	 * TCJ
	 * This method returns an iterable for the edges in the graph
	 * that contains all of the edges, making the time complexity
	 * O(m) where m is the number of edges
	 */
	public Iterable<Edge<E>> edges() {
		return edges;
	}

	/* 
	 * returns a Vertex list of vertices on each end of the edge
	 */
	@TimeComplexity("O(1)")
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}


	/* 
	 * Inserts an edge with element, o, between two vertices, u and v
	 */
	@TimeComplexity("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		if(getEdge(u,v) == null) {
			InnerEdge<E> e = new InnerEdge<>(u, v, o);
			e.setPosition(edges.addLast(e));
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> dest = validate(v);
			origin.getOutgoing().put(v, e);
			dest.getIncoming().put(u, e);
			return e;
		}else {
			throw new IllegalArgumentException("edge from u to v exists");
		}
	}

	/* 
	 * Inserts a vertex, o
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		InnerVertex<V> v = new InnerVertex<>(o, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}

	/* 
	 * returns the number of edges in the graph
	 */
	@TimeComplexity("O(1)")
	public int numEdges() {
		return edges.size();
	}

	/* 
	 * returns the number of vertices in the graph 
	 */
	@TimeComplexity("O(1)")
	public int numVertices() {
		return vertices.size();
	}

	/* 
	 * returns the vertex that is on the opposite side of vertex, v, on edge, e
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if(endpoints[0] == v) {
			return endpoints[1];
		}else if(endpoints[1] == v) {
			return endpoints[0];
		}else{
			throw new IllegalArgumentException("v is not incident to this edge");
		}
	}

	/* 
	 * removes the edge, e, and all pointers that point to it
	 */
	@TimeComplexity("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] verts = edge.getEndpoints();
		validate(verts[0]).getOutgoing().remove(verts[1]);
		validate(verts[1]).getIncoming().remove(verts[0]);
		edges.remove(edge.getPosition());
		edge.setPosition(null);
	}

	/* 
	 * removes vertex, v, and all outgoing and incoming edges
	 */
	@TimeComplexity("O(deg(v)")
	/*
	 * TCJ:
	 * This method will loop through all of the edges connected to a vertex
	 * requiring a time of O(deg(V))
	 */
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		for(Edge<E> e : vert.getOutgoing().values()) {
			removeEdge(e);
		}
		for(Edge<E> e: vert.getIncoming().values()) {
			removeEdge(e);
		}
		vertices.remove(vert.getPosition());
	}

	/* 
	 * replaces the element in edge object, returns the old element
	 */
	@TimeComplexity("O(1)")
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		E old = edge.getElement();
		edge.setElement(o);
		return old;
	}

	/* 
	 * replaces the element in vertex object, returns the old element
	 */
	@TimeComplexity("O(1)")
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		V old = vert.getElement();
		vert.setElement(o);
		return old;
	}

	/* 
	 * returns the vertices iterable object
	 */
	@TimeComplexity("O(n)")
	/*
	 * TCJ
	 * This method returns an iterable for traversing through the vertices 
	 * that contains all of the vertices. This makes the time complexity
	 * O(n)
	 */
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}

	/*
	 * returns the number of outgoing edges of a vertex, v
	 */
	@Override
	@TimeComplexity("O(1)")
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().size();
	}


	/*
	 * returns the number of incoming edges of a vertex, v
	 */
	@Override
	@TimeComplexity("O(1)")
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().size();
	}

	/*
	 * Returns the outgoing edges of a vertex
	 */
	@Override
	@TimeComplexity("O(deg(v))")
	/*
	 * TCJ
	 * This method will go through and find all of the outgoing edges
	 * requiring this to search through all of the edges connected to
	 * the vertex v, this is known as the degree of v. Therefore
	 * the time complexity is O(deg(v))
	 */
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().values();
	}

	/*
	 * Returns the incoming edges of a vertex
	 */
	@Override
	@TimeComplexity("O(deg(v))")
	/*
	 * TCJ
	 * This method will go through and find all of the incoming edges
	 * requiring this to search through all of the edges connected to
	 * the vertex v, this is known as the degree of v. Therefore
	 * the time complexity is O(deg(v))
	 */
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().values();
	}

	/*
	 * Returns the edge between two given vertices
	 */
	@Override
	@TimeComplexity("O(1)")
	
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> origin = validate(u);
		return origin.getOutgoing().get(v);
	}

	/*
	 * Class for InnerVertex containing useful methods used throughout the program
	 */
	@SuppressWarnings("hiding")
	class InnerVertex<V> implements Vertex<V>{

		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		public InnerVertex(V elem, boolean graphIsDirected) {
			element = elem;
			outgoing = new HashMap<>();
			if(graphIsDirected) {
				incoming = new HashMap<>();
			}else {
				incoming = outgoing;
			}
		}

		@Override
		public V getElement() {
			return element;
		}

		public void setElement(V elem) {
			element = elem;
		}

		public void setPosition(Position<Vertex<V>> p) {
			pos = p;
		}

		public Position<Vertex<V>> getPosition(){
			return pos;
		}

		public Map<Vertex<V>, Edge<E>> getOutgoing(){
			return outgoing;
		}

		public Map<Vertex<V>, Edge<E>> getIncoming(){
			return incoming;
		}
	}

	/*
	 * Class for InnerEdge containing useful methods used throughout the program
	 */
	@SuppressWarnings("hiding")
	class InnerEdge<E> implements Edge<E>{
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;

		@SuppressWarnings("unchecked")
		public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[] {u,v};
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E e) {
			element = e;
		}

		public Vertex<V>[] getEndpoints(){
			return endpoints;
		}

		public void setPosition(Position<Edge<E>> p) {
			pos = p;
		}

		public Position<Edge<E>> getPosition(){
			return pos;
		}

	}

	/*
	 * Validates that a vertex, v, exists
	 */
	@TimeComplexity("O(1)")
	public InnerVertex<V> validate(Vertex<V> v) throws IllegalArgumentException{
		if(v.getElement() != null || (v instanceof InnerVertex)) {
			return (InnerVertex<V>) v;
		}else {
			throw new IllegalArgumentException("Invalid Vertex");
		}
	}

	/*
	 * Validates that a edge, e, exists
	 */
	@TimeComplexity("O(1)")
	public InnerEdge<E> validate(Edge<E> e) throws IllegalArgumentException{
		if(e.getElement() != null || (e instanceof InnerEdge)) {
			InnerEdge<E> temp = (InnerEdge<E>) e;
			return temp;
		}else {
			throw new IllegalArgumentException("Invalid Edge");
		}
	}

}


