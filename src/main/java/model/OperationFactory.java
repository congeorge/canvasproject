package model;

public interface OperationFactory {
		String CANVAS_DRAW="C";
		String LINE_DRAW="L";
		String RECTANGLE_DRAW="R";
		String FILL="B";
		String QUIT="Q";
		
		public static CanvasOperation getCommand(String[] inputArgs) throws  Exception {
			CanvasOperation operation=null;
			switch(inputArgs[0]){
					case CANVAS_DRAW:
						operation = new DrawCanvasOperation(inputArgs);
						break;
					case LINE_DRAW:
						operation = new LineOperation(inputArgs);
						break;
					case RECTANGLE_DRAW:
						operation = new RectangleOperation(inputArgs);
						break;
					case FILL:
						operation = new FillOperation(inputArgs);
						break;
					case QUIT:
						operation = new QuitOperation(inputArgs);
						break;

					default:
						throw new Exception();
				}
			return operation;
		}

}
