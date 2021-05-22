import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] paths1 = {{0,2,5}, {1,0,1},{2,3,2},{3,4,3},{4,0,4},{5,0,3},{5,4,3}};
		
		int[][] graph1 = generateDirectionalGraph(paths1,6); // directional graph
		
		int[][] friendRelations = {{0,2}, {1,0},{2,3},{3,4},{4,0},{5,0},{5,4}};
		Person[] people = {new Person("p1",13,'F'), new Person("p2",10,'M'),new Person("p3",23,'M'),new Person("p4",30,'F')
				,new Person("p5",43,'M'),new Person("p6",11,'M')};
		
		
		int[][] friends = generateNondirectionalGraph(friendRelations, 6); // nondirectional, no distance paths.
		
		ArrayList<Person> myFriends = friendFriendsByName("p3", people, friends);
		
		
		// above examples have space complexity O(n^2) 
		int[][]  treePaths = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,12},{3,6},{3,7},{3,8},{4,9},{5,10},{5,11}};
		int[][] tree1 = generateTree1(treePaths,13);
		int depth = getDepthDFS(tree1, 0);
		depth = getDepthBFS(tree1,0 );
		
		ArrayList<Integer> descendants = getAllDescendantsDFS(tree1, 0);
		descendants =  getAllDescendantsBFS(tree1, 0);
		
		
			

	}

	

	private static ArrayList<Integer> getAllDescendantsBFS(int[][] tree, int root) {
		// TODO Auto-generated method stub
		ArrayList<Integer> descendants =new ArrayList<>();
		
		ArrayList<Integer> current = new ArrayList<Integer>();
		current.add(root);
		ArrayList<Integer> next =new ArrayList<Integer>();
		
		while (!current.isEmpty()) {
			for (int node:current) {
				ArrayList<Integer> children = getChildren(tree, node);
				descendants.addAll(children);
				next.addAll(children);
			}
			current = next;
			next = new ArrayList<>();
		}
			
		return descendants;
	}



	private static ArrayList<Integer> getAllDescendantsDFS(int[][] tree, int root) {
		// TODO Auto-generated method stub
		ArrayList<Integer> descendants = new ArrayList<>();
		
		ArrayList<Integer> children = getChildren(tree,root);
		if (children.isEmpty()) return descendants;
		
		for (int child:children) {
			descendants.addAll(getAllDescendantsDFS(tree, child));
		}
		
		return descendants;
	}



	private static int getDepthBFS(int[][] tree, int root) {
		// TODO Auto-generated method stub
		ArrayList<Integer> current = new ArrayList<>();
		current.add(root);
		ArrayList<Integer> next = new ArrayList<>();
		int depth=0;
		while (!current.isEmpty()) {
			for (int node:current) {
				ArrayList<Integer> children = getChildren(tree, node);
				next.addAll(children);
			}
			depth++;
			current = next;
			next=new ArrayList<>();
		}
		
		return depth-1;
	}



	private static int getDepthDFS(int[][] tree, int root) {
		// TODO Auto-generated method stub

		ArrayList<Integer> children = getChildren(tree, root);
		if (children.size() == 0) return 0;
		int subDepth = 0;
		
		for (int child:children) {
			int tempDepth = getDepthDFS(tree,child);
			if (subDepth<tempDepth)
				subDepth=tempDepth;
		}
		
		return subDepth+1;
	}



	private static ArrayList<Integer> getChildren(int[][] tree, int root) {
		// TODO Auto-generated method stub
		ArrayList<Integer> children=new ArrayList<>();
		for (int i=0;i<tree[root].length;i++) {
			if (tree[root][i]==1) {
				children.add(i);
			}
		}
		return children;
	}



	private static int[][] generateTree1(int[][] treePaths, int nodeNum) {
		// TODO Auto-generated method stub
		int[][] tree=new int[nodeNum][nodeNum];
		for (int i=0;i<nodeNum;i++) {
			for (int j=0;j<nodeNum;j++) {
				tree[i][j]=0;
			}
		}
		
		for (int[] path: treePaths) {
			tree[path[0]][path[1]] = 1;
		}
		
		return tree;
	}



	private static ArrayList<Person> friendFriendsByName(String name, Person[] people, int[][] friends) {
		// TODO Auto-generated method stub
		ArrayList<Person> myFriends =new ArrayList<>();
		int index=0;
		for (index=0;index<people.length;index++) {
			if (people[index].name.equals(name))
				break;
		}
		
		for (int i=0;i<friends[index].length;i++) {
			if (friends[index][i]==1) {
				Person person = people[i];
				myFriends.add(person);
			}
		}
		
		return myFriends;
	}



	private static int[][] generateNondirectionalGraph(int[][] paths, int nodeNum) {
		// TODO Auto-generated method stub
		int[][] graph = new int[nodeNum][nodeNum];
		for (int i=0;i<nodeNum;i++) {
			for (int j=0;j<nodeNum;j++) {
				graph[i][j]=0;
			}
		}
		
		for (int[] path:paths) {
			graph[path[0]][path[1]]=1;
			graph[path[1]][path[0]]=1;
		}
		
		return graph;
		
	}

	private static int[][] generateDirectionalGraph(int[][] paths, int nodeNum) {
		// TODO Auto-generated method stub
		int[][] graph = new int[nodeNum][nodeNum];
		for (int i=0;i<nodeNum;i++) {
			for (int j=0;j<nodeNum;j++) {
				graph[i][j]=0;
			}
		}
		
		for (int[] path:paths) {
			graph[path[0]][path[1]]=path[2];
		}
		
		return graph;
	}

}

class Person {
	String name;
	int age;
	char gender;
	
	public Person(String name, int age, char gender) {
		this.name = name;
		this.age = age;
		this.gender =gender;
	}
}
