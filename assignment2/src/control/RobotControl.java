package control;

import java.util.Arrays;

import robot.Robot;

//Robot Assignment for Programming 1
public class RobotControl implements Control {
	// we need to internally track where the arm is
	private int height = Control.INITIAL_HEIGHT;
	private int width = Control.INITIAL_WIDTH;
	private int depth = Control.INITIAL_DEPTH;

	private int[] barHeights;
	private int[] blockHeights;
	// no need to write any constructor, the robot is initialised
	private Robot robot; // by calling the init method as shown on line 33 below.

	// called by RobotImpl
	@Override
	public void control(Robot robot, int[] barHeightsDefault, int[] blockHeightsDefault) {
		this.robot = robot;

		// some hard coded init values you can change these for testing
		this.barHeights = new int[] {  };
		this.blockHeights = new int[] { 1 ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,};

		robot.init(this.barHeights, this.blockHeights, height, width, depth);

		start();

	}

	private void start() {

		int arr[][] = new int[10][];
		for (int x = 0; x < 10; x++) {
			if (x == 0 || x == 9) {
				arr[x] = new int[0];
			} else {
				arr[x] = new int[initialArraySize(x + 1)];
			}
		}
		int y = 1;
		for (int x = 0; x < this.barHeights.length; x++) {

			arr[y][0] = this.barHeights[x];
			y++;
		}

		int x = 1;
		int counter = 0;
		int blockNo = 0;

		for (int i = 0; i < this.blockHeights.length; i++) {

			if (blockNo < this.blockHeights.length) {
				if (x > 0 && x < 9) {
					arr[x][placeInArray(x, counter)] = this.blockHeights[i];

					counter++;
				}
			}

			if (counter < 7 && i < 8) {
				x++;
			}

			else if (counter == 8) {
				continue;
			} else if (counter >= 8 && counter < 16) {
				x--;
			} else if (i == 15) {
				continue;
			}

			else {
				x++;
			}

			if (counter == 16) {
				counter = 0;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}

		int maxHeight = maxHeight(arr);
		boolean pickUP = false;
		rearrange(arr, maxHeight, pickUP);

	}

	private int initialArraySize(int i) {
		int v1 = i - 1;
		int v2 = 18 - i;
		int v3 = 15 + i;
		try {
		if (Integer.toString(this.blockHeights[i - 2]) != null) {
			if (this.blockHeights.length >= v1 && this.blockHeights.length < v2) {
				return 2;
			} else if (this.blockHeights.length >= v2 && this.blockHeights.length < v3) {
				return 3;
			} else if (this.blockHeights.length >= v3) {
				return 4;
			}

		}
		}catch(ArrayIndexOutOfBoundsException e){
		return 0;
	}return 0;
	}

	private int placeInArray(int rowNum, int counter) {
		int v1 = (rowNum + 1);

		int v2 = (counter + rowNum);
		int v3 = (counter - rowNum);
		int x = 0;
		if (v1 == rowNum + 1) {
			x = 1;
		}
		if (v2 == 16) {
			x = 2;
		}
		if (v3 == 15) {
			x = 3;
		}
//		System.out.println("RowNum: " + rowNum + "| Counter: " + counter);
//		System.out.println("x: " + x + "\n");
//		System.out.println("v1: " + v1 + "| v2: " + v2 + "| v3: " + v3 + "\n\n");

		return x;

	}

	private void rearrange(int[][] arr, int maxHeight, boolean pickUP) {
		boolean right = true;
		int blockSize = 0;
		int counter = 0;
		int noMoved = 0;
		int target = 0;
		this.depth = 1;
		System.out.println("\n\nstart\n\n");
		while(noMoved<this.blockHeights.length){
			
			
			if(right) {
			System.out.print("\nStart Right\n-----\n");
			//Find the block to pick up
			 target = findNextBlockRight(arr);
			System.out.println("Target Col: "+target);
			//move to that block
			while(this.width<target) {
				while(pathBlockAcross(arr, right)) {
					upArm1();
				}
				extendArm2();
			}
			
			//move down to positing to pick up the block
				//CHECK WHICH colHeight NEEDS TO BE USED
			System.out.println("Arm1 Height : "+this.height);
			System.out.println("arm3 Depth: "+this.depth);
			System.out.println("Col "+target+" Height: "+(colHeightBelow(arr)));
			while(this.height-this.depth>colHeightBelow(arr)) {
				if(pathBlockedDown(arr)) {
					while(this.height-this.depth>colHeightBelow(arr)) {
						downArm1();
					}
				}else {
					lowerArm3();
						counter++;
				}
			}
			//pickup the block and update relevant info
			
			robot.pick();
			blockSize = getBlockSize(arr);
			this.depth = this.depth + blockSize;
			arr = updateArr(arr, blockSize);
			
			
			//move the arm3 back up to optimal height
			while(counter>0) {
				raiseArm3();{
					counter--;
				}
			}
			

			//move to destination col
			while (this.width < 10) {
				while(pathBlockAcross(arr, right)) {
					upArm1();
				}
				extendArm2();
			}
			
			//lower into place
				//CHECK WHICH colHeight NEEDS TO BE USED
			if (colHeight(arr, right) < 1) {
				while (this.height-this.depth>colHeight(arr, right)) {
					if (pathBlockedDown(arr)) {
						while (this.height - this.depth > colHeight(arr, right)) {
							downArm1();
							
						}
					} else {
						lowerArm3();
						counter++;
					}
				}
			}else {
				while(this.height-this.depth>colHeight(arr, right)) {
					if(pathBlockedDown(arr)) {
						while(this.height-this.depth>colHeight(arr, right)) {
							downArm1();
						}
					}else {
						lowerArm3();
							counter++;
					}
				}
			}

			//place block and update relevant information
			robot.drop();
			arr = updateArr(arr, blockSize);
			this.depth = this.depth -blockSize;
			blockSize = 0;
			right = false;
			noMoved++;
			//move arm back into optimal position
			while(counter>0) {
				raiseArm3();{
					counter--;
				}
			}
			
			System.out.print("\nend Right\n-----");
			//Move onto right
			
			
			//next slide
			}else {
			
					System.out.print("Start Left\n-----\n");
					//Find the block to pick up
					target = findNextBlockLeft(arr);
					
					//move to that block
					while(this.width>target) {
						while(pathBlockAcross(arr, right)) {
							upArm1();
						}
						contractArm2();
					}
					for (int i = 0; i < arr.length; i++) {
						System.out.println(Arrays.toString(arr[i]));
					}

					//move down to positing to pick up the block
						//CHECK WHICH colHeight NEEDS TO BE USED
					System.out.println(this.height-this.depth);
					while(this.height-this.depth>colHeightBelow(arr)) {
						if(pathBlockedDown(arr)) {
							while(this.height-this.depth>colHeightBelow(arr)) {
								lowerArm3();
								counter++;
							}
						}else {downArm1();
							
						}
					}
					//pickup the block and update relevant info
					
					robot.pick();
					blockSize = getBlockSize(arr);
					this.depth = this.depth + blockSize;
					arr = updateArr(arr, blockSize);
					
					
					//move the arm3 back up to optimal height
					while(counter>0) {
						raiseArm3();{
							counter--;
						}
					}
					

					//move to destination col
					while (this.width > 1) {
						while(pathBlockAcross(arr, right)) {
							upArm1();
						}
						contractArm2();
					}
					
					//lower into place
						//CHECK WHICH colHeight NEEDS TO BE USED
					if (colHeightBelow(arr) < 1) {
						while (this.height-this.depth>colHeight(arr, right)) {
							if (pathBlockedDown(arr)) {
								while (this.height + this.depth > colHeight(arr, right)) {
									lowerArm3();
									counter++;
								}
							} else {
								downArm1();
							}
						}
						
					} else {
						while (this.height-this.depth>colHeightBelow(arr)) {

							if (pathBlockedDown(arr)) {
								while (this.height + this.depth > colHeightBelow(arr)) {
									lowerArm3();
									counter++;
								}
							} else {
								downArm1();
							}
						}

					}

					//place block and update relavent information
					robot.drop();
					arr = updateArr(arr, blockSize);
					this.depth = this.depth-blockSize;
					blockSize = 0;
					right = true;
					noMoved++;
					
					//move arm back into optimal position
					while(counter>0) {
						raiseArm3();{
							counter--;
						}
					}
					
					System.out.print("\nend Left\n-----");
					//restart the whooooooooole process
					
		}
			
		}
	}

	private boolean pathBlockAcross(int[][] arr, boolean right) {
		if(this.height-this.depth>maxHeight(arr)) {
		return false;
		}else {
			if(this.height-this.depth<colHeight(arr, right)){
			return true;
			}else{
			return false;
			}
		}
	}
	
	
	private int colHeight(int[][] arr, boolean right) {
		int colHeight=0;
		if(right) {
			if(this.width==10) {
				if(arr[9].length<1) {
					System.out.println("Col height: "+colHeight);
					return colHeight;
				}else {
					for(int i = 0; i<arr[this.width-1].length;i++) {
						colHeight = colHeight + arr[this.width-1][i];
					}
					System.out.println("Col height: "+colHeight);
					return colHeight;
				}
			}else {
		for(int i = 0; i<arr[this.width].length;i++) {
			colHeight = colHeight + arr[this.width][i];
		}
		System.out.println("Col height: "+colHeight);
		return colHeight;
			}
	}else {
		if(this.width==1) {
			for(int i =0; i<arr[0].length; i++) {
				colHeight = colHeight + arr[this.width][i];
			}
			System.out.println("Col height: "+colHeight);
			return colHeight;
		}else {
		for(int i = 0; i<arr[this.width-2].length;i++) {
			System.out.println("Col height: "+colHeight);
			colHeight = colHeight + arr[this.width-2][i];
		}
		System.out.println("Col height: "+colHeight);
		return colHeight;
	}
	}
		
	}	

	private int colHeightBelow(int[][] arr) {
		int colHeight=0;

		for(int i = 0; i<arr[this.width-1].length;i++) {
			System.out.println("Col height: "+colHeight);
			colHeight = colHeight + arr[this.width-1][i];
		}
		System.out.println("Col height"+(this.width-1)+": "+colHeight);
		return colHeight;
	}
	
	
	private boolean pathBlockedDown(int[][]arr) {
		System.out.println("Max Height : "+maxHeight(arr));
		System.out.println("Arm1 Height : "+this.height);
		System.out.println("arm3 Depth: "+this.depth);
		if(this.height - this.depth > maxHeight(arr)) {
			if(this.width>tallestCol(arr)) {
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}

	private int[][] updateArr(int[][] arr, int blockSize) {
		int[] temp;

		if (this.width == 10 || this.width == 1) {
			if (arr[this.width - 1].length < 1) {
				temp = new int[] { blockSize };
				arr[this.width - 1] = temp;
				return arr;
			} else {
				temp = new int[(arr[this.width - 1].length) + 1];
				for (int i = 0; i < temp.length; i++) {
					if (i == temp.length - 1) {
						temp[i] = blockSize;
					} else {
						temp[i] = arr[this.width - 1][i];

					}
				}
				arr[this.width - 1] = temp;
				return arr;
			}
		}

		else {
			temp = new int[(arr[this.width - 1].length) - 1];
			{
				for (int i = 0; i <= temp.length - 1; i++) {

					temp[i] = arr[this.width - 1][i];
					System.out.println(arr[this.width - 1][i]);
				}
			}
		}

		arr[this.width - 1] = temp;

		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}

		return arr;
	}

	private int getBlockSize(int[][] arr) {
		System.out.println("Blocksize = " + arr[this.width - 1][(arr[this.width - 1].length - 1)]);
		return arr[this.width - 1][(arr[this.width - 1].length - 1)];

	}


	private int maxHeight(int[][] arr) {
		int maxHeight = 0;
		for (int i = 0; i < 10; i++) {
			int newHeight = 0;
			for (int z = 0; z < arr.length; z++) {
				try {
					newHeight = newHeight + arr[i][z];
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
			if (newHeight > maxHeight) {
				maxHeight = newHeight;
			}
		}
		return maxHeight;
	}

	private int tallestCol(int[][] arr) {
		int maxHeight = 0;
		int colNum = 0;
		for (int i = 0; i < 10; i++) {
			int newHeight = 0;
			for (int z = 0; z < arr.length; z++) {
				try {
					newHeight = newHeight + arr[i][z];
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
			if (newHeight > maxHeight) {
//				System.out.println("New Max Height" + newHeight);
				maxHeight = newHeight;
				colNum = i;
			}
		}
		return colNum;
	}

	private int findNextBlockRight(int[][] arr) {
		int y = 0;
		for (int x = 1; x < 9; x++) {
			if (arr[x].length <= 1) {
				y++;
				continue;

			} else {
				return x+1;
			}
		}
		return 0;
	}

	private int findNextBlockLeft(int[][] arr) {

		int y = this.barHeights.length;
		if(y<1) {
			for (int x= 8; x> 1; x--) {
				if(arr[x].length >=1) {
					System.out.println("Target col: "+(x+1));
					return x+1;
				}
			}
		}
			for (int x= 8; x> 1; x--) {
		int i = arr[x].length-1;	
		
		if(arr[x].length >1) {
			if(arr[x][i] != this.barHeights[y]) {
				System.out.println("Target col: "+(x+1));
				return x+1;
			}else {
				y--;
				continue;
			}
		}
		}
		return 0;
	
}

	private void downArm1() {
		robot.down();
		this.height--;
	}

	private void upArm1() {
		robot.up();
		this.height++;
	}

	private void extendArm2() {
		robot.extend();
		this.width++;
	}

	private void contractArm2() {
			robot.contract();
			this.width--;
		}

	private void lowerArm3() {
			robot.lower();
			this.depth++;
		}

	private void raiseArm3() { 
			robot.raise();
			this.depth--;
	
	}
